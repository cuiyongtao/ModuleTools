package com.victory.basemodule.network.presenter;


import com.victory.basemodule.constant.BaseConstant;
import com.victory.basemodule.network.view.BaseView;
import com.victory.basemodule.tools.LogUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
    /**
     * 获取BaseView
     */
    private BaseView baseView;
    /**
     * 日志工具
     */
    private LogUtil mLogUtil;

    /**
     * 获取网络请求订阅
     */
    private Disposable mDisposable;


    /**
     * 获取打印日志类
     *
     * @return
     */
    private LogUtil getLogUtil() {
        if (mLogUtil == null) {
            mLogUtil = LogUtil.Companion.getLogUtil(BaseConstant.Companion.getCommonTAG());
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

    }

    public void getObjectData(Observable<Object> objectObservable) {
       objectObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Object>() {
                    @Override
                    public void onError(Throwable e) {
                        baseView.requestError(e.toString());
                    }

                    @Override
                    public void onNext(Object o) {
                        baseView.requestSuccess(o.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


//    /**
//     * 请求返回非正常实体类型，返回对象
//     *
//     * @param objectObservable
//     */
//    public void getObjectData(Observable<Object> objectObservable) {
//        objectObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe();
//    }

//    /**
//     * 请求返回非正常实体类型，返回实体
//     */
//    public <T> void getBeanData(Observable<BaseBean<T>> beanObservable) {
//        getCompositeSubscription().add(
//                beanObservable
//                        .subscribeOn(Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Observer<BaseBean<T>>() {
//                            @Override
//                            public void onNext(BaseBean<T> tBaseBean) {
//                                baseView.requestSuccess(tBaseBean.getData());
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                getLogUtil().getLogNet(BaseConstant.Companion.getNetWorkTAGError(), e.toString());
//                            }
//
//                            @Override
//                            public void onCompleted() {
//                                getLogUtil().getLogNet(BaseConstant.Companion.getNetWorkTAGBody(), "onCompleted()");
//                            }
//                        }));
//    }

}