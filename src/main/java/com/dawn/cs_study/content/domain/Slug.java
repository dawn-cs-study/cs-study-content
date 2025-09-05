package com.dawn.cs_study.content.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Slug {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ElementCollection
    private List<String> tags;

    private String summary;
    private String author;

    public static Slug of(String title, Category category, List<String> tags, String summary, String author) {
        return new Slug(null, title, category, tags, summary, author);
    }

}
