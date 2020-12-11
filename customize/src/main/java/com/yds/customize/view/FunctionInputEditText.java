package com.yds.customize.view;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.yds.customize.R;


/**
 * @author YDS
 * @date 2020/12/8
 * @discribe 输入EditText
 */
@SuppressWarnings("all")
public class FunctionInputEditText extends LinearLayout implements View.OnClickListener {

    private EditText et_function_input_msg;
    private ImageButton ib_function_input_clear, ib_function_input_eye;
    private View v_function_input_underline;

    private String et_hint_msg;
    private boolean clearIconAvailable = false;                       //清空按钮是否展示
    private boolean eyeIconAvailable = false;                         //可视按钮是否展示
    private boolean isEyeOpen = false;                                //密码是否可视

    public FunctionInputEditText(Context context) {
        super(context);
    }

    public FunctionInputEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_function_input_edittext, this);

        et_function_input_msg = inflate.findViewById(R.id.et_function_input_msg);
        ib_function_input_clear = inflate.findViewById(R.id.ib_function_input_clear);
        ib_function_input_eye = inflate.findViewById(R.id.ib_function_input_eye);
        v_function_input_underline = inflate.findViewById(R.id.v_function_input_underline);

        ib_function_input_clear.setOnClickListener(this);
        ib_function_input_eye.setOnClickListener(this);

        initEditText();
    }

    /**
     * 初始化EditText
     */
    private void initEditText() {
        //默认展示提示语
        et_hint_msg = getResources().getString(R.string.hint_input);

        et_function_input_msg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //在清除按钮显示的情况下，如果输入为空-隐藏，反之-展示
                if (TextUtils.isEmpty(s.toString())) {
                    if (clearIconAvailable) {
                        ib_function_input_clear.setVisibility(GONE);
                    }
                } else {
                    if (clearIconAvailable) {
                        ib_function_input_clear.setVisibility(VISIBLE);
                    }

                    //设置光标在文字后
                    et_function_input_msg.setSelection(s.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    /**
     * 设置输入文字颜色
     *
     * @param resColor
     */
    public void setFunctionTextColor(int resColor) {
        et_function_input_msg.setTextColor(getResources().getColor(resColor));
    }

    /**
     * 设置输入文字大小
     *
     * @param size
     */
    public void setFunctionTextSize(int size) {
        et_function_input_msg.setTextSize(size);
    }

    /**
     * 设置输入提示文字
     *
     * @param hintText
     */
    public void setFunctionHintText(String hintText) {
        et_function_input_msg.setHint(hintText);
    }

    /**
     * 设置输入提示文字颜色
     *
     * @param resColor
     */
    public void setFunctionHintTextColor(int resColor) {
        et_function_input_msg.setHintTextColor(getResources().getColor(resColor));
    }

    /**
     * 设置输入类型
     *
     * @param typeClassNumber InputType.TYPE_CLASS_TEXT
     */
    public void setFunctionInputType(int typeClassNumber) {
        et_function_input_msg.setInputType(typeClassNumber);
    }

    /**
     * 设置输入最大字数限制
     *
     * @param length
     */
    public void setFunctionInputMaxLength(int length) {
        et_function_input_msg.setFilters(new InputFilter[]{new InputFilter.LengthFilter(length)});
    }

    /**
     * 设置清空按钮是否可用
     *
     * @param clearIconAvailable
     */
    public void setFunctionClearIconAvailable(boolean clearIconAvailable) {
        this.clearIconAvailable = clearIconAvailable;
    }

    /**
     * 设置清空按钮资源样式
     *
     * @param resId
     */
    public void setFunctionClearIcon(int resId) {
        ib_function_input_clear.setImageResource(resId);
    }


    /**
     * 设置可视按钮是否可用
     *
     * @param eyeIconAvailable
     */
    public void setFunctionEyeIconAvailable(boolean eyeIconAvailable) {
        this.eyeIconAvailable = eyeIconAvailable;
        ib_function_input_eye.setVisibility(eyeIconAvailable ? VISIBLE : GONE);

        //隐藏输入内容
        et_function_input_msg.setTypeface(Typeface.DEFAULT);
        et_function_input_msg.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    /**
     * 设置可视按钮资源样式
     *
     * @param resId drawable-selecter资源
     */
    public void setFunctionEyeIcon(int resId) {
        ib_function_input_eye.setImageResource(resId);
    }

    /**
     * 设置下划线是否可用
     *
     * @param underlineAvailable
     */
    public void setFunctionUnderlineAvailable(boolean underlineAvailable) {
        v_function_input_underline.setVisibility(underlineAvailable ? VISIBLE : INVISIBLE);
    }

    /**
     * 设置下划线背景色
     *
     * @param resColor
     */
    public void setFunctionUnderlineColor(int resColor) {
        v_function_input_underline.setBackgroundColor(getResources().getColor(resColor));
    }

    /**
     * 设置光标颜色
     *
     * @param resId drawable-shape资源
     */
    public void setFunctionCursorColor(int resId) {
        try {
            et_function_input_msg.setTextCursorDrawable(getResources().getDrawable(resId));
        } catch (Throwable ignored) {
            ignored.getMessage();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ib_function_input_clear) {
            et_function_input_msg.setText("");
            et_function_input_msg.setHint(et_hint_msg);
        } else if (id == R.id.ib_function_input_eye) {
            if (isEyeOpen) {
                ib_function_input_eye.setSelected(false);
                et_function_input_msg.setTypeface(Typeface.DEFAULT);
                et_function_input_msg.setTransformationMethod(PasswordTransformationMethod.getInstance());
            } else {
                ib_function_input_eye.setSelected(true);
                et_function_input_msg.setTypeface(Typeface.DEFAULT);
                et_function_input_msg.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            isEyeOpen = !isEyeOpen;
        }
    }


}
