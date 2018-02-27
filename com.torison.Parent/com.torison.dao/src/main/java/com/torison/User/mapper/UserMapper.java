package com.torison.User.mapper;

import com.torison.User.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert({
        "insert into user (ID, Account, ",
        "UserName, PassWord, ",
        "Age, Birthday, Address, ",
        "Rank, Email)",
        "values (#{id,jdbcType=INTEGER}, #{account,jdbcType=VARCHAR}, ",
        "#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{age,jdbcType=INTEGER}, #{birthday,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
        "#{rank,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    int insertSelective(User record);

    @Select({
            "select",
            "*",
            "from User",
            "where Account=#{account,jdbcType=VARCHAR}"
    })
    List<User> queryUserByAcc(String account);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByIDSelective")
    int updateByIDSelective(User user);



}