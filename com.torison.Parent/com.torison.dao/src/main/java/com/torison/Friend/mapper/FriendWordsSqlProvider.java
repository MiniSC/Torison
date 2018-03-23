package com.torison.Friend.mapper;

import com.torison.Friend.model.FriendWords;
import org.apache.ibatis.jdbc.SQL;

public class FriendWordsSqlProvider {

    public String insertSelective(FriendWords record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("friend_words");
        
        if (record.getFromid() != null) {
            sql.VALUES("FromId", "#{fromid,jdbcType=INTEGER}");
        }
        
        if (record.getToid() != null) {
            sql.VALUES("ToId", "#{toid,jdbcType=INTEGER}");
        }
        
        if (record.getWords() != null) {
            sql.VALUES("words", "#{words,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(FriendWords record) {
        SQL sql = new SQL();
        sql.UPDATE("friend_words");
        
        if (record.getToid() != null) {
            sql.SET("ToId = #{toid,jdbcType=INTEGER}");
        }
        
        if (record.getWords() != null) {
            sql.SET("words = #{words,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("FromId = #{fromid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}