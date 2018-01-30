package com.torison.route.model;


import com.torison.common.model.Form;

/**
 * 路线信息表单
 *
 */
public class RouteForm extends Form {

    private static final long serialVersionUID = 9082998051713549727L;

    /**
     * 路线ID
     */
    private Integer routeid;

    /**
     * 路线名称
     */
    private String routename;

    /**
     * 出发地
     */
    private String routefromaddress;

    /**
     * 目的地
     */
    private String routeendaddress;

    /**
     * 参加者预付款
     */
    private Double routeneedmoney;

    /**
     * 路线介绍
     */
    private String routeintroduce;

    /**
     * 制作者id
     */
    private String routefromid;

    /**
     * 最大参与人数
     */
    private Integer routemaxpersonnum;

    /**
     * 目前剩余可报名数量
     */
    private Integer routelastpersonnum;
    /**
     * 备用参数
     */
    private Double deposite;

    public Integer getRouteid() {
        return routeid;
    }

    public void setRouteid(Integer routeid) {
        this.routeid = routeid;
    }

    public String getRoutename() {
        return routename;
    }

    public void setRoutename(String routename) {
        this.routename = routename == null ? null : routename.trim();
    }

    public String getRoutefromaddress() {
        return routefromaddress;
    }

    public void setRoutefromaddress(String routefromaddress) {
        this.routefromaddress = routefromaddress == null ? null : routefromaddress.trim();
    }

    public String getRouteendaddress() {
        return routeendaddress;
    }

    public void setRouteendaddress(String routeendaddress) {
        this.routeendaddress = routeendaddress == null ? null : routeendaddress.trim();
    }

    public Double getRouteneedmoney() {
        return routeneedmoney;
    }

    public void setRouteneedmoney(Double routeneedmoney) {
        this.routeneedmoney = routeneedmoney;
    }

    public String getRouteintroduce() {
        return routeintroduce;
    }

    public void setRouteintroduce(String routeintroduce) {
        this.routeintroduce = routeintroduce == null ? null : routeintroduce.trim();
    }

    public String getRoutefromid() {
        return routefromid;
    }

    public void setRoutefromid(String routefromid) {
        this.routefromid = routefromid == null ? null : routefromid.trim();
    }

    public Integer getRoutemaxpersonnum() {
        return routemaxpersonnum;
    }

    public void setRoutemaxpersonnum(Integer routemaxpersonnum) {
        this.routemaxpersonnum = routemaxpersonnum;
    }

    public Integer getRoutelastpersonnum() {
        return routelastpersonnum;
    }

    public void setRoutelastpersonnum(Integer routelastpersonnum) {
        this.routelastpersonnum = routelastpersonnum;
    }

    public Double getDeposite() {
        return deposite;
    }

    public void setDeposite(Double deposite) {
        this.deposite = deposite;
    }
}
