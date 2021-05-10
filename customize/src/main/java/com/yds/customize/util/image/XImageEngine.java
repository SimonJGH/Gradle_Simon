package com.yds.customize.util.image;

import android.content.Context;
import android.widget.ImageView;

import com.yds.customize.R;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * @author YDS
 * @date 2021/5/10
 * @discribe xutils引擎
 *
 * Tips：请在application中初始化 x.Ext.init(this);
 */
@SuppressWarnings("all")
public class XImageEngine implements IImageLoadListener {
    private static float density = -1F;
    private static int resDefaultPlaceholderId = R.drawable.rect_glide_default_error;  //加载默认图片
    private static int resDefaultErrorId = R.drawable.rect_glide_default_error;        //加载错误默认图片

    @Override
    public void display(Context context, String url, ImageView imageView, int resPlaceholderId, int resErrorId) {
        if (resPlaceholderId == -1) {
            resPlaceholderId = resDefaultPlaceholderId;
        }

        if (resErrorId == -1) {
            resErrorId = resDefaultErrorId;
        }

        ImageOptions imageOptions = new ImageOptions.Builder()
                .setLoadingDrawableId(resPlaceholderId)
                .setFailureDrawableId(resErrorId)
                .setUseMemCache(true)
                .setFadeIn(true) //淡入效果
                .build();

        x.image().bind(imageView, url, imageOptions);
    }

    @Override
    public void displayFit(Context context, String url, ImageView imageView, int resPlaceholderId, int resErrorId) {
        if (resPlaceholderId == -1) {
            resPlaceholderId = resDefaultPlaceholderId;
        }

        if (resErrorId == -1) {
            resErrorId = resDefaultErrorId;
        }

        ImageOptions imageOptions = new ImageOptions.Builder()
                .setLoadingDrawableId(resPlaceholderId)
                .setFailureDrawableId(resErrorId)
                .setUseMemCache(true)
                .setFadeIn(true) //淡入效果
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();

        x.image().bind(imageView, url, imageOptions);
    }

    @Override
    public void displayRoundImage(Context context, String url, ImageView imageView, int resPlaceholderId, int resErrorId) {
        if (resPlaceholderId == -1) {
            resPlaceholderId = resDefaultPlaceholderId;
        }

        if (resErrorId == -1) {
            resErrorId = resDefaultErrorId;
        }

        ImageOptions imageOptions = new ImageOptions.Builder()
                .setLoadingDrawableId(resPlaceholderId)
                .setFailureDrawableId(resErrorId)
                .setUseMemCache(true)
                .setCircular(true) //设置成圆形图片
                .setFadeIn(true) //淡入效果
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
                .build();

        x.image().bind(imageView, url, imageOptions);
    }

    @Override
    public void displayRectImage(Context context, String url, ImageView imageView, float corner, int resPlaceholderId, int resErrorId) {
        if (resPlaceholderId == -1) {
            resPlaceholderId = resDefaultPlaceholderId;
        }

        if (resErrorId == -1) {
            resErrorId = resDefaultErrorId;
        }

        ImageOptions imageOptions = new ImageOptions.Builder()
                .setUseMemCache(false)
                .setRadius(dip2px(context, corner))//设置拐角弧度
                .setFadeIn(true) //淡入效果
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                .setLoadingDrawableId(resPlaceholderId)
                .setFailureDrawableId(resErrorId)
                .build();

        x.image().bind(imageView, url, imageOptions);
    }

    @Override
    public void displayGif(Context context, String url, ImageView imageView, int resPlaceholderId, int resErrorId) {
        if (resPlaceholderId == -1) {
            resPlaceholderId = resDefaultPlaceholderId;
        }

        if (resErrorId == -1) {
            resErrorId = resDefaultErrorId;
        }

        ImageOptions imageOptions = new ImageOptions.Builder()
                .setLoadingDrawableId(resPlaceholderId)
                .setFailureDrawableId(resErrorId)
                .setUseMemCache(false)
                .setIgnoreGif(false)//是否忽略gif图。false表示不忽略。不写这句，默认是true
                .setFadeIn(true) //淡入效果
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
                .build();

        x.image().bind(imageView, url, imageOptions);
    }

    /**
     * dp转px
     *
     * @param context
     * @param dpValue
     * @return
     */
    private static int dip2px(Context context, float dpValue) {
        return (int) (dpValue * getDensity(context) + 0.5F);
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    private static int px2dip(Context context, float pxValue) {
        return (int) (pxValue / getDensity(context) + 0.5F);
    }

    /**
     * 获取当前像素密度
     *
     * @param context
     * @return
     */
    private static float getDensity(Context context) {
        if (density <= 0F) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return density;
    }

}
