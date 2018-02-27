package com.login.controller;

import com.torison.User.UserService;
import com.torison.User.model.User;
import com.torison.common.model.RespEntity;
import com.torison.common.model.respCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/login")
public class LoginAndOutController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/toLogin")
    public String toLogin(User user){
        return "test/Login/login";
    }

    @RequestMapping(value = "/loginTest")
    public String login(Model model, User user, HttpServletRequest request){
        RespEntity<User> respEntity = userService.testLogin(user);
        if (respCode.Login.TREUUSER.equals(respEntity.getRespCode())){
            HttpSession session = request.getSession();
            session.setAttribute("username",respEntity.getData().getUsername());
            session.setAttribute("account",respEntity.getData().getAccount());
            session.setAttribute("userid",respEntity.getData().getId());
            return "test/index";
        }else{
            model.addAttribute("wrong","wrong");
            return "test/Login/login";
        }
    }

    @RequestMapping(value = "/logOut")
    public String logOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        return "test/index";
    }
}
