package com.yds.customize.util.network;


/**
 * @author YDS
 * @date 2020/12/14
 * @discribe 网络状态等级监听
 */
public interface NetworkStateLevelListener {

    /**
     * 信号最好
     *
     * @param levelResId  R.mipmap.card_icon_wifi_4
     * @param levelResMsg R.string.signal_best
     */
    void signalBest(int levelResId, String levelResMsg);

    /**
     * 信号较好
     *
     * @param levelResId  R.mipmap.card_icon_wifi_3
     * @param levelResMsg R.string.signal_good
     */
    void signalGood(int levelResId, String levelResMsg);

    /**
     * 信号一般
     *
     * @param levelResId  R.mipmap.card_icon_wifi_2
     * @param levelResMsg R.string.signal_average
     */
    void signalAverage(int levelResId, String levelResMsg);

    /**
     * 信号较差
     *
     * @param levelResId  R.mipmap.card_icon_wifi_1
     * @param levelResMsg R.string.signal_poor
     */
    void signalPoor(int levelResId, String levelResMsg);

    /**
     * 无信号
     *
     * @param levelResId  R.mipmap.card_icon_wifi_0
     * @param levelResMsg R.string.signal_none
     */
    void signalNone(int levelResId, String levelResMsg);

}
