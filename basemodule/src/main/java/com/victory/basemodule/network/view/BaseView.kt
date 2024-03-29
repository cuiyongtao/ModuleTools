package com.victory.basemodule.network.view

/**
 * @Author： Victory
 * @Time： 2018/12/10
 * @QQ： 949021037
 * @Explain： com.test.networkrequestmodule
 */
interface BaseView<T>{
    /**
     * 请求成功
     * @param data
     */
    fun requestSuccess(data: T)

    /**
     * 请求失败
     * @param errorMessage
     */
    fun requestError(errorMessage: String)
}
