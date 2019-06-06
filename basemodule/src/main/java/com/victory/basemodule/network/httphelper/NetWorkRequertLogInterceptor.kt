package com.victory.basemodule.network.httphelper


import com.victory.basemodule.constant.BaseConstant
import com.victory.basemodule.tools.LogUtil


import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.Response

/**
 * @Author： Victory
 * @Time： 2018/12/10
 * @QQ： 949021037
 * @Explain： com.test.networkrequestmodule.httphelper
 */
class NetWorkRequertLogInterceptor : Interceptor {
    internal var logUtil = LogUtil()
    override fun intercept(chain: Interceptor.Chain): Response {
        /**
         * request 请求体
         * response 返回体
         * mediaType 媒介类型
         * content 返回body
         * stringparam 请求参数
         */
        var request: Request? = null
        var response: Response? = null
        val mediaType: MediaType? = null
        var content = ""
        val stringparam: StringBuffer
        try {
            request = chain.request()
            response = chain.proceed(chain.request())

            /**
             * 添加头部信息
             * request.header()
             * request.headers();
             */
            stringparam = StringBuffer()
            content = response!!.body()!!.string()
            if (request!!.body() is FormBody) {
                val body = request.body() as FormBody?
                for (i in 0 until body!!.size()) {
                    stringparam.append(body.encodedName(i) + ":" + body.encodedValue(i) + ",")
                }
                logUtil.getLogNet(BaseConstant.NetWorkTAGURL, "-----" + "\nurl:" + request.url() + "\nparam:" + stringparam + "\nbody:" + content + "\n-----")
            } else {
                logUtil.getLogNet(BaseConstant.NetWorkTAGBody, "-----" + "\nurl:" + request.url() + "\nparam:" + "JSON参数请在传递时打印" + "\nbody:" + content + "\n-----")
            }
        } catch (e: Exception) {
            logUtil.getLogNet(BaseConstant.NetWorkTAGError, e.toString())
        }

        /**
         * okhttp调用一次后会close，所以新建了一个
         */
        return response!!.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build()
    }
}
