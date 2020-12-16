package com.yds.customize.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.yds.customize.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author YDS
 * @date 2020/12/16
 * @discribe 空/错误视图 数据为空时展示空占位图 请求异常展示错误占位图
 */
@SuppressWarnings("all")
public class EmptyOrErrorView extends FrameLayout implements View.OnClickListener {
    private ConstraintLayout cl_empty_or_error_container;
    private ImageView iv_empty_or_error_icon;
    private OnEmptyClickListener mOnEmptyClickListener;

    @IntDef({State.EMPTY, State.ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
        int EMPTY = 1;
        int ERROR = 2;
    }

    public EmptyOrErrorView(@NonNull Context context) {
        super(context);
    }

    public EmptyOrErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyOrErrorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public EmptyOrErrorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        inflate(getContext(), R.layout.layout_empty_or_error_view, this);
        cl_empty_or_error_container = findViewById(R.id.cl_empty_or_error_container);
        iv_empty_or_error_icon = findViewById(R.id.iv_empty_or_error_icon);

        iv_empty_or_error_icon.setOnClickListener(this);
    }

    /**
     * 展示错误占位图
     * @param resId
     */
    public void showError(int resId) {
        cl_empty_or_error_container.setVisibility(VISIBLE);
        setIcon(getResources().getDrawable(resId));
    }

    /**
     * 展示空占位图
     * @param resId
     */
    public void showEmpty(int resId) {
        cl_empty_or_error_container.setVisibility(VISIBLE);
        setIcon(getResources().getDrawable(resId));
    }

    /**
     * 隐藏视图
     */
    public void hide() {
        cl_empty_or_error_container.setVisibility(GONE);
    }

    private void setIcon(@Nullable Drawable drawable) {
        if (drawable == null) {
            iv_empty_or_error_icon.setVisibility(GONE);
        } else {
            iv_empty_or_error_icon.setVisibility(VISIBLE);
            iv_empty_or_error_icon.setImageDrawable(drawable);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_empty_or_error_icon) {
            if (mOnEmptyClickListener != null) {
                mOnEmptyClickListener.onEmptyClick();
            }
        }
    }

    public void setOnEmptyClickListener(OnEmptyClickListener onEmptyClickListener) {
        this.mOnEmptyClickListener = onEmptyClickListener;
    }

    /**
     * 占位图点击事件回调
     */
    public interface OnEmptyClickListener {
        void onEmptyClick();
    }
}
