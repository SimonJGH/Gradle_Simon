package com.yds.gradle_simon.http;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author YDS
 * @date 2021/4/13
 * @discribe Subject
 */
public interface IHttpProcessor {

    void doPost(@NotNull String url, @NotNull Map<String, Object> params, @NotNull ICallback callback);

}
