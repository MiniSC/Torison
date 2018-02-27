package com.login.controller;

import com.alibaba.fastjson.JSON;
import com.model.Result;
import com.torison.User.UserService;
import com.torison.User.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private UserService userService;

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
        System.out.println("即将录入的用户信息"+JSON.toJSONString(user));
        int id =  userService.saveUser(user);
        System.out.println(user.getId());
        result.setSuccess(true);
        return result;

    }


}
