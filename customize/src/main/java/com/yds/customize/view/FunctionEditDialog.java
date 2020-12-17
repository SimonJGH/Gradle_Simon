package com.yds.customize.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.yds.customize.R;


/**
 * Created by yds on 2018/5/12.
 * 可输入对话框
 */
@SuppressWarnings("all")
public class FunctionEditDialog extends Dialog implements View.OnClickListener {
    private EditText et_dialog_content;

    private String title;
    private String content;
    private boolean canCancel;
    private OnItemClickLintener itemClickListener;


    /**
     * @param context
     * @param title     标题
     * @param content   输入内容
     * @param canCancel 是否可以取消
     */
    public FunctionEditDialog(@NonNull Context context, String title, String content, boolean canCancel) {
        this(context, R.style.normal_dialog, title, content, canCancel);
    }

    public FunctionEditDialog(@NonNull Context context, int themeResId, String title, String content, boolean canCancel) {
        super(context, themeResId);
        this.title = title;
        this.content = content;
        this.canCancel = canCancel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_custom_dialog_function_edit, null);
        ConstraintLayout cl_dialog_container_out = view.findViewById(R.id.cl_dialog_container_out);
        ConstraintLayout cl_dialog_container_in = view.findViewById(R.id.cl_dialog_container_in);
        TextView tv_dialog_title = view.findViewById(R.id.tv_dialog_title);
        et_dialog_content = view.findViewById(R.id.et_dialog_content);
        Button bt_dialog_confirm = view.findViewById(R.id.bt_dialog_confirm);
        Button bt_dialog_cancel = view.findViewById(R.id.bt_dialog_cancel);

        bt_dialog_confirm.setOnClickListener(this);
        bt_dialog_cancel.setOnClickListener(this);

        //如果标题为空，隐藏标题栏
        if (TextUtils.isEmpty(title)) {
            tv_dialog_title.setVisibility(View.GONE);
        } else {
            tv_dialog_title.setText(title);
            tv_dialog_title.setVisibility(View.VISIBLE);
        }

        //如果默认数据不为空-展示，否则展示hint提示语
        if (!TextUtils.isEmpty(content)) {
            et_dialog_content.setText(content);
        }

        //设置宽度为屏幕的0.85
        int widthPixels = getContext().getResources().getDisplayMetrics().widthPixels;
        ViewGroup.LayoutParams layoutParams = cl_dialog_container_in.getLayoutParams();
        layoutParams.width = (int) (widthPixels * 0.85);
        cl_dialog_container_in.setLayoutParams(layoutParams);

        //是否可以取消
        setCancelable(canCancel);
        //点击外部区域取消
        if (canCancel) {
            cl_dialog_container_out.setOnClickListener(this);
        }

        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0); //消除边距
//        window.setWindowAnimations(R.style.AnimBottom);
        window.setGravity(Gravity.CENTER);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(view);

    }

    /**
     * 点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.bt_dialog_confirm) {
            if (itemClickListener != null) {
                String content = et_dialog_content.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(getContext(), getContext().getResources().getString(R.string.empty_input), Toast.LENGTH_SHORT).show();
                    return;
                }
                itemClickListener.onConfirm(content);
            }
        } else if (viewId == R.id.bt_dialog_cancel) {
            if (itemClickListener != null) {
                itemClickListener.onCancel();
            }
        } else if (viewId == R.id.cl_dialog_container_out) {
            if (itemClickListener != null) {
                itemClickListener.manualDismiss();
            }
        }
        dismiss();
    }

    //设置选项点击回调
    public void setOnItemClickListener(OnItemClickLintener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    //选项点击回调
    public interface OnItemClickLintener {
        //确认
        void onConfirm(String content);

        //取消
        void onCancel();

        //主观关闭dialog
        void manualDismiss();
    }

    /*@Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 屏蔽返回键
            return canCancel;
        }
        return super.onKeyDown(keyCode, event);
    }*/

}
