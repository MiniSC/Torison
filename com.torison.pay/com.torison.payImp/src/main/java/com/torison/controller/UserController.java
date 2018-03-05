package com.torison.controller;

import com.torison.JPA.UserJPA;
import com.torison.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserJPA userJPA;

    @RequestMapping(value = "/list")
    public List<UserEntity> list(){
        return userJPA.findAll();
    }

    @RequestMapping(value = "/add")
    public String add(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("董金杰");
        userEntity.setAccount(123);
        userEntity.setPassWord("123");
        if (!userJPA.queryUserEntityByAccount(123).isEmpty()){
            return "用户账号重复";
        }
        userJPA.save(userEntity);
        userJPA.saveAndFlush(userEntity);
        return "用户信息添加成功"+ userEntity.getUserID();
    }
}
