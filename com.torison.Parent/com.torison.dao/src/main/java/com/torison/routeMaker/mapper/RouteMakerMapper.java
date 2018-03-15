package com.torison.routeMaker.mapper;

import com.torison.routeMaker.model.RouteMaker;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface RouteMakerMapper {
    @Delete({
        "delete from route_maker",
        "where UserID = #{userid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userid);

    @Insert({
        "insert into route_maker (UserID, Pic1, ",
        "Pic2, Introduce)",
        "values (#{userid,jdbcType=INTEGER}, #{pic1,jdbcType=VARCHAR}, ",
        "#{pic2,jdbcType=VARCHAR}, #{introduce,jdbcType=VARCHAR},",
        "#{status,jdbcType=VARCHAR})"
    })
    int insert(RouteMaker record);

    @InsertProvider(type=RouteMakerSqlProvider.class, method="insertSelective")
    int insertSelective(RouteMaker record);

    @Select({
        "select",
        "UserID, Pic1, Pic2, Introduce, Status",
        "from route_maker",
        "where UserID = #{userid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="UserID", property="userid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="Pic1", property="pic1", jdbcType=JdbcType.VARCHAR),
        @Result(column="Pic2", property="pic2", jdbcType=JdbcType.VARCHAR),
        @Result(column="Status",property="status",jdbcType = JdbcType.VARCHAR),
        @Result(column="Introduce", property="introduce", jdbcType=JdbcType.VARCHAR)
    })
    RouteMaker selectByPrimaryKey(Integer userid);

    @UpdateProvider(type=RouteMakerSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RouteMaker record);

    @Update({
        "update route_maker",
        "set Pic1 = #{pic1,jdbcType=VARCHAR},",
          "Pic2 = #{pic2,jdbcType=VARCHAR},",
          "Introduce = #{introduce,jdbcType=VARCHAR}",
          "Status = #{status,jdbcType=VARCHAR}",
        "where UserID = #{userid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RouteMaker record);
}