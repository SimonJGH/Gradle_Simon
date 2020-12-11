package com.yds.customize.util;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yds.customize.R;


/**
 * Created by Simon on 2017/10/2.
 * <p>
 * 使用说明：需要获得MyApplication的context
 */
@SuppressWarnings("all")
public class ToastUtil extends BaseCustomizeUtil {

    private ToastUtil() {
    }

    public static ToastUtil getInstance() {
        return SafeMode.mToast;
    }

    /**
     * static final 保证了实例的唯一和不可改变
     */
    private static class SafeMode {
        private static final ToastUtil mToast = new ToastUtil();
    }

    /**
     * 吐司--短
     *
     * @param msg
     */
    public void showShortToast(String msg) {
        Toast toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * 吐司--短--底部
     *
     * @param msg
     */
    public void showShortToastBottom(String msg) {
        Toast toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 150);
        toast.show();
    }

    /**
     * 吐司--长
     *
     * @param msg
     */
    public void showLongToast(String msg) {
        Toast toast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * 吐司--长--底部
     *
     * @param msg
     */
    public void showLongToastBottom(String msg) {
        Toast toast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 150);
        toast.show();
    }


    /**
     * 吐司--自定义图片布局
     *
     * @param msg
     */
    public void showShortImageToast(int icon_id,String msg) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_toast_custom, null);
        ImageView iv_toast_icon = view.findViewById(R.id.iv_toast_icon);
        iv_toast_icon.setImageResource(icon_id);
        TextView tv_toast_message = view.findViewById(R.id.tv_toast_message);
        tv_toast_message.setText(msg);

        Toast toast = new Toast(mContext);
        toast.setGravity(Gravity.CENTER, 0, 10);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }
}
