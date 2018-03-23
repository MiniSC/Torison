package com.torison.User.dao;

import com.torison.User.mapper.UserMapper;
import com.torison.User.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author dongjj
 *
 */
@Service
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据账号查询用户
     * @param account
     * @return
     */
    public User getUserByAcc(String account){
        return userMapper.queryUserByAcc(account).get(0);
    }
    /**
     * 根据账号查询用户
     * @param account
     * @return
     */
    public User getUserById(String id){
        return userMapper.queryUserById(id).get(0);
    }

    /**
     * 添加用户
     * @param user
     */
    public int insertUser(User user){

       return userMapper.insert(user);

    }

    /**
     * 有什么就修改什么
     * @Param User
     */
    public int updateByIDSelective(User user){
        return userMapper.updateByIDSelective(user);
    }

}
