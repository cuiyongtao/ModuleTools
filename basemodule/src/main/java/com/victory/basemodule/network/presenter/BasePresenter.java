package com.victory.basemodule.network.presenter;


import com.victory.basemodule.network.view.BaseView;
import com.victory.basemodule.tools.LogUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author： Victory
 * @Time： 2018/12/10
 * @QQ： 949021037
 * @Explain： com.test.networkrequestmodule.presenter
 */
public class BasePresenter implements BasePresenterInterface {

//    private CompositeSubscripti

    private CompositeDisposable compositeDisposable;

    /**
     * 获取BaseView
     */
    private BaseView baseView;
    /**
     * 日志工具
     */
    private LogUtil mLogUtil;

    /**
     * 获取打印日志类
     *
     * @return
     */
    private LogUtil getLogUtil() {
        if (mLogUtil == null) {
            mLogUtil = LogUtil.Companion.getLogUtil("victory");
        }
        return mLogUtil;
    }

    /**
     * 请求开始
     *
     * @param view
     */
    @Override
    public void onRequestStart(BaseView view) {
        baseView = view;
    }

    /**
     * 请求中
     */
    @Override
    public void onRequesting() {

    }

    /**
     * 请求失败
     */
    @Override
    public void onRequestStop() {
        compositeDisposable.dispose();
    }

    /**
     * 请求返回非正常实体类型，返回对象
     *
     * @param objectObservable
     */
    public void getObjectData(final CompositeDisposable compositeDisposable, Observable<Object> objectObservable) {
        objectObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onNext(Object o) {
                        baseView.requestSuccess(o);
                    }

                    @Override
                    public void onComplete() {
                        compositeDisposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getLogUtil().getLogNet("aaas", e.toString());
                        compositeDisposable.dispose();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }
                });
    }


}