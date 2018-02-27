package com.torison.Friend;

import com.torison.Friend.dao.FriendDao;
import com.torison.Friend.model.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendSerivce {

    @Autowired
    private FriendDao friendDao;

    /**
     * 添加好友
     * @param friend
     * @return
     */
    public int addFriend(Friend friend){
        return friendDao.addFriend(friend);
    }

    /**
     * 删除好友
     * @param friend
     * @return
     */
    public int deleteFriend(Friend friend){
        return friendDao.deleteFriend(friend.getUserid(),friend.getFriendid());
    }
}
