package com.dawn.cs_study.content.infrastructure.jpa;

import com.dawn.cs_study.content.domain.Slug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlugJpaRepository extends JpaRepository<Slug, Long> {
}
