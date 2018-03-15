package com.torison.RouteCollect.mapper;

import com.torison.RouteCollect.model.RouteCollection;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface RouteCollectionMapper {
    @Delete({
        "delete from route_collection",
        "where UserID = #{userid,jdbcType=INTEGER}  and routeID = #{routeid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(@Param("userid")Integer userid,@Param("routeid")Integer routeid);

    @Insert({
        "insert into route_collection (UserID, routeID)",
        "values (#{userid,jdbcType=INTEGER}, #{routeid,jdbcType=INTEGER})"
    })
    int insert(RouteCollection record);

    @InsertProvider(type=RouteCollectionSqlProvider.class, method="insertSelective")
    int insertSelective(RouteCollection record);

    @Select({
        "select",
        "*",
        "from route_collection",
        "where UserID = #{userid,jdbcType=INTEGER}"
    })
    List<RouteCollection> selectByPrimaryKey(Integer userid);

    @Select({
            "select",
            "*",
            "from route_collection",
            "where UserID = #{userid,jdbcType=INTEGER} and RouteID = #{routeid,jdbcType=INTEGER}"
    })
    RouteCollection selectByPrimaryKeyAndRouteId(@Param(value = "userid") Integer userid,@Param(value = "routeid") Integer routeid);

    @UpdateProvider(type=RouteCollectionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RouteCollection record);

    @Update({
        "update route_collection",
        "set routeID = #{routeid,jdbcType=INTEGER}",
        "where UserID = #{userid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RouteCollection record);
}