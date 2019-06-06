package com.victory.basemodule.test.presenter;


import com.victory.basemodule.test.view.BaseView;

/**
 * @Author： Victory
 * @Time： 2018/12/10
 * @QQ： 949021037
 * @Explain： com.test.networkrequestmodule.presenter
 */
public interface BasePresenterInterface {
    /**
     * 请求开始
     *
     * @param view
     */
    void onRequestStart(BaseView view);

    /**
     * 请求中
     */
    void onRequesting();

    /**
     * 请求停止
     */
    void onRequestStop();

}
