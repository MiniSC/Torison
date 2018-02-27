package com.torison.Friend.mapper;

import com.torison.Friend.model.Friend;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface FriendMapper {
    @Delete({
        "delete from friends",
        "where UserID = #{userid,jdbcType=INTEGER} and FriendID = #{friendid,jdbcType=INTEGER} "
    })
    int deleteByPrimaryKeyAndFriendid(Integer userid,Integer friendid);

    @Insert({
        "insert into friends (UserID, FriendID)",
        "values (#{userid,jdbcType=INTEGER}, #{friendid,jdbcType=INTEGER})"
    })
    int insert(Friend record);

    @InsertProvider(type=FriendSqlProvider.class, method="insertSelective")
    int insertSelective(Friend record);

    @Select({
        "select",
        "UserID, FriendID",
        "from friends",
        "where UserID = #{userid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="UserID", property="userid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="FriendID", property="friendid", jdbcType=JdbcType.INTEGER)
    })
    Friend selectByPrimaryKey(Integer userid);

    @UpdateProvider(type=FriendSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Friend record);

    @Update({
        "update friends",
        "set FriendID = #{friendid,jdbcType=INTEGER}",
        "where UserID = #{userid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Friend record);
}