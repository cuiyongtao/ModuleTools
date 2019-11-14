package com.victory.basemodule.download

import android.Manifest
import android.os.Environment
import android.util.Log
import com.victory.basemodule.constant.BaseConstant
import com.victory.basemodule.tools.PermissionUtil

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

/**
 * @author Victory
 * @date 2019/6/19
 * DownLoaderHelper :
 */
class DownLoaderHelper {

    private var okHttpClient: OkHttpClient? = null
    private var request: Request? = null

    private fun getOkHttpClient(): OkHttpClient {
        okHttpClient = OkHttpClient.Builder().build()
        return okHttpClient!!
    }

    private fun getRequest(url: String): Request {
        request = Request.Builder().url(url).build()
        return request!!
    }

    /**
     * 默认下载事件
     *
     * @param downloadurl
     * @param downLoadStatesInterface
     */
    fun downloaderFile(downloadurl: String, downLoadStatesInterface: DownLoadStatesInterface?) {

        if (PermissionUtil.isHasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Log.e(BaseConstant.CommonTAG,"true")
        }else{
            PermissionUtil.getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            Log.e(BaseConstant.CommonTAG,"false")
            return
        }

        getOkHttpClient().newCall(getRequest(downloadurl)).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                downLoadStatesInterface?.error(e.toString())
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                var inputStream: InputStream? = null
                val buf = ByteArray(2048)
                var len = 0
                var fos: FileOutputStream? = null
                // 储存下载文件的目录
                val dir = File(Environment.getExternalStorageDirectory().toString() + "")
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                val file = File(dir, "12345.apk")
                try {
                    inputStream = response.body()!!.byteStream()
                    val total = response.body()!!.contentLength()
                    fos = FileOutputStream(file)
                    var sum: Long = 0
                    len = (inputStream!!.read(buf))
                    while (len != -1) {
                        fos.write(buf, 0, len)
                        sum += len.toLong()
                        val progress = (sum * 1.0f / total * 100).toInt()
                        // 下载中更新进度条
                        downLoadStatesInterface!!.loading(progress)
                    }
                    fos.flush()
                    // 下载完成
                    downLoadStatesInterface!!.success(true)
                } catch (e: Exception) {
                    downLoadStatesInterface!!.error(e.toString())
                } finally {
                    try {
                        inputStream?.close()
                    } catch (e: IOException) {

                    }

                    try {
                        fos?.close()
                    } catch (e: IOException) {
                    }

                }
            }
        })
    }

    /**
     * 自定义下载事件
     *
     * @param downloadurl
     * @param filepath
     * @param filename
     * @param downLoadStatesInterface
     */
    fun downloaderFile(downloadurl: String, filepath: String, filename: String, downLoadStatesInterface: DownLoadStatesInterface?) {

        getOkHttpClient().newCall(getRequest(downloadurl)).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                downLoadStatesInterface?.error(e.toString())
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                var inputStream: InputStream? = null
                val buf = ByteArray(2048)
                var len = 0
                var fos: FileOutputStream? = null
                // 储存下载文件的目录
                val dir = File(filepath)
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                val file = File(dir, filename)
                try {
                    inputStream = response.body()!!.byteStream()
                    val total = response.body()!!.contentLength()
                    fos = FileOutputStream(file)
                    var sum: Long = 0
                    len = inputStream!!.read(buf)
                    while (len != -1) {
                        fos.write(buf, 0, len)
                        sum += len.toLong()
                        val progress = (sum * 1.0f / total * 100).toInt()
                        // 下载中更新进度条
                        downLoadStatesInterface!!.loading(progress)
                    }
                    fos.flush()
                    // 下载完成
                    downLoadStatesInterface!!.success(true)
                } catch (e: Exception) {
                    downLoadStatesInterface!!.error(e.toString())
                } finally {
                    try {
                        inputStream?.close()
                    } catch (e: IOException) {

                    }

                    try {
                        fos?.close()
                    } catch (e: IOException) {
                    }

                }
            }
        })
    }

    companion object {
        private var mDownLoaderHelper: DownLoaderHelper? = null

        val downLoaderHelper: DownLoaderHelper
            get() {
                if (mDownLoaderHelper == null) {
                    mDownLoaderHelper = DownLoaderHelper()
                }
                return mDownLoaderHelper!!
            }
    }

}
