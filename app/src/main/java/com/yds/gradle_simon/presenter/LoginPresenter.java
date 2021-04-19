package com.yds.gradle_simon.presenter;

import android.util.Log;

import com.yds.gradle_simon.contract.LoginContract;
import com.yds.gradle_simon.entity.LoginBean;
import com.yds.gradle_simon.http.HttpCallback;
import com.yds.gradle_simon.http.HttpConstant;
import com.yds.gradle_simon.http.HttpHelper;
import com.yds.gradle_simon.mvp.BasePresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YDS
 * @date 2021/4/13
 * @discribe
 */
public class LoginPresenter extends BasePresenter<LoginContract> {

    public LoginPresenter() {
    }

    public void loginAccount(String username, String password) {
        if (getView() != null) {
            getView().showLoading("进度提示");

            Map<String, Object> params = new HashMap<>();
            params.put("username", username);
            params.put("password", password);

            HttpHelper.getInstance().doPost(HttpConstant.loginUrl, params, new HttpCallback<LoginBean>() {
                @Override
                public void onSuccess(LoginBean object) {

                }

                @Override
                public void onFailure(String errMsg) {
                    super.onFailure(errMsg);
                    Log.i("Simon", "onFailure = " + errMsg);
                }
            });
        }
    }

}
