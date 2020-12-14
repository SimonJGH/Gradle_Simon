package com.yds.customize.util.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

/**
 * @author YDS
 * @date 2020/12/2
 * @discribe 网络实时监测
 */
@SuppressWarnings("all")
public class NetworkReceiver extends BroadcastReceiver {
    private OnNetConnectListener mOnNetConnectListener;
    private boolean isNetConnceting = false;//当前网络是否正在连接

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
            boolean isConnected = NetworkUtil.isNetworkAvailable();
            if (isConnected) {
                if (mOnNetConnectListener != null) {
                    if (!isNetConnceting) {
                        isNetConnceting = true;
                        mOnNetConnectListener.onNetConnect();
                    }
                }
            } else {
                if (mOnNetConnectListener != null) {
                    if (isNetConnceting) {
                        isNetConnceting = false;
                        mOnNetConnectListener.onNetDisConnect();
                    }
                }
            }
        }
    }

    /**
     * 网络监听
     */
    public interface OnNetConnectListener {
        void onNetConnect();

        void onNetDisConnect();
    }

    public void setOnNetConnectListener(OnNetConnectListener onNetConnectListener) {
        this.mOnNetConnectListener = onNetConnectListener;
    }

}