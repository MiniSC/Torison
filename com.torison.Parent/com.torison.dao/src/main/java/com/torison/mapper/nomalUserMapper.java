package com.torison.mapper;

import com.torison.model.nomalUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface nomalUserMapper {
    @Delete({
        "delete from NOMAL_USER",
        "where userId = #{userid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userid);

    @Insert({
        "insert into NOMAL_USER (userId, userAccountNum, ",
        "userPassword)",
        "values (#{userid,jdbcType=INTEGER}, #{useraccountnum,jdbcType=VARCHAR}, ",
        "#{userpassword,jdbcType=VARCHAR})"
    })
    int insert(nomalUser record);

    @InsertProvider(type=nomalUserSqlProvider.class, method="insertSelective")
    int insertSelective(nomalUser record);

    @Select({
        "select",
        "userId, userAccountNum, userPassword",
        "from NOMAL_USER",
        "where userAccountNum = #{userAccount,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="userId", property="userid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userAccountNum", property="useraccountnum", jdbcType=JdbcType.VARCHAR),
        @Result(column="userPassword", property="userpassword", jdbcType=JdbcType.VARCHAR)
    })
    nomalUser selectByUserAcc(String userAccount);

    @UpdateProvider(type=nomalUserSqlProvider.class, method="updateByAccountSelective")
    int updateByAccountSelective(nomalUser record);

    @Update({
        "update NOMAL_USER",
        "set userAccountNum = #{useraccountnum,jdbcType=VARCHAR},",
          "userPassword = #{userpassword,jdbcType=VARCHAR}",
        "where userId = #{userid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(nomalUser record);
}