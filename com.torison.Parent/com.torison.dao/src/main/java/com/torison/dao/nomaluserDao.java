package com.torison.dao;

import com.torison.mapper.nomalUserMapper;
import com.torison.model.nomalUser;
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
    nomalUserMapper nomalUserMapper;

    public void savenomalUser(nomalUser nomaluser){
        nomalUserMapper.insert(nomaluser);
    }


}
