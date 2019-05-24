package com.victory.basemodule.tools

import android.content.Context
import android.content.SharedPreferences
import com.victory.basemodule.constant.BaseConstant

/**
 * @author  Victory
 * @date 2019/5/24
 * SPUtils : 存储相关
 */

class SPUtils {
    companion object {
        var spUtils: SPUtils? = null
        lateinit var mContext: Context

        fun getSPUtils(context: Context): SPUtils {
            if (spUtils == null) {
                spUtils = SPUtils()
            }
            mContext = context;
            return spUtils!!
        }
    }

    /**
     * 存储
     *
     * @return
     */
    fun getSelfData(): SharedPreferences {
        return mContext.getSharedPreferences(BaseConstant.spName, Context.MODE_PRIVATE)
    }

    /**
     * 存储
     *
     * @return
     */
    fun getSelfDataEditor(): SharedPreferences.Editor {
        return getSelfData().edit()
    }

    /**
     * 存储字符串
     */
    fun putSPString(key: String, value: String) {
        val editor = getSelfDataEditor();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 获取字符串
     */
    fun getSPString(key: String): String {
        return getSelfData().getString(key, "");
    }

    /**
     * 存储bool值
     */
    fun putSPBoolean(key: String, value: Boolean) {
        val editor = getSelfDataEditor();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 获取bool值
     */
    fun getSPBoolean(key: String): Boolean {
        return getSelfData().getBoolean(key, false);
    }


    /**
     * 存储整数
     */
    fun putSPInt(key: String, value: String) {
        val editor = getSelfDataEditor();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 获取整数
     */
    fun getSPInt(key: String): String {
        return getSelfData().getString(key, "");
    }


    /**
     * 存储小数
     */
    fun putSPFlout(key: String, value: Float) {
        val editor = getSelfDataEditor();
        editor.putFloat(key, value);
        editor.commit();
    }

    /**
     * 获取小数
     */
    fun getSPFlout(key: String): Float {
        return getSelfData().getFloat(key, -1f);
    }

    /**
     * 存储长整型
     */
    fun putSPLong(key: String, value: Long) {
        val editor = getSelfDataEditor();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 获取长整型
     */
    fun getSPLong(key: String): Long {
        return getSelfData().getLong(key, -1);
    }
}
