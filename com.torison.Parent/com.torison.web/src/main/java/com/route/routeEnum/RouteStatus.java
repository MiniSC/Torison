package com.route.routeEnum;

import javax.persistence.criteria.CriteriaBuilder;

public enum RouteStatus {
        PASS(0,"审核通过"),
        REJ(1,"审核不通过"),
        WAITE(2,"等待审核"),
        STOP(3,"路线禁开"),
        DELETE(4,"路线删除");

        private Integer code;
        private String desc;

        RouteStatus(Integer code, String desc){
            this.code = code;
            this.desc = desc;
        }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
