package com.dawn.cs_study.content.api;

import com.dawn.cs_study.content.application.ReadSlugService;
import com.dawn.cs_study.content.application.RenderMarkdownService;
import com.dawn.cs_study.content.application.port.in.ReadSlugUseCase;
import com.dawn.cs_study.content.application.port.in.RenderMarkdownUseCase;
import com.dawn.cs_study.content.domain.Slug;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MarkdownController {

    private final ReadSlugUseCase readSlugUseCase;
    private final RenderMarkdownUseCase renderMarkdownUseCase;

    @GetMapping("/favicon.ico")
    public void favicon() {
        // 아무 것도 반환 안 함
    }

    @GetMapping("/{title}")
    public String test(@PathVariable(name = "title") String title) throws Exception {

        // 1-1. CDN 체크 후 없으면 html 렌더
        // 1-2. CDN 체크 있으면 바로 제공
        // 2. 결과물 CDN 으로 업로드 후 제공

        // 현재는 S3에서 가져오는 중
        return renderMarkdownUseCase.renderHtml("cs/content/os/test.md");
    }


    @GetMapping("dd")
    public Slug slug() {
        return readSlugUseCase.readSlug("cs/content/os/test.json");
    }

}

