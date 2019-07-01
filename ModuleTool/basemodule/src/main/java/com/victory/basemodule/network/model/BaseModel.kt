package com.victory.basemodule.network.model

import com.google.gson.Gson
import com.victory.basemodule.network.bean.BaseBean
import com.victory.basemodule.network.httphelper.NetWorkRequestHelper
import com.victory.basemodule.network.httphelper.NetWorkRequestServer

import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.RequestBody

//import rx.Observable;

/**
 * @Author： Victory
 * @Time： 2018/12/10
 * @QQ： 949021037
 * @Explain： com.test.networkrequestmodule.model
 */
class BaseModel<T>(tClass: Class<T>) {

    internal var netRequestService: NetWorkRequestServer<T> ?=null
    internal var netWorkRequestHelper:NetWorkRequestHelper<T> ?=null
    init {
        netWorkRequestHelper= NetWorkRequestHelper()
        netRequestService = netWorkRequestHelper!!.getNetWorkRequestServer(tClass) as NetWorkRequestServer<T>
    }

    /**
     * post表单请求,返回object对象
     *
     * @param url
     * @param parme
     * @return
     */
    fun postFormRegisterObject(url: String, parme: Map<String, Any>): Observable<Any> {
        return netRequestService!!.postRequestFormObject(url, parme)
    }

    /**
     * post表单请求,返回实体对象
     *
     * @param url
     * @param parme
     * @return
     */
    fun postFormRegisterBean(url: String, parme: Map<String, Any>): Observable<BaseBean<T>> {
        return netRequestService!!.postRequestFormBean(url, parme)
    }

    /**
     * postJSON请求，返回Objet
     * @param url
     * @param parme
     * @return
     */
    fun postRequestJsonObject(url: String, parme: Map<String, Any>): Observable<Any> {
        val gson = Gson()
        val requestBody = RequestBody.create(MediaType.parse("application/json"), gson.toJson(parme))
        return netRequestService!!.postRequestJsonObject(url, requestBody)
    }

}
