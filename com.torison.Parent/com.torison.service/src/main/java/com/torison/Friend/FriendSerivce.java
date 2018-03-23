package com.torison.Friend;

import com.torison.Friend.dao.FriendDao;
import com.torison.Friend.model.Friend;
import com.torison.Friend.model.FriendForm;
import com.torison.Friend.model.FriendWords;
import com.torison.Friend.model.FwordsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class FriendSerivce {

    @Autowired
    private FriendDao friendDao;

    /**
     * insert friend
     * status --> 1 to wait confirm
     * if the user confirm the relation ,verify operation will be execute in update relation
     * @param friend
     * @return
     */
    public int addFriend(Friend friend){
        friend.setStatus(1);
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

    /**
     * 查询好友
     * @return
     */
    public List<FriendForm> listFriend(Integer userid,Integer status){
        List<FriendForm> allFriend = new LinkedList<>();
        List<FriendForm> byuserid = friendDao.listFriendbyuseridAndStatus(userid,status);
        List<FriendForm> byfriendid = friendDao.listFriendbyFriendidAndStatus(userid,status);
        allFriend.addAll(byuserid);
        allFriend.addAll(byfriendid);
        return  allFriend;
    }

    /**
     * test friend relation if exist
     * return true -->exist
     *        false -->not exist
     * @param userid
     * @param friendid
     * @return
     */
    public boolean testFriendExist(Integer userid,Integer friendid){
        if (friendDao.selectFriendByUseridAndFriendid(userid,friendid)!=null
                || friendDao.selectFriendByUseridAndFriendid(friendid,userid)!=null){
            return true;
        }
        return false;
    }

    /**
     * update relation by ids
     * @param friend
     * @return
     */
    public int updateRelation(Friend friend){
        return friendDao.updateFriendByUidAndFid(friend);
    }

    /**
     * insert words by ids and words
     * @param friendWords
     * @return
     */
    public int inserWords(FriendWords friendWords){
        return friendDao.insertWords(friendWords);
    }


    /**
     * listwordsby toId
     * @param toId
     * @return
     */
    public List<FwordsForm> listwords(Integer toId){
        return friendDao.selectmyWordsById(toId);
    }



}
