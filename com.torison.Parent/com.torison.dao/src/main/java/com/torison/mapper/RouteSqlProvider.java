package com.torison.mapper;

import com.torison.model.Route;
import org.apache.ibatis.jdbc.SQL;

public class RouteSqlProvider {

    public String insertSelective(Route record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("ROUTE");
        
        if (record.getRouteid() != null) {
            sql.VALUES("routeID", "#{routeid,jdbcType=INTEGER}");
        }
        
        if (record.getRoutename() != null) {
            sql.VALUES("routeName", "#{routename,jdbcType=VARCHAR}");
        }
        
        if (record.getRoutefromaddress() != null) {
            sql.VALUES("routeFromAddress", "#{routefromaddress,jdbcType=VARCHAR}");
        }
        
        if (record.getRouteendaddress() != null) {
            sql.VALUES("routeEndAddress", "#{routeendaddress,jdbcType=VARCHAR}");
        }
        
        if (record.getRouteneedmoney() != null) {
            sql.VALUES("routeNeedMoney", "#{routeneedmoney,jdbcType=DOUBLE}");
        }
        
        if (record.getRouteintroduce() != null) {
            sql.VALUES("routeIntroduce", "#{routeintroduce,jdbcType=VARCHAR}");
        }
        
        if (record.getRoutefromid() != null) {
            sql.VALUES("routeFromId", "#{routefromid,jdbcType=VARCHAR}");
        }
        
        if (record.getRoutemaxpersonnum() != null) {
            sql.VALUES("routeMaxPersonNum", "#{routemaxpersonnum,jdbcType=INTEGER}");
        }
        
        if (record.getRoutelastpersonnum() != null) {
            sql.VALUES("routeLastPersonNum", "#{routelastpersonnum,jdbcType=INTEGER}");
        }
        
        if (record.getDeposite() != null) {
            sql.VALUES("deposite", "#{deposite,jdbcType=DOUBLE}");
        }
        
        return sql.toString();
    }
}