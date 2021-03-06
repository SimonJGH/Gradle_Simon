package com.yds.customize.util;

/**
 * @author YDS
 * @date 2020/8/18
 * @discribe 防止 同一时间段内多次点击 工具
 */
@SuppressWarnings("all")
public class RepeatClickRefuseUtil {
    private static int DELAY_TIME = 2000;                 //点击间隔延迟2000毫秒

    private static boolean isCanClick = true;             //是否允许点击
    private static long lastClickTime = 0;                //上一次点击时间戳

    /**
     * 点击按钮事件
     *
     * @param listener
     */
    public static void clickButton(OnClickListener listener) {
        calculateTime(DELAY_TIME, listener);
    }

    /**
     * 点击按钮事件-自定义时间
     *
     * @param delayTime
     * @param listener
     */
    public static void clickButton(int delayTime, OnClickListener listener) {
        calculateTime(delayTime, listener);
    }

    /**
     * 时间计算
     *
     * @param delayTime
     * @param listener
     */
    private static void calculateTime(int delayTime, OnClickListener listener) {
        //获取两次点击事件得时间差
        long timeDifference = System.currentTimeMillis() - lastClickTime;
        //如果时间差大于我们设定的点击间隔时间，则允许用户点击
        if (timeDifference > delayTime) {
            isCanClick = true;
        } else {
            isCanClick = false;
            listener.onClickRefuse();
        }
        if (isCanClick) {
            lastClickTime = System.currentTimeMillis();
            listener.onClickEnabled();
        }
    }

    /**
     * 允许点击事件回调
     */
    public interface OnClickListener {
        void onClickEnabled();

        default void onClickRefuse() {

        }
    }
}
