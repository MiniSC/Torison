package com.torison.model;

public class Route {
    private Integer routeid;

    private String routename;

    private String routefromaddress;

    private String routeendaddress;

    private Double routeneedmoney;

    private String routeintroduce;

    private String routefromid;

    private Integer routemaxpersonnum;

    private Integer routelastpersonnum;

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