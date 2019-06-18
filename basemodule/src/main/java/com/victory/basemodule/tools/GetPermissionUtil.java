package com.victory.basemodule.tools;

import android.content.Context;

/**
 * @author Victory
 * @date 2019/6/18
 * GetPermissionUtil : 获取权限
 */
public class GetPermissionUtil {
    /**
     *  TODO 获取权限类
     */

    private static GetPermissionUtil  mGetPermissionUtil;
    private static Context mContext;

    public static GetPermissionUtil getPermissionUtil(Context context){
        if (mGetPermissionUtil==null){
            mGetPermissionUtil=new GetPermissionUtil();
        }
        mContext=context;
        return mGetPermissionUtil;
    }

    /**
     * 获取权限
     */


}
