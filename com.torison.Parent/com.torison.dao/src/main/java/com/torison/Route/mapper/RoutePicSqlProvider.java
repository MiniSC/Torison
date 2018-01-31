package com.torison.Route.mapper;

import com.torison.Route.model.RoutePic;
import org.apache.ibatis.jdbc.SQL;

public class RoutePicSqlProvider {

    public String insertSelective(RoutePic record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("ROUTE_PIC");
        
        if (record.getRouteid() != null) {
            sql.VALUES("routeID", "#{routeid,jdbcType=INTEGER}");
        }
        
        if (record.getRoutepic1() != null) {
            sql.VALUES("routePic1", "#{routepic1,jdbcType=VARCHAR}");
        }
        
        if (record.getRoutepic2() != null) {
            sql.VALUES("routePic2", "#{routepic2,jdbcType=VARCHAR}");
        }
        
        if (record.getRoutepic3() != null) {
            sql.VALUES("routePic3", "#{routepic3,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(RoutePic record) {
        SQL sql = new SQL();
        sql.UPDATE("ROUTE_PIC");
        
        if (record.getRoutepic1() != null) {
            sql.SET("routePic1 = #{routepic1,jdbcType=VARCHAR}");
        }
        
        if (record.getRoutepic2() != null) {
            sql.SET("routePic2 = #{routepic2,jdbcType=VARCHAR}");
        }
        
        if (record.getRoutepic3() != null) {
            sql.SET("routePic3 = #{routepic3,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("routeID = #{routeid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}