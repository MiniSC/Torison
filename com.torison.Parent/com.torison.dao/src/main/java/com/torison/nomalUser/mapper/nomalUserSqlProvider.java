package com.torison.nomalUser.mapper;

import com.torison.nomalUser.model.nomalUser;
import org.apache.ibatis.jdbc.SQL;

public class nomalUserSqlProvider {

    public String insertSelective(nomalUser record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("NOMAL_USER");
        
        if (record.getUserid() != null) {
            sql.VALUES("userId", "#{userid,jdbcType=INTEGER}");
        }
        
        if (record.getUseraccountnum() != null) {
            sql.VALUES("userAccountNum", "#{useraccountnum,jdbcType=VARCHAR}");
        }
        
        if (record.getUserpassword() != null) {
            sql.VALUES("userPassword", "#{userpassword,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByAccountSelective(nomalUser record) {
        SQL sql = new SQL();
        sql.UPDATE("NOMAL_USER");

        if (record.getUseraccountnum() != null) {
            sql.SET("userAccountNum = #{useraccountnum,jdbcType=VARCHAR}");
        }

        if (record.getUserpassword() != null) {
            sql.SET("userPassword = #{userpassword,jdbcType=VARCHAR}");
        }

        sql.WHERE("userAccountNum = #{useraccountnum,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}