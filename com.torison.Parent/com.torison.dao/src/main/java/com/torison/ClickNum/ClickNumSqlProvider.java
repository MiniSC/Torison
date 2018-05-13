package com.torison.ClickNum;

import org.apache.ibatis.jdbc.SQL;

public class ClickNumSqlProvider {

    public String insertSelective(ClickNum record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("click_num");
        
        if (record.getRouteid() != null) {
            sql.VALUES("routeId", "#{routeid,jdbcType=INTEGER}");
        }
        
        if (record.getTimes() != null) {
            sql.VALUES("times", "#{times,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ClickNum record) {
        SQL sql = new SQL();
        sql.UPDATE("click_num");
        
        if (record.getTimes() != null) {
            sql.SET("times = #{times,jdbcType=INTEGER}");
        }
        
        sql.WHERE("routeId = #{routeid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}