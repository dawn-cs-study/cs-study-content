package com.dawn.cs_study.content.application;

import com.dawn.cs_study.content.application.port.in.RenderMarkdownUseCase;
import com.dawn.cs_study.content.application.port.out.ContentReaderPort;
import com.dawn.cs_study.content.application.port.out.MarkdownReaderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RenderMarkdownService implements RenderMarkdownUseCase {

    private final ContentReaderPort contentReaderPort;
    private final MarkdownReaderPort markdownReaderPort;

    @Override
    public String renderHtml(String documentName) {
        String md = contentReaderPort.readText(documentName);
        return markdownReaderPort.toHtml(md);
    }

}
