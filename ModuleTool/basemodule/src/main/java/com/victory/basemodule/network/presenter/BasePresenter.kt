package com.victory.basemodule.network.presenter


import com.victory.basemodule.network.view.BaseView
import com.victory.basemodule.tools.LogUtil

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

/**
 * @Author： Victory
 * @Time： 2018/12/10
 * @QQ： 949021037
 * @Explain： com.test.networkrequestmodule.presenter
 */
class BasePresenter<T> : BasePresenterInterface<T> {

    //    private CompositeSubscripti

    private val compositeDisposable: CompositeDisposable? = null

    /**
     * 获取BaseView
     */
    private var baseView: BaseView<T>? = null
    /**
     * 日志工具
     */
    private var mLogUtil: LogUtil? = null

    /**
     * 获取打印日志类
     *
     * @return
     */
    private val logUtil: LogUtil
        get() {
            if (mLogUtil == null) {
                mLogUtil = LogUtil.getLogUtil("victory")
            }
            return mLogUtil!!
        }

    /**
     * 请求开始
     *
     * @param view
     */
    override fun onRequestStart(view: BaseView<T>) {
        baseView = view
    }

    /**
     * 请求中
     */
    override fun onRequesting() {

    }

    /**
     * 请求失败
     */
    override fun onRequestStop() {
        compositeDisposable!!.dispose()
    }


    /**
     * 请求返回非正常实体类型，返回对象
     *
     * @param objectObservable
     */
    fun getObjectData(compositeDisposable: CompositeDisposable, objectObservable: Observable<T>) {
        objectObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .retry()
                .subscribe(object : Observer<T> {
                    override fun onNext(o: T) {
                        baseView!!.requestSuccess(o)
                    }

                    override fun onComplete() {
                        compositeDisposable.dispose()
                    }

                    override fun onError(e: Throwable) {
                        requestErrorException(e)
                        compositeDisposable.dispose()
                    }

                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }
                })
    }

    /**
     * 请求错误统一处理
     */
    fun requestErrorException(e: Throwable) {
        try {
            if (e is SocketTimeoutException) {
                //请求超时
            } else if (e is ConnectException) {
                //网络连接超时
            } else if (e is SSLHandshakeException) {
                //证书错误

            } else if (e is HttpException) {
                //请求地址错误
                val code = e.code();
                if (code == 504) {
                    //网络异常 请检查网络状态
                } else if (code == 404) {
                    //请求地址不存在
                } else {
                    //请求失败
                }
            } else if (e is UnknownHostException) {
                //域名错误
            } else {
                //其他错误
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            logUtil.getLogE(e.message!!)
        }
    }

}