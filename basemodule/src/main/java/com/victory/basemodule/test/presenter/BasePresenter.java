package com.victory.basemodule.test.presenter;



import com.victory.basemodule.test.view.BaseView;
import com.victory.basemodule.tools.LogUtil;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @Author： Victory
 * @Time： 2018/12/10
 * @QQ： 949021037
 * @Explain： com.test.networkrequestmodule.presenter
 */
public class BasePresenter implements BasePresenterInterface {
    /**
     * 获取当前实列
     */
    private static BasePresenter mBasePresenterInstance;
    /**
     * 获取BaseView
     */
    private BaseView baseView;
    /**
     * 获取网络请求订阅
     */
    private CompositeSubscription mCompositeSubscription;
    /**
     * 日志工具
     */
    private LogUtil mLogUtil;

    /**
     * 单例报证该类只会被创建一次
     *
     * @return
     */
    public static BasePresenter getBasePresenterInstance() {
        if (mBasePresenterInstance == null) {
            mBasePresenterInstance = new BasePresenter();
        }
        return mBasePresenterInstance;
    }

    /**
     * 获取订阅实例
     *
     * @return
     */
    private CompositeSubscription getCompositeSubscription() {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        return mCompositeSubscription;
    }

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
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    /**
     * 请求返回非正常实体类型，返回对象
     *
     * @param objectObservable
     */
    public void getObjectData(Observable<Object> objectObservable) {
        getCompositeSubscription().add(
                objectObservable
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Object>() {
                            @Override
                            public void onNext(Object o) {
                                baseView.requestSuccess(o);
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onCompleted() {
                            }
                        }));
    }

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
//                                getLogUtil().printLogE(getBaseConstant().TAGNETE, e.toString());
//                            }
//
//                            @Override
//                            public void onCompleted() {
//                                getLogUtil().printLogD(getBaseConstant().TAGNETD, "onCompleted()");
//                            }
//                        }));
//    }

}