package com.yds.customize.view;

import android.R.color;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * 作者：${Simon} on 2016/11/22 0022 17:29
 * <p>
 * 描述：FunctionEasyDialog
 * 使用说明：
 * ①：context 需为 Activity
 */
@SuppressWarnings("all")
public class FunctionEasyDialog {

    private Window window;
    private AlertDialog dialog;

    private FunctionEasyDialog() {
    }

    public static FunctionEasyDialog getInstance() {
        return SafeMode.mDialog;
    }

    public static class SafeMode {
        private static final FunctionEasyDialog mDialog = new FunctionEasyDialog();
    }

    /**
     * 获取dialog当前状态
     *
     * @return true打开 false关闭
     */
    public boolean getDialogState() {
        if (dialog != null) {
            return dialog.isShowing();
        }

        return false;
    }

    /**
     * 创建Dialog
     *
     * @param activity 上下文必须为Activity
     * @param inflate  dialog的展示布局
     * @param gravity  显示位置
     * @param scaleX   x轴缩放比例  0-默认wrapcontent 1-matchparent
     * @param scaleY   y轴缩放比例  0-默认wrapcontent 1-matchparent
     * @param back     true 支持返回键  false 屏蔽返回键
     */
    public void createDialog(Activity activity, View inflate, int gravity, Double scaleX, Double scaleY) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    return true;
                }
            });
            if (dialog != null) {
                dialog.dismiss();
            } else {
                dialog = builder.create();
            }
            dialog.setCanceledOnTouchOutside(false);
            /*
             * 一般在广播中使用dialog.getWindow().setType(WindowManager.LayoutParams.
             * TYPE_SYSTEM_ALERT);
             */
            dialog.show();
            dialog.setContentView(inflate);
            // 获取窗口
            window = dialog.getWindow();
            // 设置对话框背景为透明
            window.setBackgroundDrawableResource(color.transparent);
            // 设置窗口位置
            window.setGravity(gravity);
            // 获取窗口属性
            WindowManager.LayoutParams lp = window.getAttributes();
            // 获取窗口管理者
            WindowManager windowManager = ((Activity) activity).getWindowManager();
            // 获取真机参数
            Display display = windowManager.getDefaultDisplay();
            // 设置窗口大小
            if (scaleX == 0) {
                lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            } else if (scaleX == 1) {
                lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            } else {
                lp.width = (int) (display.getWidth() * scaleX);
            }
            if (scaleY == 0) {
                lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            } else if (scaleY == 1) {
                lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
            } else {
                lp.height = (int) (display.getHeight() * scaleY);
            }
            // 给窗口设置属性
            window.setAttributes(lp);
            //弹出系统键盘
            window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        } catch (Exception e) {
        }
    }

    /**
     * 设置过场动画
     *
     * @param animStyle 具体样式点击setAnimations看详情
     */
    /*
     * <!-- 底部dialog --> <style name="dialogWindowAnim"
     * parent="android:Animation"> <item
     * name="android:windowEnterAnimation">@anim/dialog_enter</item> <item
     * name="android:windowExitAnimation">@anim/dialog_exit</item> </style>
     */
    public void setAnimations(int animStyle) {
        if (window != null) {
            window.setWindowAnimations(animStyle);
        }
    }

    /**
     * 退出dialog
     */
    public void exitDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
