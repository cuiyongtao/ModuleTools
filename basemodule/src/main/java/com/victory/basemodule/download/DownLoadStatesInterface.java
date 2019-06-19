package com.victory.basemodule.download;

/**
 * @author Victory
 * @date 2019/6/19
 * DownLoadStatesInterface :
 */
public interface DownLoadStatesInterface {
    /**
     * 下载成功
     */
    public void success(Boolean b);

    /**
     * 下载失败
     */
    public void error(String errormsg);

    /**
     * 下载中
     */
    public void loading(int schedule);

}
