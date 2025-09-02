package com.dawn.cs_study.content.infrastructure.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import com.dawn.cs_study.content.domain.Slug;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Objects;

public class SlugCreatedHandler implements RequestHandler<S3Event, String> {

    // AWS SDK v2 S3 Client
    private static final S3Client S3 = S3Client.builder().build();

    // Jackson
    private static final ObjectMapper OM = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Override
    public String handleRequest(S3Event event, Context ctx) {
        var log = ctx.getLogger();

        for (S3EventNotification.S3EventNotificationRecord r : event.getRecords()) {
            final String eventName = r.getEventName(); // ex) ObjectCreated:Put
            final String bucket = r.getS3().getBucket().getName();
            final String key = URLDecoder.decode(r.getS3().getObject().getKey(), StandardCharsets.UTF_8);

            // 1) 생성 이벤트만 처리
            if (eventName == null || !eventName.startsWith("ObjectCreated:")) {
                log.log("skip non-created event: " + eventName);
                continue;
            }
            // 2) .json만 처리
            if (!key.endsWith(".json")) {
                log.log("skip non-json key: " + key);
                continue;
            }

            try {
                String json = readObjectAsString(bucket, key);
                Slug slug = parse(json, key);
                validate(slug, key);

                log.log("created slug: title=" + slug.title() + ", key=" + key);
            } catch (Exception e) {
                log.log("error handling key=" + key + " : " + e.getMessage());
                throw (e instanceof RuntimeException re) ? re : new RuntimeException(e);
            }
        }
        return "OK";
    }

    private String readObjectAsString(String bucket, String key) throws IOException {
        try (ResponseInputStream<GetObjectResponse> in =
                     S3.getObject(GetObjectRequest.builder().bucket(bucket).key(key).build())) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private Slug parse(String json, String key) {
        try {
            return OM.readValue(json, Slug.class);
        } catch (IOException e) {
            throw new IllegalStateException("invalid slug json: key=" + key + ", " + e.getMessage(), e);
        }
    }

    private void validate(Slug s, String key) {
        Objects.requireNonNull(s, "slug null: key=" + key);
        if (s.title() == null || s.title().isBlank())
            throw new IllegalStateException("slug.title blank: key=" + key);
        if (s.category() == null || s.category().isBlank())
            throw new IllegalStateException("slug.category blank: key=" + key);
    }
}