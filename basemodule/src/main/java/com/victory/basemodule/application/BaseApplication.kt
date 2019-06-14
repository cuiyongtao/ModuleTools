package com.victory.basemodule.application

import android.app.Application
import android.content.Context

/**
 * @author Victory
 * @date 2019/5/24
 * BaseApplication : 程序入口
 * app程序启动时调用，用于当前module集成的三方库初始化和全局上下文的获取
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    /**
     * 初始化
     */
    private fun init() {
        baseApplication = this
        mContext = applicationContext
    }

    /**
     * 获取全局上下文
     */
    override fun getApplicationContext(): Context {
        return super.getApplicationContext()
    }

    /**
     * 设置application单例
     */
    companion object {
        lateinit var mContext: Context
        lateinit var baseApplication: BaseApplication
            private set
    }
}
