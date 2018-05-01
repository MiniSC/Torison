package com.torison.Route.mapper;

import com.torison.Route.model.BestEndAddress;
import com.torison.Route.model.Route;
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
        "#{routelastpersonnum,jdbcType=INTEGER}, #{deposite,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "routeid")
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
     * 查询最新的十条路线
     * @return
     */
    @Select({
            "select",
            "*",
            "from ROUTE GROUP BY routeID DESC LIMIT 10"
    })
    List<Route> queryTopTenRoute();

    /**
     * 查询最热门的目的地
     * @return
     */
    @Select({
            " select routeEndAddress ,count(routeEndAddress) num",
            " from route ",
            " GROUP BY routeEndAddress",
            " LIMIT 10"
    })
    @Results({
            @Result(column="routeEndAddress", property="routeendaddress", jdbcType= JdbcType.VARCHAR),
            @Result(column="num", property="num", jdbcType=JdbcType.INTEGER)})
    List<BestEndAddress> listbestend();

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
     * 根据发布者ID查询路线
     * @param routeFromID
     * @return
     */
    @Select({
            "select",
            "*",
            "from ROUTE",
            "where routeFromID=#{routeFromID,jdbcType=VARCHAR}"
    })
    List<Route> queryRouteByMakerID(int routeFromID);
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


    /**
     * 根据发布者ID查询路线
     * @param endaddress
     * @return
     */
    @Select({
            "select",
            "*",
            "from ROUTE",
            "where routeEndAddress=#{endaddress,jdbcType=VARCHAR}"
    })
    List<Route> queryRouteByEnd(String endaddress);

}