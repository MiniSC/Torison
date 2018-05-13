package com.torison.ClickNum;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface ClickNumMapper {
    @Delete({
        "delete from click_num",
        "where routeId = #{routeid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer routeid);

    @Insert({
        "insert into click_num (routeId, times)",
        "values (#{routeid,jdbcType=INTEGER}, #{times,jdbcType=INTEGER})"
    })
    int insert(ClickNum record);

    @InsertProvider(type=ClickNumSqlProvider.class, method="insertSelective")
    int insertSelective(ClickNum record);

    @Select({
        "select",
        "routeId, times",
        "from click_num",
        "where routeId = #{routeid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="routeId", property="routeid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="times", property="times", jdbcType=JdbcType.INTEGER)
    })
    ClickNum selectByPrimaryKey(Integer routeid);

    @UpdateProvider(type=ClickNumSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ClickNum record);

    @Update({
        "update click_num",
        "set times = #{times,jdbcType=INTEGER}",
        "where routeId = #{routeid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ClickNum record);
}