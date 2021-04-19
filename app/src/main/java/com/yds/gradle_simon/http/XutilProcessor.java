package com.yds.gradle_simon.http;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Iterator;
import java.util.Map;

/**
 * @author YDS
 * @date 2021/4/13
 * @discribe xutils框架
 */
public class XutilProcessor implements IHttpProcessor {
    private Handler mHandler;

    public XutilProcessor() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void doPost(String url, Map<String, Object> params, ICallback callback) {
        RequestParams requestParams = new RequestParams(url);
        Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            requestParams.addParameter(entry.getKey(), entry.getValue());
        }
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onFailure(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
