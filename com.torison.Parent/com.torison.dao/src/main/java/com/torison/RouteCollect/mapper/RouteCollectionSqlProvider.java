package com.torison.RouteCollect.mapper;

import com.torison.RouteCollect.model.RouteCollection;
import org.apache.ibatis.jdbc.SQL;

public class RouteCollectionSqlProvider {

    public String insertSelective(RouteCollection record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("route_collection");
        
        if (record.getUserid() != null) {
            sql.VALUES("UserID", "#{userid,jdbcType=INTEGER}");
        }
        
        if (record.getRouteid() != null) {
            sql.VALUES("routeID", "#{routeid,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(RouteCollection record) {
        SQL sql = new SQL();
        sql.UPDATE("route_collection");
        
        if (record.getRouteid() != null) {
            sql.SET("routeID = #{routeid,jdbcType=INTEGER}");
        }
        
        sql.WHERE("UserID = #{userid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}