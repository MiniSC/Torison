package com.torison.mapper;

import com.torison.model.RoutePic;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface RoutePicMapper {
    @Delete({
        "delete from ROUTE_PIC",
        "where routeID = #{routeid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer routeid);

    @Insert({
        "insert into ROUTE_PIC (routeID, routePic1, ",
        "routePic2, routePic3, ",
        "routePic4, routePic5)",
        "values (#{routeid,jdbcType=INTEGER}, #{routepic1,jdbcType=VARCHAR}, ",
        "#{routepic2,jdbcType=VARCHAR}, #{routepic3,jdbcType=VARCHAR}, ",
        "#{routepic4,jdbcType=VARCHAR}, #{routepic5,jdbcType=VARCHAR})"
    })
    int insert(RoutePic record);

    @InsertProvider(type=RoutePicSqlProvider.class, method="insertSelective")
    int insertSelective(RoutePic record);

    @Select({
        "select",
        "routeID, routePic1, routePic2, routePic3, routePic4, routePic5",
        "from ROUTE_PIC",
        "where routeID = #{routeid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="routeID", property="routeid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="routePic1", property="routepic1", jdbcType=JdbcType.VARCHAR),
        @Result(column="routePic2", property="routepic2", jdbcType=JdbcType.VARCHAR),
        @Result(column="routePic3", property="routepic3", jdbcType=JdbcType.VARCHAR),
        @Result(column="routePic4", property="routepic4", jdbcType=JdbcType.VARCHAR),
        @Result(column="routePic5", property="routepic5", jdbcType=JdbcType.VARCHAR)
    })
    RoutePic selectByPrimaryKey(Integer routeid);

    @UpdateProvider(type=RoutePicSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RoutePic record);

    @Update({
        "update ROUTE_PIC",
        "set routePic1 = #{routepic1,jdbcType=VARCHAR},",
          "routePic2 = #{routepic2,jdbcType=VARCHAR},",
          "routePic3 = #{routepic3,jdbcType=VARCHAR},",
          "routePic4 = #{routepic4,jdbcType=VARCHAR},",
          "routePic5 = #{routepic5,jdbcType=VARCHAR}",
        "where routeID = #{routeid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RoutePic record);
}