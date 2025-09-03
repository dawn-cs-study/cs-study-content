package com.dawn.cs_study.content.infrastructure.markdown;


import com.dawn.cs_study.content.application.port.out.MarkdownReaderPort;
import com.dawn.cs_study.content.application.port.out.ContentReaderPort;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MarkdownReaderAdapter implements MarkdownReaderPort {

    private final Parser parser;

    private final HtmlRenderer renderer;


    @Override
    public String toHtml(String md) {
        return renderer.render(parser.parse(md));
    }

}
