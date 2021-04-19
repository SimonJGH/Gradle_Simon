package com.yds.gradle_simon.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.yds.gradle_simon.R;
import com.yds.gradle_simon.contract.LoginContract;
import com.yds.gradle_simon.mvp.BaseActivity;
import com.yds.gradle_simon.presenter.LoginPresenter;

import org.jetbrains.annotations.Nullable;


/**
 * 临时测试
 */
public class TempActivity extends BaseActivity<LoginContract,LoginPresenter> implements LoginContract {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        getPresenter().loginAccount("18210431042", "123456");
    }

    @Override
    public void onLoginResult(String result) {
        Log.i("Simon", "onLoginResult = " + result);
    }

    @Nullable
    @Override
    public LoginContract initView() {
        return this;
    }

    @Nullable
    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

}