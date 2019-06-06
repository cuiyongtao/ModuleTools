package com.victory.basemodule.constant

/**
 * @author  Victory
 * @date 2019/5/24
 * BaseConstant : 常量
 */
class BaseConstant {
    /**
     * 静态参数和方法
     */
    companion object {
        //单例
        private var baseConstant: BaseConstant? = null

        fun getBaseConstantInstance(): BaseConstant {
            if (baseConstant == null) {
                baseConstant = BaseConstant()
            }
            return baseConstant!!
        }

        /**
         * 是否是调试模式
         * 用于切换线上环境和线下环境以及日志打印
         */
        val isDebug = true
        /**
         * logd打印前缀
         */
        //打印前缀
        val CommonTAG = "Victory"
        //网络请求前缀
        open val NetWorkTAG = CommonTAG + "NetWork-"
        //网络请求设置URL
        val NetWorkTAGURL = NetWorkTAG + "URL"
        //网络请求设置参数
        public val NetWorkParam = NetWorkTAG + "Param"
        //网络请求设置返回数据
        val NetWorkTAGBody = NetWorkTAG + "Body"
        //网络请求设置错误
        val NetWorkTAGError = NetWorkTAG + "Error"
        /**
         * 列表页每页显示行数
         */
        val pageSize = 10
        /**
         * 测试服务器地址
         */
        val debugURL = "http://www.wanandroid.com/"
        /**
         * 正式库服务器地址
         */
        val baseURL = "http://www.wanandroid.com/"
        /**
         * 网络请求时间
         */
        val networkTime = 10
        /**
         * 缓存名称
         */
        val spName="VictoryData";
    }

    /**
     * 缓存统一使用下列字段作为Key
     */
}