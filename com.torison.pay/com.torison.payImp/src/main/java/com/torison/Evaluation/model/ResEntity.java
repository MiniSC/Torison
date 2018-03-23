package com.torison.Evaluation.model;

import java.io.Serializable;

/**
 * ====================
 * @author dongjj
 * ResponseEntity
 * Date : 2018/3/5
 * Time : 14.45
 * ====================
 * @param <T>
 */
public class ResEntity<T> implements Serializable {

    private static final long serialVersionUID = -565833265243056011L;
    private T data;
    private String resMsg;
    private String resCode;
    private boolean Success;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }
}

