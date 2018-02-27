package com.torison.Friend.dao;

import com.torison.Friend.mapper.FriendMapper;
import com.torison.Friend.model.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendDao {

    @Autowired
    private FriendMapper friendMapper;

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
        return friendMapper.deleteByPrimaryKeyAndFriendid(userid,friendid);
    }

}
