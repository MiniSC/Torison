package com.torison.Friend.mapper;

import com.torison.Friend.model.FriendWords;
import com.torison.Friend.model.FwordsForm;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface FriendWordsMapper {
    @Delete({
        "delete from friend_words",
        "where FromId = #{fromid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer fromid);

    @Insert({
        "insert into friend_words (FromId, ToId, ",
        "words)",
        "values (#{fromid,jdbcType=INTEGER}, #{toid,jdbcType=INTEGER}, ",
        "#{words,jdbcType=VARCHAR})"
    })
    int insert(FriendWords record);

    @InsertProvider(type=FriendWordsSqlProvider.class, method="insertSelective")
    int insertSelective(FriendWords record);

    @Select({
        "select",
        "FromId, ToId, words",
        "from friend_words",
        "where FromId = #{fromid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="FromId", property="fromid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ToId", property="toid", jdbcType=JdbcType.INTEGER),
        @Result(column="words", property="words", jdbcType=JdbcType.VARCHAR)
    })
    FriendWords selectByPrimaryKey(Integer fromid);


    @Select({
        "SELECT `user`.username,friend_words.words" ,
            " from friend_words,`user` " ,
            "WHERE friend_words.ToId =#{fromid,jdbcType=INTEGER} " ,
            "and friend_words.FromId=`user`.id"
    })
    @Results({
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="words", property="msg", jdbcType=JdbcType.VARCHAR),
    })
    List<FwordsForm> selectBytoid(Integer toId);

    @UpdateProvider(type=FriendWordsSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FriendWords record);

    @Update({
        "update friend_words",
        "set ToId = #{toid,jdbcType=INTEGER},",
          "words = #{words,jdbcType=VARCHAR}",
        "where FromId = #{fromid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FriendWords record);
}