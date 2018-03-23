package com.test.controller;


import com.model.Result;

import com.torison.User.UserService;
import com.torison.User.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String test(Model model,HttpServletRequest request) {
        if (request.getSession().getAttribute("userid")!=null)
        {
             return "/test/index";
        }
        return "/login/toLogin";
    }

    @RequestMapping("/listRouteMake")
    public String listRouteMaker() {
        return "test/Route/MakeRoute";
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request, String makerName, String introduce, MultipartFile file) {
        return null;
    }

    @RequestMapping(value = "/route", method = RequestMethod.POST)
    @ResponseBody
    public Result route(HttpServletRequest request, String makerName, String introduce, MultipartFile file) {

        return null;
    }


    @RequestMapping(value = "/")
    public String toLogin(){
        return "test/Login/login";
    }



    @RequestMapping(value = "/mailtest")
    public String toLogin(String useracc){
        User user = new User();
        user.setRank("1");
        user.setAccount(useracc);
        userService.update(user);
        return "test/Login/emailchecked";
    }
}
