package back.Route.dao.model;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;

import javax.persistence.*;
import javax.websocket.ClientEndpoint;

@Entity
@Table(name = "route")
public class Route {

    @Column(name = "routeid")
    @GeneratedValue
    @Id
    private Integer routeId;

    @Column(name = "routename")
    private String routeName;

    @Column(name = "routefromaddress")
    private String routeFromAddress;

    @Column(name = "routeendaddress")
    private String routeEndAddress;

    @Column(name = "routeneedmoney")
    private Double routeNeedMoney;

    @Column(name = "routeintroduce")
    private String routeIntroduce;

    @Column(name = "routefromid")
    private String routeFromId;

    @Column(name = "routemaxpersonnum")
    private String routeMaxPersonNum;

    @Column(name = "routelastpersonnum")
    private String routeLastPersonNum;

    @Column(name = "deposite")
    private String deposite;

    @Column(name = "deadline")
    private String deadLine;

    @Column(name = "status")
    private Integer status;

    @Column(name = "chatconsult")
    private String chatconsult;

    @Column(name = "conditionoverleaf")
    private String conditionoverleaf;

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
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
        this.chatconsult = chatconsult;
    }

    public String getConditionoverleaf() {
        return conditionoverleaf;
    }

    public void setConditionoverleaf(String conditionoverleaf) {
        this.conditionoverleaf = conditionoverleaf;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteFromAddress() {
        return routeFromAddress;
    }

    public void setRouteFromAddress(String routeFromAddress) {
        this.routeFromAddress = routeFromAddress;
    }

    public String getRouteEndAddress() {
        return routeEndAddress;
    }

    public void setRouteEndAddress(String routeEndAddress) {
        this.routeEndAddress = routeEndAddress;
    }

    public Double getRouteNeedMoney() {
        return routeNeedMoney;
    }

    public void setRouteNeedMoney(Double routeNeedMoney) {
        this.routeNeedMoney = routeNeedMoney;
    }

    public String getRouteIntroduce() {
        return routeIntroduce;
    }

    public void setRouteIntroduce(String routeIntroduce) {
        this.routeIntroduce = routeIntroduce;
    }

    public String getRouteFromId() {
        return routeFromId;
    }

    public void setRouteFromId(String routeFromId) {
        this.routeFromId = routeFromId;
    }

    public String getRouteMaxPersonNum() {
        return routeMaxPersonNum;
    }

    public void setRouteMaxPersonNum(String routeMaxPersonNum) {
        this.routeMaxPersonNum = routeMaxPersonNum;
    }

    public String getRouteLastPersonNum() {
        return routeLastPersonNum;
    }

    public void setRouteLastPersonNum(String routeLastPersonNum) {
        this.routeLastPersonNum = routeLastPersonNum;
    }

    public String getDeposite() {
        return deposite;
    }

    public void setDeposite(String deposite) {
        this.deposite = deposite;
    }
}
