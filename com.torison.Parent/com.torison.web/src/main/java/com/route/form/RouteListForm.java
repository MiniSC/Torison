package com.route.form;

import com.common.model.Form;

/**
 * 路线Adapter所用实体类
 * @Author dongjj
 */
public class RouteListForm extends Form {

    private int routeID;
    private String routename;
    private String pic;
    private String routefrom;
    private String routeend;
    private String statusMsg;
    private Double allmoney;

    public Double getAllmoney() {
        return allmoney;
    }

    public void setAllmoney(Double allmoney) {
        this.allmoney = allmoney;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public int getRouteID() {
        return routeID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public String getRoutename() {
        return routename;
    }

    public void setRoutename(String routename) {
        this.routename = routename;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getRoutefrom() {
        return routefrom;
    }

    public void setRoutefrom(String routefrom) {
        this.routefrom = routefrom;
    }

    public String getRouteend() {
        return routeend;
    }

    public void setRouteend(String routeend) {
        this.routeend = routeend;
    }
}
