package com.yds.customize.util.network;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.CountDownTimer;


import com.yds.customize.R;
import com.yds.customize.util.BaseCustomizeUtil;

import static android.content.Context.WIFI_SERVICE;

/**
 * @author YDS
 * @date 2020/12/2
 * @discribe 网络连接管理类 使用说明：离开当前activity或者fragment时要记得调用destroy销毁connManager
 */
@SuppressWarnings("all")
public class NetworkUtil extends BaseCustomizeUtil {

    private static ConnectivityManager connManager = null;
    private static CountDownTimer mCountDownTimer;

    /**
     * 判断网络是否连接（基站或wifi）
     *
     * @param context
     * @return boolean
     */
    public static boolean isNetworkConnected() {
        if (isNetworkAvailable()) {
            int type = getConnectedType();
            if (type == 0 || type == 1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 判断网络是否可用（所有网络类型）
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable() {
        if (mContext != null) {
            connManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * WIFI网络是否链接
     *
     * @param context
     * @return boolean
     */
    public static boolean isWifiConnected() {
        if (mContext != null) {
            connManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiInfo = connManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (wifiInfo != null) {
                return wifiInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 获取wifi状态级别
     *
     * @param levelListener
     */
    public static void getWifiStateLevel(NetworkStateLevelListener levelListener) {
        int connectedType = getConnectedType();
        if (connectedType == 1) {
            WifiManager wifi_service = (WifiManager) mContext.getSystemService(WIFI_SERVICE);
            WifiInfo wifiInfo = wifi_service.getConnectionInfo();
            if (mCountDownTimer == null) {
                initWifiStateLevelReset(wifiInfo, levelListener);
            }
        }
    }

    /**
     * 重复获取wifi信息
     *
     * @param wifiInfo
     * @param levelListener
     */
    private static void initWifiStateLevelReset(final WifiInfo wifiInfo, final NetworkStateLevelListener levelListener) {
        String macAddress = wifiInfo.getMacAddress();
        int ipAddress = wifiInfo.getIpAddress();
        int networkId = wifiInfo.getNetworkId();
        int rssi = wifiInfo.getRssi();

        mCountDownTimer = new CountDownTimer(2 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                initWifiStateLevelReset(wifiInfo, levelListener);
            }
        }.start();

        //根据获得的信号强度发送信息
        if (rssi <= 0 && rssi >= -50) {
            levelListener.signalBest(R.mipmap.card_icon_wifi_4, mContext.getString(R.string.signal_best));
        } else if (rssi < -50 && rssi >= -70) {
            levelListener.signalGood(R.mipmap.card_icon_wifi_3, mContext.getString(R.string.signal_good));
        } else if (rssi < -70 && rssi >= -80) {
            levelListener.signalAverage(R.mipmap.card_icon_wifi_2, mContext.getString(R.string.signal_average));
        } else if (rssi < -80 && rssi >= -100) {
            levelListener.signalPoor(R.mipmap.card_icon_wifi_1, mContext.getString(R.string.signal_poor));
        } else {
            levelListener.signalNone(R.mipmap.card_icon_wifi_0, mContext.getString(R.string.signal_none));
        }
    }

    /**
     * 基站网络是否连接
     *
     * @param context
     * @return boolean
     */
    public static boolean isMobileConnected() {
        if (mContext != null) {
            connManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mobileInfo = connManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mobileInfo != null) {
                return mobileInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 读取当前网络连接类型
     *
     * @param context
     * @return one of TYPE_MOBILE, TYPE_WIFI, TYPE_WIMAX, TYPE_ETHERNET,
     * TYPE_BLUETOOTH, or other types defined by ConnectivityManager
     * int值分别为：0、1、6、9、7
     */
    public static int getConnectedType() {
        if (mContext != null) {
            connManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                return networkInfo.getType();
            }
        }
        return -1;
    }

    /**
     * 网络断开连接提示
     *
     * @param context
     */
    public static void alertSetNetwork() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("提示：网络异常").setMessage("是否对网络进行设置?");

        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = null;
                try {
                    int sdkVersion = android.os.Build.VERSION.SDK_INT;
                    if (sdkVersion > 10) {
                        intent = new Intent(
                                android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                    } else {
                        intent = new Intent();
                        ComponentName comp = new ComponentName(
                                "com.android.settings",
                                "com.android.settings.WirelessSettings");
                        intent.setComponent(comp);
                        intent.setAction("android.intent.action.VIEW");
                    }
                    mContext.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    /**
     * 销毁connManager
     */
    public static void DestroyConnManager() {
        if (connManager != null) {
            connManager = null;
        }

        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }
}
