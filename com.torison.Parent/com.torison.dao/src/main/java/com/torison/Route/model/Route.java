package com.torison.Route.model;

/**
 * @author dongjj
 * @time 18.3.22
 * route entity
 */
public class Route {

    /**
     * 路线ID
     */
    private Integer routeid;

    /**
     * 路线名称
     */
    private String routename;

    /**
     * 路线出发地
     */
    private String routefromaddress;

    /**
     * 路线到达地
     */
    private String routeendaddress;

    /**
     * 路线定金
     */
    private Double routeneedmoney;

    /**
     * 路线介绍
     */
    private String routeintroduce;

    /**
     * 制作人ID
     */
    private String routefromid;

    /**
     * 最大可容人数
     */
    private Integer routemaxpersonnum;

    /**
     * 剩余可定人数
     */
    private Integer routelastpersonnum;

    /**
     * 出发时间
     */
    private String deposite;

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

    public String getDeposite() {
        return deposite;
    }

    public void setDeposite(String deposite) {
        this.deposite = deposite;
    }
}