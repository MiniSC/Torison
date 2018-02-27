package com.torison.Order.mapper;

import com.torison.Order.model.Order;
import org.apache.ibatis.jdbc.SQL;

public class OrderSqlProvider {

    public String insertSelective(Order record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("orders");
        
        if (record.getOrderid() != null) {
            sql.VALUES("OrderID", "#{orderid,jdbcType=INTEGER}");
        }
        
        if (record.getUserid() != null) {
            sql.VALUES("UserID", "#{userid,jdbcType=INTEGER}");
        }
        
        if (record.getRouteid() != null) {
            sql.VALUES("RouteID", "#{routeid,jdbcType=INTEGER}");
        }
        
        if (record.getNum() != null) {
            sql.VALUES("Num", "#{num,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("Status", "#{status,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Order record) {
        SQL sql = new SQL();
        sql.UPDATE("orders");
        
        if (record.getUserid() != null) {
            sql.SET("UserID = #{userid,jdbcType=INTEGER}");
        }
        
        if (record.getRouteid() != null) {
            sql.SET("RouteID = #{routeid,jdbcType=INTEGER}");
        }
        
        if (record.getNum() != null) {
            sql.SET("Num = #{num,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("Status = #{status,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("OrderID = #{orderid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}