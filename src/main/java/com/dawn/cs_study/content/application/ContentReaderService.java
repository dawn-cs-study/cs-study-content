package com.dawn.cs_study.content.application;

import com.dawn.cs_study.content.application.port.in.UseCase;
import com.dawn.cs_study.content.application.port.out.MarkdownReaderPort;
import com.dawn.cs_study.content.application.port.out.ContentReaderPort;
import com.dawn.cs_study.content.domain.Slug;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentReaderService implements UseCase {

    private final MarkdownReaderPort markdownReaderPort;
    private final ContentReaderPort contentReaderPort;

    @Override
    public String renderHtml(String documentName) {
        String md = contentReaderPort.readText(documentName);
        return markdownReaderPort.toHtml(md);
    }

    // slug 만들어서 JPA로 DB 쓰기

    @Override
    public Slug readSlug(String slugName) {
        contentReaderPort.readJson(slugName, Slug.class);
        return contentReaderPort.readJson(slugName, Slug.class);
    }


}
