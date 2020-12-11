package com.yds.gradle_simon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;

import com.yds.customize.view.FunctionInputEditText;

/**
 * ./gradlew install
 * ./gradlew bintrayUpload
 *
 * https://jcenter.bintray.com/com/simon/customize/
 */
public class MainActivity extends AppCompatActivity {

    private FunctionInputEditText et_function_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_function_input = findViewById(R.id.et_function_input);

        //设置输入文字颜色
        et_function_input.setFunctionTextColor(R.color.gray_333333);
        //设置输入文字大小
        et_function_input.setFunctionTextSize(16);
        //设置输入提示文字颜色
        et_function_input.setFunctionHintTextColor(R.color.gray_999999);
        //设置输入提示文字
        et_function_input.setFunctionHintText("请输入...");
        //设置输入类型
        et_function_input.setFunctionInputType(InputType.TYPE_CLASS_NUMBER);
        //设置输入最大字数限制
        et_function_input.setFunctionInputMaxLength(11);

        //设置清空按钮是否可用
        et_function_input.setFunctionClearIconAvailable(true);
        //设置清空按钮资源样式
        et_function_input.setFunctionClearIcon(R.mipmap.icon_circle_delete);

        //设置可视按钮是否可用
        et_function_input.setFunctionEyeIconAvailable(true);
        //设置可视按钮资源样式
        et_function_input.setFunctionEyeIcon(R.drawable.icon_et_eye);

        //设置下划线是否可用
        et_function_input.setFunctionUnderlineAvailable(true);
        //设置下划线背景色
        et_function_input.setFunctionUnderlineColor(R.color.gray_333333);
        //设置光标颜色
        et_function_input.setFunctionCursorColor(R.drawable.icon_cursor_bg);

    }
}