package com.torison.Route.model;

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

    private String deposite;

    private String deadline;

    private Integer status;

    private String chatconsult;

    private String conditionoverleaf;


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
        this.deposite = deposite == null ? null : deposite.trim();
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline == null ? null : deadline.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getChatconsult() {
        return chatconsult;
    }

    public void setChatconsult(String chatconsult) {
        this.chatconsult = chatconsult == null ? null : chatconsult.trim();
    }

    public String getConditionoverleaf() {
        return conditionoverleaf;
    }

    public void setConditionoverleaf(String conditionoverleaf) {
        this.conditionoverleaf = conditionoverleaf == null ? null : conditionoverleaf.trim();
    }
}