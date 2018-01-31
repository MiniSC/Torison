package com.torison.nomalUser.dao;

import com.torison.nomalUser.mapper.nomalUserMapper;
import com.torison.nomalUser.model.nomalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 普通用户操作接口
 * @author dongjj
 * @createtime 1.19
 */
@Service
public class nomaluserDao {

    @Autowired
    nomalUserMapper userMapper;

    /**
     * 保存普通用户信息
     * @param nomaluser
     */
    public void savenomalUser(nomalUser nomaluser){

        userMapper.insert(nomaluser);
    }

    /**
     * 查询普通用户信息
     * @param userAcc
     * @return
     */
    public nomalUser queryUser(String userAcc){
       nomalUser user = userMapper.selectByUserAcc(userAcc);
       return user;
    }

    /**
     * 修改用户信息
     * @param user
     */
    public Integer updateUser(nomalUser user){
       return userMapper.updateByAccountSelective(user);
    }



}
