package com.yds.gradle_simon.http;

import java.util.Map;

/**
 * @author YDS
 * @date 2021/4/13
 * @discribe 代理
 */
public class HttpHelper implements IHttpProcessor {
    private static IHttpProcessor mIHttpProcessor;

    //单例
    public static HttpHelper getInstance() {
        return SafeMode.mHttpHelper;
    }

    private static class SafeMode {
        private static final HttpHelper mHttpHelper = new HttpHelper();
    }

    public void init(IHttpProcessor httpProcessor) {
        mIHttpProcessor = httpProcessor;
    }

    @Override
    public void doPost(String url, Map<String, Object> params, ICallback callback) {
        mIHttpProcessor.doPost(url, params, callback);
    }

}
