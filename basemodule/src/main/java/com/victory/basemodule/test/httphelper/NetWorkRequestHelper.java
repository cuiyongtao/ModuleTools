package com.victory.basemodule.test.httphelper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.victory.basemodule.constant.BaseConstant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author： Victory
 * @Time： 2018/12/10
 * @QQ： 949021037
 * @Explain： com.test.networkrequestmodule.httphelper
 */
public class NetWorkRequestHelper<T> {

    private static NetWorkRequestHelper netWorkRequestHelperInstance;
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    private Gson mGson;
    private NetWorkRequertLogInterceptor mNetWorkRequertLogInterceptor;


    public static NetWorkRequestHelper getNetWorkRequestHelperInstance() {
        if (netWorkRequestHelperInstance == null) {
            netWorkRequestHelperInstance = new NetWorkRequestHelper();
        }
        return netWorkRequestHelperInstance;
    }

    /**
     * OkHttpClient配置
     *
     * @return
     */
    private OkHttpClient getOkHttpClient() {
        mOkHttpClient = new OkHttpClient.Builder()
                //设置超时时间
                .connectTimeout(BaseConstant.Companion.getNetworkTime(), TimeUnit.SECONDS)
                //写入超时
                .writeTimeout(BaseConstant.Companion.getNetworkTime(), TimeUnit.SECONDS)
                //读取超时
                .readTimeout(BaseConstant.Companion.getNetworkTime(), TimeUnit.SECONDS)
                //拦截器 日志打印
                .addInterceptor(getNetWorkRequertLogInterceptor())
                //https
//                .sslSocketFactory(SslSocketFactoryUtils.createSSLSocketFactory(mCotext), SslSocketFactoryUtils.createTrustAllManager())
                .build();
        return mOkHttpClient;
    }

    private NetWorkRequertLogInterceptor getNetWorkRequertLogInterceptor() {
        if (mNetWorkRequertLogInterceptor == null) {
            mNetWorkRequertLogInterceptor = new NetWorkRequertLogInterceptor();
        }
        return mNetWorkRequertLogInterceptor;
    }


    /**
     * Gson配置
     *
     * @return
     */
    private Gson getGson() {
        mGson = new GsonBuilder()
                //格式化时间
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        return mGson;
    }

    /**
     * Retrofit配置
     *
     * @return
     */
    public Retrofit getRetrofit() {
        mRetrofit = new Retrofit.Builder()
                //判断是否是调试模式
                .baseUrl(BaseConstant.Companion.isDebug() ? BaseConstant.Companion.getBaseURL():BaseConstant.Companion.getDebugURL())
                //Goso序列化数据
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                //使用RxJava
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //载入Okhttp设置
                .client(getOkHttpClient())
                .build();
        return mRetrofit;
    }

    /**
     * 网络请求服务
     *
     * @return
     */
    public T getNetWorkRequestServer(Class<T> tClass) {
        return getRetrofit().create(tClass);
    }
}
