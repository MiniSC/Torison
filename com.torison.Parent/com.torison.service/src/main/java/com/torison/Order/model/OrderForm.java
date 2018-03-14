package com.torison.Order.model;


import com.torison.common.model.Form;

public class OrderForm extends Form {
    public OrderForm() {
    }

    /**
     *
     * @param routeID
     * @param pic
     * @param routeFrom
     * @param routeEnd
     * @param num
     * @param routeName
     */
    public OrderForm(Integer routeID, String pic, String routeFrom, String routeEnd, Integer num, String routeName) {
        this.routeID = routeID;
        this.pic = pic;
        this.routeFrom = routeFrom;
        this.routeEnd = routeEnd;
        this.num = num;
        this.routeName = routeName;
    }
    private Integer routeID;
    private String pic;
    private String routeFrom;
    private String routeEnd;
    private Integer num;
    private String routeName;

    public Integer getRouteID() {
        return routeID;
    }

    public void setRouteID(Integer routeID) {
        this.routeID = routeID;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getRouteFrom() {
        return routeFrom;
    }

    public void setRouteFrom(String routeFrom) {
        this.routeFrom = routeFrom;
    }

    public String getRouteEnd() {
        return routeEnd;
    }

    public void setRouteEnd(String routeEnd) {
        this.routeEnd = routeEnd;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }
}
