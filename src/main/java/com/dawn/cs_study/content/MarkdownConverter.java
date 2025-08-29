package com.dawn.cs_study.content;


import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class MarkdownConverter {

    private final Parser parser;

    private final HtmlRenderer renderer;

    public String toHtml(Resource resource) {
        Objects.requireNonNull(resource, "resource");
        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            String md = FileCopyUtils.copyToString(reader);
            return renderer.render(parser.parse(md == null ? "" : md));
        } catch (Exception e) {
            throw new IllegalStateException("Markdown 변환 실패: ", e);
        }
    }

}
