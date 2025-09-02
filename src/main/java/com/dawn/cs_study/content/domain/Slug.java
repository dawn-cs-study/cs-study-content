package com.dawn.cs_study.content.domain;

import java.util.List;

public record Slug(
        String title,
        String category,
        List<String> tags,
        String author,
        String updatedAt
) {
}
