package com.yds.gradle_simon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yds.customize.entity.BottomDialogBean;
import com.yds.customize.util.ToastUtil;
import com.yds.customize.view.EmptyOrErrorView;
import com.yds.customize.view.FunctionBottomDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * ./gradlew install
 * ./gradlew bintrayUpload
 * <p>
 * https://jcenter.bintray.com/com/simon/customize/
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_test = findViewById(R.id.bt_test);
        bt_test.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_test:
                break;
        }
    }
}