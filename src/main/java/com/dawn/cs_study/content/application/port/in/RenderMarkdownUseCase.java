package com.dawn.cs_study.content.application.port.in;

import com.dawn.cs_study.content.domain.Slug;

public interface RenderMarkdownUseCase {

    // 이름과 분리에 대해서는 더 고민해봐야 함

    String renderHtml(String key);

}
