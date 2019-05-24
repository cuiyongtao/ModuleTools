package com.victory.basemodule.tools

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import android.widget.Toast
import androidx.core.content.FileProvider
import com.victory.basemodule.R
import java.io.File
import java.math.BigDecimal
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * @author  Victory
 * @date 2019/5/24
 * ConstantUtils : 常用工具类
 */

class ConstantUtils {
    companion object {
        private var constantUtils: ConstantUtils? = null
        private lateinit var mContext: Context

        fun getConstantUtils(context: Context): ConstantUtils {
            if (constantUtils == null) {
                constantUtils = ConstantUtils()
            }
            mContext = context
            return constantUtils!!
        }
    }

    /**
     * dp转px
     * @param dpValue
     */
    fun dp2px(dpValue: Double): Int {
        val scale = Resources.getSystem().getDisplayMetrics().density;
        return (dpValue * scale + 0.5f).toInt();
    }

    /**
     * px转dp
     * @param pxValue
     */
    fun px2dp(pxValue: Double): Int {
        val scale = Resources.getSystem().getDisplayMetrics().density;
        return (pxValue / scale + 0.5f).toInt();
    }

    /**
     * sp转px
     *@param spValue
     */
    fun sp2px(spValue: Double): Int {
        val scale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (spValue * scale + 0.5f).toInt();
    }

    /**
     * px转sp
     * @param pxValue
     */
    fun px2sp(pxValue: Double): Int {
        val scale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (pxValue / scale + 0.5f).toInt();
    }

    /**
     * 单位转换
     * @param size
     */
    fun getFormatSize(size: Long): String {
        val kiloByte = size / 1024
        if (kiloByte < 1) {
            return size.toString() + "BB"
        }
        val megaByte = kiloByte / 1024
        if (megaByte < 1) {
            return getBigDecimal(kiloByte) + "KB"
        }
        val gigaByte = megaByte / 1024
        if (gigaByte < 1) {
            return getBigDecimal(megaByte) + "MB"
        }
        val teraBytes = gigaByte / 1024
        return if (teraBytes < 1) {
            getBigDecimal(gigaByte) + "GB"
        } else {
            getBigDecimal(teraBytes) + "TB"
        }
    }

    /**
     * 大数据精确运算
     */
    private fun getBigDecimal(bytes: Long): String {
        return BigDecimal(bytes).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
    }

    /**
     * 获取当前app版本
     */
    fun getAppVersion(): String {
        try {
            val appVersion = mContext.packageManager.getPackageInfo(mContext.packageName, 0).versionName
            return appVersion
        } catch (e: Exception) {
            return e.toString()
        }
    }

    /**
     * 使用md5加密，例如password
     *
     * @param string
     * @return
     */
    fun takeMd5(string: String): String {
        var md5: MessageDigest? = null
        try {
            md5 = MessageDigest.getInstance("MD5")
            val bytes = md5!!.digest(string.toByteArray())
            var result = ""
            for (b in bytes) {
                var temp = Integer.toHexString(b.toInt() and 0xff)
                if (temp.length == 1) {
                    temp = "0$temp"
                }
                result += temp
            }
            return result
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            Toast.makeText(mContext, mContext.resources.getString(R.string.string_to_md5_error), Toast.LENGTH_SHORT).show()
        }
        return ""
    }

    /**
     * 查看文件夹大小
     */

    fun getFileSize(file: File): Long {
        var size: Long = 0
        try {
            val fileList = file.listFiles()
            for (i in fileList.indices) {
                if (fileList[i].isDirectory) {
                    size = size + getFileSize(fileList[i])
                } else {
                    size = size + fileList[i].length()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
          Toast.makeText(mContext, mContext.resources.getString(R.string.get_file_size_error),Toast.LENGTH_SHORT).show()
        }

        return size
    }

    /**
     * 生成文件夹
     */
    fun makeRootDirectory(filePath: String) {
        var file: File? = null
        try {
            file = File(filePath)
            //如果不存在则生成文件
            if (!file.exists()) {
                file.mkdirs()
            }
        } catch (e: Exception) {
            Toast.makeText(mContext, mContext.resources.getString(R.string.make_file_error),Toast.LENGTH_SHORT).show()
        }

    }

    /**
     * 删除文件
     * @param filePath 文件路径
     */
    fun deleteFiles(filePath: String): Boolean {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                val file = File(filePath)
                if (file.isDirectory) {
                    // 处理目录
                    val files = file.listFiles()
                    for (i in files.indices) {
                        deleteFiles(files[i].absolutePath)
                    }
                }
                if (!file.isDirectory) {
                    // 如果是文件，删除
                    file.delete()
                } else {
                    // 目录下没有文件或者目录，删除
                    if (file.listFiles().isEmpty()) {
                        file.delete()
                    }
                }
                return true
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(mContext, mContext.resources.getString(R.string.delete_file_error), Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return false
    }

    /**
     * 安装APP
     * @param filePath 文件路径
     * @param authority 7.0后读取的fileprovider
     */

    fun installApp(filePath: String, authority: String) {
        val apkFile = File(filePath)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        //7.0以上需要增加临时读取路径权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            //临时路径
            val contentUri = FileProvider.getUriForFile(mContext, authority, apkFile)
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive")
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive")
        }
        mContext.startActivity(intent)
        deleteFiles(filePath)
    }

    /**
     * 跳转到浏览器
     * @param url 链接地址
     */
    fun goBrowser(url: String) {
        val intent = Intent()
        intent.action = "android.intent.action.VIEW"
        val content_url = Uri.parse(url)
        intent.data = content_url
        mContext.startActivity(intent)
    }

}