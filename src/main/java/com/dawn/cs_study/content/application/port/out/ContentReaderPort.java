package com.dawn.cs_study.content.application.port.out;

public interface ContentReaderPort {

    String readText(String key);

    <T> T readJson(String key,Class<T> type);

}
