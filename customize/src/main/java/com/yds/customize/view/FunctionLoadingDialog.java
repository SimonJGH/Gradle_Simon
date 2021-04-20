package com.yds.customize.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.yds.customize.R;

/**
 * @author YDS
 * @date 2020/12/17
 * @discribe 加载提示框
 */
@SuppressWarnings("all")
public class FunctionLoadingDialog extends Dialog {
    private ImageView iv_loading;
    private TextView tv_loading;

    private int mResId = R.mipmap.ic_loading_white;
    private String mResMsg = getContext().getResources().getString(R.string.loading_data);
    private int mColorId = R.color.white;
    private float mMsgSize = 14f;

    private RotateAnimation mRotateAnimation;
    private CountDownTimer countDownTimer;

    public FunctionLoadingDialog(@NonNull Context context) {
        this(context, R.style.LoadingDialog);
    }

    public FunctionLoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    /**
     * 设置加载图片
     *
     * @param resId
     */
    public void setLoadingResId(int resId) {
        this.mResId = resId;
    }

    /**
     * 设置加载提示语
     *
     * @param resMsg
     */
    public void setLoadingResMsg(String resMsg) {
        this.mResMsg = resMsg;
    }

    public void setLoadingResMsgColor(int colorId) {
        this.mColorId = colorId;
    }

    public void setLoadingResMsgSize(float size) {
        this.mMsgSize = size;
    }

    /**
     * 初始化自定义布局
     */
    private void initView() {
        setContentView(R.layout.layout_dialog_loading);
        // 设置窗口大小
        WindowManager windowManager = getWindow().getWindowManager();
        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.3f;
        attributes.width = (int) (screenWidth / 2.8);
        attributes.height = attributes.width;
        getWindow().setAttributes(attributes);
        setCancelable(false);

        TextView tv_loading = findViewById(R.id.tv_loading);
        ImageView iv_loading = findViewById(R.id.iv_loading);
        iv_loading.setImageResource(mResId);
        tv_loading.setText(mResMsg);
        tv_loading.setTextColor(getContext().getResources().getColor(mColorId));
        tv_loading.setTextSize(mMsgSize);

        iv_loading.measure(0, 0);
        mRotateAnimation = new RotateAnimation(0, 360, iv_loading.getMeasuredWidth() / 2, iv_loading.getMeasuredHeight() / 2);
        mRotateAnimation.setInterpolator(new LinearInterpolator());
        mRotateAnimation.setDuration(1000);
        mRotateAnimation.setRepeatCount(-1);
        iv_loading.startAnimation(mRotateAnimation);
    }

    @Override
    public void show() {
        initView();
        super.show();

        countDownTimer = new CountDownTimer(10 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                dismiss();
            }
        };
        countDownTimer.start();
    }

    @Override
    public void dismiss() {
        if (mRotateAnimation != null) {
            mRotateAnimation.cancel();
        }
        super.dismiss();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 屏蔽返回键
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}
