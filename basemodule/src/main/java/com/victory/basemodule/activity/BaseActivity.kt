package com.victory.basemodule.activity

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.victory.basemodule.application.BaseApplication
import com.victory.basemodule.tools.ToastUtil

/**
 * @author  Victory
 * @date 2019/5/24
 * BaseActivity :
 * 用户初始化各种第三方控件等
 */

open class BaseActivity : AppCompatActivity() {
    //获取全局上下文
    lateinit var mContext: Context
    //
    lateinit var toastUtil: ToastUtil

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext=BaseApplication.baseApplication.applicationContext;
        toastUtil= ToastUtil.getToastUtilInstance(mContext);



    }
}