package com.yds.gradle_simon.http;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author YDS
 * @date 2021/4/13
 * @discribe 回调接口的一种实现
 */
public abstract class HttpCallback<Result> implements ICallback {

    @Override
    public void onSuccess(String sucMsg) {
        Class<?> clz = analysisClassType(this);
        Result result = (Result) new Gson().fromJson(sucMsg, clz);
        onSuccess(result);
    }

    /**
     * 分析class类型
     *
     * @param object
     * @return
     */
    private Class<?> analysisClassType(Object object) {
        Type superclass = object.getClass().getGenericSuperclass();
        Type[] typeArguments = ((ParameterizedType) superclass).getActualTypeArguments();
        return (Class<?>) typeArguments[0];
    }

    public abstract void onSuccess(Result object);

    @Override
    public void onFailure(String errMsg) {

    }
}
