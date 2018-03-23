package com.torison.Friend.mapper;

import com.torison.Friend.model.Friend;
import com.torison.Friend.model.FriendForm;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface FriendMapper {
    @Delete({
        "delete from friends",
        "where UserID = #{userid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userid);

    @Delete({
            "delete from friends",
            "where UserID = #{userid,jdbcType=INTEGER} and FriendID=#{friendid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKeyAndFriendId(@Param(value = "userid") Integer userid,@Param(value = "friendid") Integer friendid);

    @Insert({
        "insert into friends (UserID, FriendID, ",
        "Status)",
        "values (#{userid,jdbcType=INTEGER}, #{friendid,jdbcType=INTEGER}, ",
        "#{status,jdbcType=INTEGER})"
    })
    int insert(Friend record);

    @InsertProvider(type=FriendSqlProvider.class, method="insertSelective")
    int insertSelective(Friend record);

    @Select({
        "select",
        "UserID, FriendID, Status",
        "from friends",
        "where UserID = #{userid,jdbcType=INTEGER} and FriendID = #{friendid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="UserID", property="userid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="FriendID", property="friendid", jdbcType=JdbcType.INTEGER),
        @Result(column="Status", property="status", jdbcType=JdbcType.INTEGER)
    })
    Friend selectByUseridAndFriendid(@Param(value = "userid") Integer userid,@Param(value = "friendid") Integer friendid);

    @UpdateProvider(type=FriendSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Friend record);

    @Update({
        "update friends",
        "set ",
          "Status = #{status,jdbcType=INTEGER}",
        "where UserID = #{userid,jdbcType=INTEGER} and FriendID = #{friendid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Friend record);

    @Select({
            "select * from",
            "User, friends",
            "where User.id =friends.FriendID and friends.UserID= #{userid,jdbcType=INTEGER} and friends.Status=#{status,jdbcType=INTEGER} "
    })
    @Results({
            @Result(column="account", property="userAcc", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="username", property="userName", jdbcType=JdbcType.VARCHAR)
    })
    List<FriendForm> selectfriendByUseridandStatus(@Param(value = "userid") Integer userid,@Param(value = "status") Integer status);


    @Select({
            "select * from",
            "User, friends",
            "where User.id =friends.UserID and friends.FriendID= #{friendid,jdbcType=INTEGER} and friends.Status=#{status,jdbcType=INTEGER} "
    })
    @Results({
            @Result(column="account", property="userAcc", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="username", property="userName", jdbcType=JdbcType.VARCHAR)
    })
    List<FriendForm> selectfriendByFriendidandStatus(@Param(value = "friendid") Integer friendid,@Param(value = "status") Integer status);



}