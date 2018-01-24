package com.torison.model;

public class RoutePic {
    private Integer routeid;

    private String routepic1;

    private String routepic2;

    private String routepic3;

    private String routepic4;

    private String routepic5;

    public Integer getRouteid() {
        return routeid;
    }

    public void setRouteid(Integer routeid) {
        this.routeid = routeid;
    }

    public String getRoutepic1() {
        return routepic1;
    }

    public void setRoutepic1(String routepic1) {
        this.routepic1 = routepic1 == null ? null : routepic1.trim();
    }

    public String getRoutepic2() {
        return routepic2;
    }

    public void setRoutepic2(String routepic2) {
        this.routepic2 = routepic2 == null ? null : routepic2.trim();
    }

    public String getRoutepic3() {
        return routepic3;
    }

    public void setRoutepic3(String routepic3) {
        this.routepic3 = routepic3 == null ? null : routepic3.trim();
    }

    public String getRoutepic4() {
        return routepic4;
    }

    public void setRoutepic4(String routepic4) {
        this.routepic4 = routepic4 == null ? null : routepic4.trim();
    }

    public String getRoutepic5() {
        return routepic5;
    }

    public void setRoutepic5(String routepic5) {
        this.routepic5 = routepic5 == null ? null : routepic5.trim();
    }
}