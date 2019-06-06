package com.victory.basemodule.test.view;

/**
 * @Author： Victory
 * @Time： 2018/12/10
 * @QQ： 949021037
 * @Explain： com.test.networkrequestmodule
 */
public interface BaseView<T> {
    /**
     * 请求成功
     * @param data
     */
    void requestSuccess(T data);

    /**
     * 请求失败
     * @param errorMessage
     */
    void requestError(String errorMessage);
}
