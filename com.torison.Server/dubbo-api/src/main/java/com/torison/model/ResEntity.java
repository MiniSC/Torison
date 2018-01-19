package com.torison.model;

import java.io.Serializable;

/**
 * 返回实体类
 * return entity
 * @author dongjj
 * @createtime 1.19
 */
public class ResEntity<T> implements Serializable {

    private static final long serialVersionUID = -1635802854189374967L;
    private String respMsg;
    private String respCode;
    private T data;

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
