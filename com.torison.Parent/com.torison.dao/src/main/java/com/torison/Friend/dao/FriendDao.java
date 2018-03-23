package com.torison.Friend.dao;

import com.torison.Friend.mapper.FriendMapper;
import com.torison.Friend.mapper.FriendWordsMapper;
import com.torison.Friend.model.Friend;
import com.torison.Friend.model.FriendForm;
import com.torison.Friend.model.FriendWords;
import com.torison.Friend.model.FwordsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendDao {

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private FriendWordsMapper friendWordsMapper;

    /**
     * 添加好友
     * @param friend
     * @return
     */
    public int addFriend(Friend friend){
        return friendMapper.insert(friend);
    }

    /**
     * 删除好友
     * @param userid
     * @param friendid
     * @return
     */
    public int deleteFriend(Integer userid,Integer friendid){
        return friendMapper.deleteByPrimaryKeyAndFriendId(userid,friendid);
    }


    /**
     * ListMyFriends by userid with status CONFIRM-->"0"
     * @param userid
     * @return
     */
    public List<FriendForm> listFriendbyuseridAndStatus(Integer userid,Integer status){
        return friendMapper.selectfriendByUseridandStatus(userid,status);
    }
    /**
     * ListMyFriends by friendid with status CONFIRM-->"0"
     * @param friendid
     * @return
     */
    public List<FriendForm> listFriendbyFriendidAndStatus(Integer friendid,Integer status){
        return friendMapper.selectfriendByFriendidandStatus(friendid,status);
    }

    /**
     * List Friend By User's id and Friend's id
     * for test friend is exist
     * @param userid
     * @param friendid
     * @return
     */
    public Friend selectFriendByUseridAndFriendid(Integer userid,Integer friendid){
        return friendMapper.selectByUseridAndFriendid(userid,friendid);
    }


    /**
     * updateFriendByUidAndFid
     * for update status
     * @param friend
     * @return
     */
    public int updateFriendByUidAndFid(Friend friend){
        return friendMapper.updateByPrimaryKey(friend);
    }


    /**
     * insert to someone's words
     * @param friendWords
     * @return
     */
    public int insertWords(FriendWords friendWords){
        return friendWordsMapper.insert(friendWords);
    }

    /**
     * listWords by toId
     * because i'm receiver
     * @param toId
     * @return
     */
    public List<FwordsForm> selectmyWordsById(Integer toId){
        return friendWordsMapper.selectBytoid(toId);
    }







}
