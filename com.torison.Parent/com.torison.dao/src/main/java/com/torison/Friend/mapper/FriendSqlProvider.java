package com.torison.Friend.mapper;

import com.torison.Friend.model.Friend;
import org.apache.ibatis.jdbc.SQL;

public class FriendSqlProvider {

    public String insertSelective(Friend record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("friends");
        
        if (record.getUserid() != null) {
            sql.VALUES("UserID", "#{userid,jdbcType=INTEGER}");
        }
        
        if (record.getFriendid() != null) {
            sql.VALUES("FriendID", "#{friendid,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Friend record) {
        SQL sql = new SQL();
        sql.UPDATE("friends");
        
        if (record.getFriendid() != null) {
            sql.SET("FriendID = #{friendid,jdbcType=INTEGER}");
        }
        
        sql.WHERE("UserID = #{userid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}