package com.victory.basemodule.network.bean

/**
 * @Author： Victory
 * @Time： 2018/12/11
 * @QQ： 949021037
 * @Explain： com.test.networkrequestmodule.bean
 */
class BaseBean<T> {
    var errorCode: Int = 0
    var errorMsg: String? = null
    var data: T? = null
}
