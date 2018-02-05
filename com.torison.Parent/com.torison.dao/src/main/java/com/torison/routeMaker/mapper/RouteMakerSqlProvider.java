package com.torison.routeMaker.mapper;

import com.torison.routeMaker.model.RouteMaker;
import org.apache.ibatis.jdbc.SQL;

public class RouteMakerSqlProvider {

    public String insertSelective(RouteMaker record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("ROUTE_MAKER");
        
        if (record.getUserid() != null) {
            sql.VALUES("userId", "#{userid,jdbcType=INTEGER}");
        }
        
        if (record.getIntroduce() != null) {
            sql.VALUES("introduce", "#{introduce,jdbcType=VARCHAR}");
        }
        
        if (record.getSenioritypic() != null) {
            sql.VALUES("seniorityPic", "#{senioritypic,jdbcType=VARCHAR}");
        }
        
        if (record.getSenioritypic2() != null) {
            sql.VALUES("seniorityPic2", "#{senioritypic2,jdbcType=VARCHAR}");
        }
        
        if (record.getIsagency() != null) {
            sql.VALUES("isAgency", "#{isagency,jdbcType=TINYINT}");
        }
        
        if (record.getSeniorityyet() != null) {
            sql.VALUES("seniorityYet", "#{seniorityyet,jdbcType=TINYINT}");
        }
        
        return sql.toString();
    }
}