package com.torison.mapper;

import com.torison.model.Route;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RouteMapper {
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

    @InsertProvider(type=RouteSqlProvider.class, method="insertSelective")
    int insertSelective(Route record);
}