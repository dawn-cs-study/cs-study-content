package com.dawn.cs_study.content.application.port.in;

import com.dawn.cs_study.content.domain.Slug;

public interface ReadSlugUseCase {

    Slug readSlug(String slugName);

}
