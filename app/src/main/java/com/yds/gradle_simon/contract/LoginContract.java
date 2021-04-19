package com.yds.gradle_simon.contract;

import com.yds.gradle_simon.mvp.BaseContract;

/**
 * @author YDS
 * @date 2021/4/13
 * @discribe
 */
public interface LoginContract extends BaseContract {

    void onLoginResult(String result);
}
