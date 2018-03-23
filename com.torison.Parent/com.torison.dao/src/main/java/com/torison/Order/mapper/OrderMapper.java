package com.torison.Order.mapper;

import com.torison.Order.model.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.validation.ValidationUtils;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Delete({
        "delete from orders",
        "where OrderID = #{orderid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer orderid);

    @Insert({
        "insert into orders (OrderID, UserID, ",
        "RouteID, Num, Status)",
        "values (#{orderid,jdbcType=INTEGER}, #{userid,jdbcType=INTEGER}, ",
        "#{routeid,jdbcType=INTEGER}, #{num,jdbcType=INTEGER}, #{status,jdbcType=VARCHAR})"
    })
    int insert(Order record);

    @InsertProvider(type=OrderSqlProvider.class, method="insertSelective")
    int insertSelective(Order record);

    @Select({
        "select",
        "OrderID, UserID, RouteID, Num, Status",
        "from orders",
        "where OrderID = #{orderid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="OrderID", property="orderid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="UserID", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="RouteID", property="routeid", jdbcType=JdbcType.INTEGER),
        @Result(column="Num", property="num", jdbcType=JdbcType.INTEGER),
        @Result(column="Status", property="status", jdbcType=JdbcType.VARCHAR)
    })
    Order selectByPrimaryKey(Integer orderid);

    @UpdateProvider(type=OrderSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Order record);

    @Update({
        "update orders",
        "set UserID = #{userid,jdbcType=INTEGER},",
          "RouteID = #{routeid,jdbcType=INTEGER},",
          "Num = #{num,jdbcType=INTEGER},",
          "Status = #{status,jdbcType=VARCHAR}",
        "where OrderID = #{orderid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Order record);


    @Select({
            "select",
            "OrderID, UserID, RouteID, Num, Status",
            "from orders",
            "where UserID = #{userid,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="OrderID", property="orderid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="UserID", property="userid", jdbcType=JdbcType.INTEGER),
            @Result(column="RouteID", property="routeid", jdbcType=JdbcType.INTEGER),
            @Result(column="Num", property="num", jdbcType=JdbcType.INTEGER),
            @Result(column="Status", property="status", jdbcType=JdbcType.VARCHAR)
    })
    List<Order> selectByUserID(Integer userid);

    @Select({
            "select",
            "OrderID, UserID, RouteID, Num, Status",
            "from orders",
            "where UserID = #{userid,jdbcType=INTEGER} and Status = #{status,jdbcType=VARCHAR} "
    })
    @Results({
            @Result(column="OrderID", property="orderid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="UserID", property="userid", jdbcType=JdbcType.INTEGER),
            @Result(column="RouteID", property="routeid", jdbcType=JdbcType.INTEGER),
            @Result(column="Num", property="num", jdbcType=JdbcType.INTEGER),
            @Result(column="Status", property="status", jdbcType=JdbcType.VARCHAR)
    })
    List<Order> selectByUserIDAndStatus(@Param(value = "userid") Integer userid,@Param(value = "status") String status);


    @Select({
            "select",
            "OrderID, UserID, RouteID, Num, Status",
            "from orders",
            "where UserID = #{userid,jdbcType=INTEGER} and RouteID = #{routeid,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="OrderID", property="orderid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="UserID", property="userid", jdbcType=JdbcType.INTEGER),
            @Result(column="RouteID", property="routeid", jdbcType=JdbcType.INTEGER),
            @Result(column="Num", property="num", jdbcType=JdbcType.INTEGER),
            @Result(column="Status", property="status", jdbcType=JdbcType.VARCHAR)
    })
    List<Order> selectByUserIDAndRouteID(@Param(value = "userid") Integer userid,@Param(value = "routeid") Integer routeid);
 @Select({
            "select",
            "OrderID, UserID, RouteID, Num, Status",
            "from orders",
            "where UserID = #{userid,jdbcType=INTEGER} and RouteID = #{routeid,jdbcType=INTEGER} and Status = #{status,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="OrderID", property="orderid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="UserID", property="userid", jdbcType=JdbcType.INTEGER),
            @Result(column="RouteID", property="routeid", jdbcType=JdbcType.INTEGER),
            @Result(column="Num", property="num", jdbcType=JdbcType.INTEGER),
            @Result(column="Status", property="status", jdbcType=JdbcType.VARCHAR)
    })
    Order selectByall(Order order);
}