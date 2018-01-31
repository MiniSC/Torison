package com.torison.Route.mapper;

import com.torison.Route.model.Route;
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

    public String updateByIDSelective(Route record) {
        SQL sql = new SQL();
        sql.UPDATE("ROUTE");

        if (record.getRouteid() != null) {
          sql.SET("routeID = #{routeid,jdbcType=INTEGER}");
        }
        if (record.getRoutename() != null) {
            sql.SET("routeName = #{routename,jdbcType=VARCHAR}");
        }

        if (record.getRoutefromaddress() != null) {
            sql.SET("routeFromAddress = #{routefromaddress,jdbcType=VARCHAR}");
        }

        if (record.getRouteendaddress() != null) {
            sql.SET("routeEndAddress = #{routeendaddress,jdbcType=VARCHAR}");
        }

        if (record.getRouteneedmoney() != null) {
            sql.SET("routeNeedMoney = #{routeneedmoney,jdbcType=VARCHAR}");
        }

        if (record.getRouteintroduce() != null) {
            sql.SET("routeIntroduc = #{routeintroduce,jdbcType=VARCHAR");
        }

        if (record.getRoutefromid() != null) {
            sql.SET("routeFromId = #{routefromid,jdbcType=VARCHAR}");
        }

        if (record.getRoutemaxpersonnum() != null) {
            sql.SET("routeMaxPersonNum = #{routemaxpersonnum,jdbcType=VARCHAR");
        }

        if (record.getRoutelastpersonnum() != null) {
            sql.SET("routeLastPersonNum = #{routelastpersonnum,jdbcType=VARCHAR}");
        }

        if (record.getDeposite() != null) {
            sql.SET("deposite = #{deposite,jdbcType=DOUBLE}");
        }

        sql.WHERE("routeID = #{routeid,jdbcType=INTEGER}");

        return sql.toString();
    }
}