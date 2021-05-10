package com.yds.customize.util.image;

/**
 * @author YDS
 * @date 2021/5/10
 * @discribe 图片加载管理器
 */
@SuppressWarnings("all")
public class ImageLoadManager {

    public static final String TYPE_GLIDE = "glide";
    public static final String TYPE_XUTILS = "xutils";
    public static final String TYPE_DEFAULT = TYPE_GLIDE;

    /**
     * 默认引擎
     *
     * @return
     */
    public static IImageLoadListener load() {
        return load(TYPE_DEFAULT);
    }

    /**
     * 指定引擎
     *
     * @param type
     * @return
     */
    public static IImageLoadListener load(String type) {
        switch (type) {
            case TYPE_GLIDE:
                return new GlideEngine();
            case TYPE_XUTILS:
                return new XImageEngine();
            default:
                return new GlideEngine();
        }
    }

}
