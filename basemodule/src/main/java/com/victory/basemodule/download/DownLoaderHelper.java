package com.victory.basemodule.download;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Victory
 * @date 2019/6/19
 * DownLoaderHelper :
 */
public class DownLoaderHelper {

    private OkHttpClient okHttpClient;
    private static DownLoaderHelper mDownLoaderHelper;
    private Request request;


    public static DownLoaderHelper getDownLoaderHelper() {
        if (mDownLoaderHelper == null) {
            mDownLoaderHelper = new DownLoaderHelper();
        }
        return mDownLoaderHelper;
    }

    private OkHttpClient getOkHttpClient() {
        okHttpClient = new OkHttpClient.Builder().build();
        return okHttpClient;
    }

    private Request getRequest(String url) {
        request = new Request.Builder().url(url).build();
        return request;
    }

    /**
     * 默认下载事件
     * @param downloadurl
     * @param downLoadStatesInterface
     */
    public void downloaderFile(String downloadurl, final DownLoadStatesInterface downLoadStatesInterface) {
        getOkHttpClient().newCall(getRequest(downloadurl)).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (downLoadStatesInterface != null) {
                    downLoadStatesInterface.error(e.toString());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                File dir = new File(Environment.getExternalStorageDirectory() + "");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, "12345.apk");
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中更新进度条
                        downLoadStatesInterface.loading(progress);
                    }
                    fos.flush();
                    // 下载完成
                    downLoadStatesInterface.success(true);
                } catch (Exception e) {
                    downLoadStatesInterface.error(e.toString());
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                    } catch (IOException e) {

                    }
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    /**
     * 自定义下载事件
     * @param downloadurl
     * @param filepath
     * @param filename
     * @param downLoadStatesInterface
     */
    public void downloaderFile(String downloadurl, final String filepath, final String filename, final DownLoadStatesInterface downLoadStatesInterface) {
        getOkHttpClient().newCall(getRequest(downloadurl)).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (downLoadStatesInterface != null) {
                    downLoadStatesInterface.error(e.toString());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                File dir = new File(filepath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, filename);
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中更新进度条
                        downLoadStatesInterface.loading(progress);
                    }
                    fos.flush();
                    // 下载完成
                    downLoadStatesInterface.success(true);
                } catch (Exception e) {
                    downLoadStatesInterface.error(e.toString());
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                    } catch (IOException e) {

                    }
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

}
