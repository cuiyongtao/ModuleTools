package com.victory.basemodule.test.bean;

/**
 * @Author： Victory
 * @Time： 2018/12/11
 * @QQ： 949021037
 * @Explain： com.test.networkrequestmodule.bean
 */
public class BaseBean<T> {
    private int errorCode;
    private String errorMsg;
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
