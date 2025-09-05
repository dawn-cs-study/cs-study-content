package com.dawn.cs_study.content.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
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

}
