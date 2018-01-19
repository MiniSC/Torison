package com.torison.mapper;

import com.torison.model.nomalUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Mapper
public interface nomalUserMapper {
    @Insert({
        "insert into nomaluser (userId, userAccountNum, ",
        "userPassword)",
        "values (#{userid,jdbcType=INTEGER}, #{useraccountnum,jdbcType=VARCHAR}, ",
        "#{userpassword,jdbcType=VARCHAR})"
    })
    int insert(nomalUser record);

    @InsertProvider(type=nomalUserSqlProvider.class, method="insertSelective")
    int insertSelective(nomalUser record);
}