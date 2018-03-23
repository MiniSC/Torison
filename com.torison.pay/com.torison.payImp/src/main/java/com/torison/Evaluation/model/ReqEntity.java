package com.torison.Evaluation.model;

import java.io.Serializable;

/**
 * ========================
 * @Author dongjj
 * RequestEntity
 * Date: 2018/3/5
 * Time: 14.43
 * @param <T>
 * ========================
 */
public class ReqEntity<T> implements Serializable {

    private static final long serialVersionUID = 4107633940436476200L;
    private T data;
    private String status;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
