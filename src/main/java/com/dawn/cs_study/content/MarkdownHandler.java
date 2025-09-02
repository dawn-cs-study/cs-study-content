//package com.dawn.cs_study.content;
//
//
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.RequestHandler;
//import com.amazonaws.services.lambda.runtime.events.S3Event;
//import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
//import org.springframework.core.annotation.Order;
//import software.amazon.awssdk.core.sync.RequestBody;
//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.model.PutObjectRequest;
//import software.amazon.awssdk.services.s3.model.S3Exception;
//
//import java.net.URLDecoder;
//import java.nio.charset.StandardCharsets;
//import java.time.LocalDate;
//import java.util.List;
//
//public class MarkdownHandler implements RequestHandler<S3Event, String> {
//
//    private static final S3Client S3_CLIENT = S3Client.builder().build();
//
//    /**
//     * Record to model the input event.
//     */
//
//    private record Slug(String title, String category, String[] tags, String author, LocalDate updatedAt) {
//    }
//
//    @Override
//    public String handleRequest(S3Event event, Context ctx) {
//
//        for (S3EventNotification.S3EventNotificationRecord record : event.getRecords()) {
//            final String eventName = record.getEventName();
//            final String bucketName = record.getS3().getBucket().getName();
//            final String encodeKey = record.getS3().getObject().getKey();
//            final String key = URLDecoder.decode(encodeKey, StandardCharsets.UTF_8);
//            final String versionId = record.getS3().getObject().getVersionId();
//
//
//            ctx.getLogger().log(eventName);
//            ctx.getLogger().log(bucketName);
//            ctx.getLogger().log(encodeKey);
//            ctx.getLogger().log(key);
//            ctx.getLogger().log(versionId);
//
//
//            if (!key.endsWith("slug.json")) {
//                ctx.getLogger().log("skip non-slug.json: " + key);
//                continue;
//            }
//
//            try {
//
//
//                if (eventName.startsWith("ObjectRemoved: ")) {
//
//                    String trimmed = key.endsWith("/slug.json") ? key.substring(0,key.length()-"/slug.json".length())
//                }
//
//
//            }
//
//        }
//
//    }
//
//
//}
