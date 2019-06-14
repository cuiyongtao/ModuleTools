package com.victory.basemodule.network.presenter


import com.victory.basemodule.network.view.BaseView

/**
 * @Author： Victory
 * @Time： 2018/12/10
 * @QQ： 949021037
 * @Explain： com.test.networkrequestmodule.presenter
 */
interface BasePresenterInterface {
    /**
     * 请求开始
     *
     * @param view
     */
    fun onRequestStart(view: BaseView)

    /**
     * 请求中
     */
    fun onRequesting()

    /**
     * 请求停止
     */
    fun onRequestStop()

}
