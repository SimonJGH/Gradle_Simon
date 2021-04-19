package com.yds.gradle_simon.http;

/**
 * @author YDS
 * @date 2021/4/13
 * @discribe 顶层回调接口
 */
public interface ICallback {
    void onSuccess(String sucMsg);

    void onFailure(String errMsg);
}
