package com.torison.routeMaker.mapper;

import com.torison.routeMaker.model.RouteMaker;
import org.apache.ibatis.jdbc.SQL;

public class RouteMakerSqlProvider {

    public String insertSelective(RouteMaker record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("route_maker");
        
        if (record.getUserid() != null) {
            sql.VALUES("UserID", "#{userid,jdbcType=INTEGER}");
        }
        
        if (record.getPic1() != null) {
            sql.VALUES("Pic1", "#{pic1,jdbcType=VARCHAR}");
        }
        
        if (record.getPic2() != null) {
            sql.VALUES("Pic2", "#{pic2,jdbcType=VARCHAR}");
        }
        
        if (record.getIntroduce() != null) {
            sql.VALUES("Introduce", "#{introduce,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(RouteMaker record) {
        SQL sql = new SQL();
        sql.UPDATE("route_maker");
        
        if (record.getPic1() != null) {
            sql.SET("Pic1 = #{pic1,jdbcType=VARCHAR}");
        }
        
        if (record.getPic2() != null) {
            sql.SET("Pic2 = #{pic2,jdbcType=VARCHAR}");
        }
        
        if (record.getIntroduce() != null) {
            sql.SET("Introduce = #{introduce,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("UserID = #{userid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}