package com.login.controller;

import com.alibaba.fastjson.JSON;
import com.common.Util.UUIDUtil;
import com.model.Result;
import com.torison.User.UserService;
import com.torison.User.model.User;
import com.torison.api.PayServiceApi;
import com.torison.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private PayServiceApi payServiceApi;

    /**
     * 初始化注册界面
     * @return
     */
    @RequestMapping(value = "/toRegist")
    public String  toRegist(){
        return "test/Login/registered";
    }

    /**
     *
     */
    @RequestMapping(value = "/regist")
    @ResponseBody
    public Result regist(User user){
        Result result = new Result();
        user.setAccount(UUIDUtil.generateShortUuid());
        System.out.println("即将录入的用户信息"+JSON.toJSONString(user));
        int id =  userService.saveUser(user);

        UserEntity userEntity = new UserEntity();
        userEntity.setMoney(0);
        userEntity.setPassWord(user.getPassword());
        userEntity.setUserName(user.getUsername());
        userEntity.setUserID(user.getId());
        userEntity.setAccount(user.getAccount());
        payServiceApi.addPayEntity(userEntity);


        result.setSuccess(true);
        return result;

    }


}
