package com.victory.basemodule.tools

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.stream.Stream

/**
 * @author Victory
 * @date 2019/6/18
 * GetPermissionUtil : 获取权限
 */
class PermissionUtil {
    /**
     * TODO 获取权限类
     */

    companion object {
        private var mGetPermissionUtil: PermissionUtil? = null
        private lateinit var mContext: Activity

        fun getPermissionUtil(context: Context): PermissionUtil {
            if (mGetPermissionUtil == null) {
                mGetPermissionUtil = PermissionUtil()
            }
            mContext = context as Activity
            return mGetPermissionUtil!!
        }

        /**
         * 判断权限是否已获取
         */
        fun isHasPermission(permission: String): Boolean {
            var noPer = false
            if (ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_GRANTED) {
                noPer = true
            }
            return noPer
        }

        /**
         * 获取权限
         */
        fun getPermission(permission: String) {
            ActivityCompat.requestPermissions(mContext, arrayOf(permission), 1);
        }


    }
}
