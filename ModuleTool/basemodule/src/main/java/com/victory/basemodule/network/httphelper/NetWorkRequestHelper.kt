package com.victory.basemodule.network.httphelper

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.victory.basemodule.application.BaseApplication
import com.victory.basemodule.constant.BaseConstant

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Author： Victory
 * @Time： 2018/12/10
 * @QQ： 949021037
 * @Explain： com.test.networkrequestmodule.httphelper
 */
class NetWorkRequestHelper<T> {
    var mOkHttpClient: OkHttpClient? = null
    var mRetrofit: Retrofit? = null
    var mGson: Gson? = null
    var mNetWorkRequertLogInterceptor: NetWorkRequertLogInterceptor? = null
    var getNetWorkRequestHelperInstance: NetWorkRequestHelper<T>? = null

    /**
     * OkHttpClient配置
     *
     * @return
     */
    private//设置超时时间
    //写入超时
    //读取超时
    //拦截器 日志打印
    //https
    //
    val okHttpClient: OkHttpClient
        get() {
            mOkHttpClient = OkHttpClient.Builder()
                    .connectTimeout(BaseConstant.networkTime.toLong(), TimeUnit.SECONDS)
                    .writeTimeout(BaseConstant.networkTime.toLong(), TimeUnit.SECONDS)
                    .readTimeout(BaseConstant.networkTime.toLong(), TimeUnit.SECONDS)
                    .addInterceptor(netWorkRequertLogInterceptor)
//                    .sslSocketFactory(SslSocketFactoryUtils.createSSLSocketFactory(BaseApplication.Companion.mContext, SslSocketFactoryUtils.createTrustAllManager()))
                    .build()
            return mOkHttpClient!!
        }

    private val netWorkRequertLogInterceptor: NetWorkRequertLogInterceptor
        get() {
            if (mNetWorkRequertLogInterceptor == null) {
                mNetWorkRequertLogInterceptor = NetWorkRequertLogInterceptor()
            }
            return mNetWorkRequertLogInterceptor!!
        }


    /**
     * Gson配置
     *
     * @return
     */
    private//格式化时间
    val gson: Gson
        get() {
            mGson = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create()
            return mGson!!
        }

    /**
     * Retrofit配置
     *
     * @return
     */
    //判断是否是调试模式
    //Goso序列化数据
    //使用RxJava
    //载入Okhttp设置
    val retrofit: Retrofit
        get() {
            mRetrofit = Retrofit.Builder()
                    .baseUrl(if (BaseConstant.isDebug) BaseConstant.baseURL else BaseConstant.debugURL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()
            return mRetrofit!!
        }

    /**
     * 网络请求服务
     *
     * @return
     */
    fun getNetWorkRequestServer(tClass: Class<T>): T {
        return retrofit.create(tClass)
    }

}
