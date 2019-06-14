package com.victory.basemodule.network.presenter


import com.victory.basemodule.network.view.BaseView
import com.victory.basemodule.tools.LogUtil

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * @Author： Victory
 * @Time： 2018/12/10
 * @QQ： 949021037
 * @Explain： com.test.networkrequestmodule.presenter
 */
class BasePresenter : BasePresenterInterface {

    //    private CompositeSubscripti

    private val compositeDisposable: CompositeDisposable? = null

    /**
     * 获取BaseView
     */
    private var baseView: BaseView? = null
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
    override fun onRequestStart(view: BaseView) {
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
    fun getObjectData(compositeDisposable: CompositeDisposable, objectObservable: Observable<Any>) {
        objectObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .retry()
                .subscribe(object : Observer<Any> {
                    override fun onNext(o: Any) {
                        baseView!!.requestSuccess(o)
                    }

                    override fun onComplete() {
                        compositeDisposable.dispose()
                    }

                    override fun onError(e: Throwable) {
                        logUtil.getLogNet("aaas", e.toString())
                        compositeDisposable.dispose()
                    }

                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }
                })
    }


}