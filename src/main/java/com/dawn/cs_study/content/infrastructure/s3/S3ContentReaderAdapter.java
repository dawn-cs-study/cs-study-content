package com.dawn.cs_study.content.infrastructure.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.dawn.cs_study.content.application.port.out.ContentReaderPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class S3ContentReaderAdapter implements ContentReaderPort {

    private final AmazonS3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String readText(String key) {
        return readAsString(key);
    }

    @Override
    public <T> T readJson(String key, Class<T> type) {
        String json = readAsString(key);
        try {
            return new ObjectMapper().readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException("JSON 파싱 실패: " + key, e);
        }
    }

    private String readAsString(String key) {
        S3Object s3Object = s3Client.getObject(bucket, key);
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(s3Object.getObjectContent()))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}