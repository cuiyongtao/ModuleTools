package com.victory.basemodule.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.victory.basemodule.application.BaseApplication
import com.victory.basemodule.constant.BaseConstant
import com.victory.basemodule.network.presenter.BasePresenter
import com.victory.basemodule.network.view.BaseView
import com.victory.basemodule.tools.*
import io.reactivex.disposables.CompositeDisposable

/**
 * @author  Victory
 * @date 2019/5/24
 * BaseActivity :
 * 用户初始化各种第三方控件等
 */

open class BaseActivity<T> : AppCompatActivity() {
    //获取全局上下文
    lateinit var mContext: Context
    //常量类
    lateinit var baseConstant: BaseConstant
    //吐司工具
    lateinit var toastUtil: ToastUtil
    //日志工具类
    lateinit var logUtil: LogUtil
    var TAG = ""
    //常用工具
    lateinit var constantUtils: ConstantUtils
    //缓存工具
    lateinit var spUtils: SPUtils
    //
    lateinit var titleBar: TitleBar
    //顶部导航栏样式  0=普通样式；1搜索样式；2主页样式
    var myTitleStyle = 0;
    var myTitleStyleSearch = 1;
    var myTitleStyleMain = 2;
    //统一管理rxjava 便于解绑rxjava防止内存泄漏
    lateinit var compositeDisposable: CompositeDisposable
    //网络管理
    lateinit var baseView: BaseView<T>
    lateinit var basePresenter: BasePresenter<T>
    //权限工具类
    lateinit var permissionUtil: PermissionUtil


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = BaseApplication.baseApplication.applicationContext;
        initUtils()
    }

    fun initUtils() {
        baseConstant = BaseConstant.getBaseConstantInstance()
        toastUtil = ToastUtil.getToastUtilInstance(mContext);
        TAG = BaseConstant.CommonTAG + getComponentName().getShortClassName();
        logUtil = LogUtil.getLogUtil(TAG)
        constantUtils = ConstantUtils.getConstantUtils(mContext)
        spUtils = SPUtils()
        permissionUtil = PermissionUtil.getPermissionUtil(this)
        TitleBar.getStyle(myTitleStyleMain)
        TitleBar.setIconLeftInterFace(object : TitleBar.IconLeftInterface {
            override fun iconLeftOnclick() {
                finish()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}