package com.dawn.cs_study.content.application;

import com.dawn.cs_study.content.application.port.in.ReadSlugUseCase;
import com.dawn.cs_study.content.application.port.out.ContentReaderPort;
import com.dawn.cs_study.content.domain.Slug;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadSlugService implements ReadSlugUseCase {

    private final ContentReaderPort contentReaderPort;

    @Override
    public Slug readSlug(String key) {
        return contentReaderPort.readJson(key, Slug.class);
    }
}
