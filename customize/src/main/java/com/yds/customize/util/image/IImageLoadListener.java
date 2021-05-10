package com.yds.customize.util.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * @author YDS
 * @date 2021/5/10
 * @discribe 图片加载基类方法
 */
@SuppressWarnings("all")
public interface IImageLoadListener {

    /**
     * 加载常规图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param resPlaceholderId 默认填 -1
     * @param resErrorId       默认填 -1
     */
    void display(Context context, String url, ImageView imageView, int resPlaceholderId, int resErrorId);

    /**
     * 加载图片自动适配容器大小
     *
     * @param context
     * @param url
     * @param imageView
     * @param resPlaceholderId 默认填 -1
     * @param resErrorId       默认填 -1
     */
    void displayFit(Context context, String url, ImageView imageView, int resPlaceholderId, int resErrorId);

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param resPlaceholderId 默认填 -1
     * @param resErrorId       默认填 -1
     */
    void displayRoundImage(Context context, String url, ImageView imageView, int resPlaceholderId, int resErrorId);

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param corner
     * @param resPlaceholderId 默认填 -1
     * @param resErrorId       默认填 -1
     */
    void displayRectImage(Context context, String url, ImageView imageView, float corner, int resPlaceholderId, int resErrorId);

    /**
     * 加载圆角图片-自定义图片四个角弧度
     *
     * @param context
     * @param url
     * @param imageView
     * @param leftTop
     * @param rightTop
     * @param rightBottom
     * @param leftBottom
     * @param corner
     */
    default void displayRectImageCustom(Context context, String url, ImageView imageView, boolean leftTop, boolean rightTop, boolean rightBottom, boolean leftBottom, int corner) {
    }

    /**
     * 加载gif-glide
     *
     * @param context
     * @param resourceId
     * @param imageView
     * @param resPlaceholderId 默认填 -1
     * @param resErrorId       默认填 -1
     */
    default void displayGif(Context context, int resourceId, ImageView imageView, int resPlaceholderId, int resErrorId) {

    }

    /**
     * 加载gif-xutils
     *
     * @param context
     * @param url
     * @param imageView
     * @param resPlaceholderId 默认填 -1
     * @param resErrorId       默认填 -1
     */
    default void displayGif(Context context, String url, ImageView imageView, int resPlaceholderId, int resErrorId) {

    }

}
