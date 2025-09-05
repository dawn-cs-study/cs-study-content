package com.dawn.cs_study.content.application.port.out;

import com.dawn.cs_study.content.domain.Slug;

import java.util.Optional;

public interface SlugRepository {

    Optional<Slug> findById(Long id);

    Slug save(Slug slug);

    void deleteById(Long id);

}
