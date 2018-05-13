package com.user.controller;

import com.alibaba.fastjson.JSON;
import com.login.model.UserRank;
import com.model.Result;
import com.torison.User.UserService;
import com.torison.User.dao.UserDao;
import com.torison.User.model.User;
import com.torison.api.PayServiceApi;
import com.torison.routeMaker.model.RouteMaker;
import com.torison.routemaker.RouteMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private RouteMakerService routeMakerService;


    @RequestMapping(value = "/toUpdate")
    public String toUpdate(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = userService.getUserByAcc(session.getAttribute("account").toString());
        RouteMaker routeMaker = routeMakerService.queryMaker(user.getId());
        if (routeMaker != null && "0".equals(routeMaker.getStatus())){
            user.setRank("发布者");

        }else{
            user.setRank("普通用户");
            model.addAttribute("flag",1);
        }

        model.addAttribute("user",user);
        return "test/User/UpdateUser";
    }

    @RequestMapping(value = "/Update")
    @ResponseBody
    public Result updateUser(Model model, User user, HttpServletRequest request){
        Result result = new Result();
        HttpSession session = request.getSession();
        user.setAccount(session.getAttribute("account").toString());
        System.out.println(JSON.toJSONString(user));
       int flag = userService.update(user);
       if (flag!=0){
          result.setSuccess(true);
           return result;
       }else
       {
           result.setSuccess(false);
           result.setMsg("系统异常");
           return result;
       }
    }
}
