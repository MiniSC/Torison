package com.torison.mapper;

import com.torison.model.Route;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface RouteMapper {
    /**
     * 插入路线
     * @param record
     * @return
     */
    @Insert({
        "insert into ROUTE (routeID, routeName, ",
        "routeFromAddress, routeEndAddress, ",
        "routeNeedMoney, routeIntroduce, ",
        "routeFromId, routeMaxPersonNum, ",
        "routeLastPersonNum, deposite)",
        "values (#{routeid,jdbcType=INTEGER}, #{routename,jdbcType=VARCHAR}, ",
        "#{routefromaddress,jdbcType=VARCHAR}, #{routeendaddress,jdbcType=VARCHAR}, ",
        "#{routeneedmoney,jdbcType=DOUBLE}, #{routeintroduce,jdbcType=VARCHAR}, ",
        "#{routefromid,jdbcType=VARCHAR}, #{routemaxpersonnum,jdbcType=INTEGER}, ",
        "#{routelastpersonnum,jdbcType=INTEGER}, #{deposite,jdbcType=DOUBLE})"
    })
    int insert(Route record);

    /**
     * 有什么插入什么的插入路线
     * @param record
     * @return
     */
    @InsertProvider(type=RouteSqlProvider.class, method="insertSelective")
    int insertSelective(Route record);

    /**
     * 查询所有路线
     * @return
     */
    @Select({
            "select",
            "*",
            "from ROUTE"
    })
    List<Route> queryAllRoute();

    /**
     * 根据ID查询路线
     * @param ID
     * @return
     */
    @Select({
            "select",
            "*",
            "from ROUTE",
            "where routeID=#{ID,jdbcType=VARCHAR}"
    })
    List<Route> queryRouteByID(int ID);

    /**
     * 有什么条件就改什么数据的修改
     * @param record
     * @return
     */
    @UpdateProvider(type=RouteSqlProvider.class, method="updateByIDSelective")
    int updateByIDSelective(Route record);

    /**
     * 根据ID删除路线
     * @param routeid
     * @return
     */
    @Delete({
            "delete from ROUTE",
            "where routeID = #{routeid,jdbcType=INTEGER}"
    })
    int deleteByID(Integer routeid);

}