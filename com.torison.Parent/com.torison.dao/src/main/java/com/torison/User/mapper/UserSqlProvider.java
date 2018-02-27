package com.torison.User.mapper;

import com.torison.User.model.User;
import org.apache.ibatis.jdbc.SQL;

public class UserSqlProvider {

    public String insertSelective(User record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user");
        
        if (record.getId() != null) {
            sql.VALUES("ID", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getAccount() != null) {
            sql.VALUES("Account", "#{account,jdbcType=VARCHAR}");
        }
        
        if (record.getUsername() != null) {
            sql.VALUES("UserName", "#{username,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.VALUES("PassWord", "#{password,jdbcType=VARCHAR}");
        }
        
        if (record.getAge() != null) {
            sql.VALUES("Age", "#{age,jdbcType=INTEGER}");
        }
        
        if (record.getBirthday() != null) {
            sql.VALUES("Birthday", "#{birthday,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            sql.VALUES("Address", "#{address,jdbcType=VARCHAR}");
        }
        
        if (record.getRank() != null) {
            sql.VALUES("Rank", "#{rank,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            sql.VALUES("Email", "#{email,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByIDSelective(User user) {
        SQL sql = new SQL();
        sql.UPDATE("User");

        if (user.getUsername() != null) {
            sql.SET("UserName = #{username,jdbcType=INTEGER}");
        }
        if (user.getPassword() != null) {
            sql.SET("PassWord = #{password,jdbcType=VARCHAR}");
        }

        if (user.getBirthday() != null) {
            sql.SET("Birthday = #{birthday,jdbcType=VARCHAR}");
        }

        if (user.getAddress() != null) {
            sql.SET("Address = #{address,jdbcType=VARCHAR}");
        }

        if (user.getEmail() != null) {
            sql.SET("Email = #{email,jdbcType=VARCHAR}");
        }

        sql.WHERE("Account = #{account,jdbcType=INTEGER}");

        return sql.toString();
    }
}