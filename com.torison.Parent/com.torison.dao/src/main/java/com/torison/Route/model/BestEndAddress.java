package com.torison.Route.model;

public class BestEndAddress {

    String routeendaddress;
    String num;

    public String getRouteendaddress() {
        return routeendaddress;
    }

    public void setRouteendaddress(String routeendaddress) {
        this.routeendaddress = routeendaddress;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "BestEndAddress{" +
                "routeendaddress='" + routeendaddress + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}
