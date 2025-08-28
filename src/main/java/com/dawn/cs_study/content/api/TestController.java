package com.dawn.cs_study.content.api;

import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Controller
public class TestController {

    private final Parser parser = Parser.builder().extensions(
                    java.util.List.of(TablesExtension.create(),
                            StrikethroughExtension.create(),
                            TaskListExtension.create(),
                            AutolinkExtension.create()))
            .build();
    private final HtmlRenderer renderer = HtmlRenderer.builder().build();

    @GetMapping("/test")
    @ResponseBody
    public String test() {

        String path = "docs/test.md";
        ClassPathResource resource = new ClassPathResource(path);

        // 2. 파일 읽기
        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            String md = FileCopyUtils.copyToString(reader);

            // 3. Markdown → HTML 변환
            return renderer.render(parser.parse(md));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
