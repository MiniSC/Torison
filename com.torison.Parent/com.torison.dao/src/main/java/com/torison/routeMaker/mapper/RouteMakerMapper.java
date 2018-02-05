package com.torison.routeMaker.mapper;

import com.torison.routeMaker.model.RouteMaker;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;

public interface RouteMakerMapper {
    @Insert({
        "insert into ROUTE_MAKER (userId, introduce, ",
        "seniorityPic, seniorityPic2, ",
        "isAgency, seniorityYet)",
        "values (#{userid,jdbcType=INTEGER}, #{introduce,jdbcType=VARCHAR}, ",
        "#{senioritypic,jdbcType=VARCHAR}, #{senioritypic2,jdbcType=VARCHAR}, ",
        "#{isagency,jdbcType=TINYINT}, #{seniorityyet,jdbcType=TINYINT})"
    })
    int insert(RouteMaker record);

    @InsertProvider(type=RouteMakerSqlProvider.class, method="insertSelective")
    int insertSelective(RouteMaker record);
}