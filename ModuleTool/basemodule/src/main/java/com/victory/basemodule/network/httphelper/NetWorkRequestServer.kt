package com.victory.basemodule.network.httphelper


import com.victory.basemodule.network.bean.BaseBean

import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url

//import rx.Observable;

/**
 * @Author： Victory
 * @Time： 2018/12/10
 * @QQ： 949021037
 * @Explain： com.test.networkrequestmodule.httphelper
 */
interface NetWorkRequestServer<T> {

    /**
     * post请求表单提交，返回object类型
     *
     * @param url   请求地址
     * @param param 请求参数
     * @return
     */
    @JvmSuppressWildcards
    @FormUrlEncoded
    @POST
    fun postRequestFormObject(@Url url: String, @FieldMap param: Map<String, Any>): Observable<Any>

    /**
     * post请求表单提交，返回实体对象
     *
     * @param url   请求地址
     * @param param 请求参数
     * @return
     */
    @JvmSuppressWildcards
    @FormUrlEncoded
    @POST
    fun postRequestFormBean(@Url url: String, @FieldMap param: Map<String, Any>): Observable<BaseBean<T>>

    /**
     * post请求json提交，返回object类型
     *
     * @param url
     * @param data
     * @return
     */
    @JvmSuppressWildcards
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST
    fun postRequestJsonObject(@Url url: String, @Body data: RequestBody): Observable<Any>

    /**
     * post请求json提交，返回实体对象
     *
     * @param url
     * @param data
     * @return
     */
    @JvmSuppressWildcards
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST
    fun postRequestJsonBean(@Url url: String, @Body data: RequestBody): Observable<Any>

}
