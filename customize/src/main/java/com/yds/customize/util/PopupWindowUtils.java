package com.yds.customize.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;

/**
 * 作者：${Simon} on 2016/11/22 0022 16:12
 * <p>
 * 描述：PopupWindowUtils
 * 使用说明：
 * ①：context 需为 Activity
 * ②：PopupWindowUtils 分为标准格式布局-大小默认全屏和缩放格式布局两个，构造函数的参数来区分
 * ③：setPopupWindowSize 动态设置缩放比例
 * ④：setLocation 设置显示位置-相对自身布局（上 下 左 右 中）
 * ⑤：setDropDown 设置显示位置-相对basicview布局的下方
 */
@SuppressWarnings("all")
public class PopupWindowUtils {
    private PopupWindow popupWindow;

    private PopupWindowUtils() {
    }

    public static PopupWindowUtils getInstance() {
        return SafeMode.mPopup;
    }

    public static class SafeMode {
        private static final PopupWindowUtils mPopup = new PopupWindowUtils();
    }

    /**
     * 标准格式布局-大小默认全屏
     *
     * @param context 需为Activity
     * @param inflate 为popupwindow布局
     */
    public void createStandardPopupWindow(Context context, View inflate) {
        popupWindow = new PopupWindow(context);
        popupWindow.setWidth(LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(inflate);
    }

    /**
     * 缩放格式布局
     *
     * @param context 需为Activity
     * @param inflate 为popupwindow布局
     * @param scaleX  x轴缩放比例 0-wrap_content
     * @param scaleY  y轴缩放比例 0-wrap_content
     */
    public void createScalePopupWindow(Context context, View inflate, View basicView) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        popupWindow = new PopupWindow(context);
        WindowManager wm = ((Activity) context).getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        // 设置窗口大小
        int scaleWidth = basicView.getMeasuredWidth();
        int scaleHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        popupWindow.setWidth(scaleWidth);
        popupWindow.setHeight(scaleHeight);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(inflate);
        popupWindow.showAsDropDown(basicView, 0, 0);
    }

    /**
     * 设置PopupWindow缩放比例
     *
     * @param context 需为Activity
     * @param scaleX  x轴缩放比例
     * @param scaleY  y轴缩放比例
     */
    public void setPopupWindowSize(Context context, Double scaleX, Double scaleY) {
        WindowManager wm = ((Activity) context).getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        int scaleWidth = (int) (width * scaleX);
        int scaleHeight = (int) (height * scaleY);
        popupWindow.setWidth(scaleWidth);
        popupWindow.setHeight(scaleHeight);
    }


    /**
     * 设置显示位置-相对自身布局（上 下 左 右 中）
     *
     * @param parent  父布局view
     * @param x       x偏移量
     * @param y       y偏移量
     * @param gravity 显示位置如Gravity.CENTER
     */
    public void setLocation(View parent, int gravity, int x, int y) {
        popupWindow.showAtLocation(parent, gravity, x, y);
    }

    /**
     * 设置显示位置-相对basicview布局的下方
     *
     * @param basicView 相对自身view
     * @param x         x偏移量
     * @param y         y偏移量
     */
    public void setDropDown(View basicView, int x, int y) {
        popupWindow.showAsDropDown(basicView, x, y);
    }

    /**
     * pop消失监听
     *
     * @param listener
     **/
    public void setPopDismissListener(PopDismissListener listener) {
        if (null != popupWindow) {
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    listener.dismiss();
                }
            });
        }
    }

    //pop消失监听
    public interface PopDismissListener {
        void dismiss();
    }

    /**
     * 关闭
     */
    public void closePop() {
        if (null != popupWindow) {
            popupWindow.dismiss();
        }
    }
}
