package com.torison.mapper;

import com.torison.model.nomalUser;
import org.apache.ibatis.jdbc.SQL;

public class nomalUserSqlProvider {

    public String insertSelective(nomalUser record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("nomaluser");
        
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
}