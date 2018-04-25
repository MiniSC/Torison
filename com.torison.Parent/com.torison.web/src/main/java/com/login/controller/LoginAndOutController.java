package com.login.controller;

import com.login.model.UserRank;
import com.model.Result;
import com.torison.User.UserService;
import com.torison.User.model.User;
import com.torison.common.model.RespEntity;
import com.torison.common.model.respCode;
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
@RequestMapping(value = "/login")
public class LoginAndOutController {

    @Autowired
    private UserService userService;

    @Autowired
    private RouteMakerService routeMakerService;



    @RequestMapping(value = "/toLogin")
    public String toLogin(User user){
        return "test/Login/login";
    }


    /**
     * 登录验证
     * @param model
     * @param user
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginTest")
    @ResponseBody
    public Result login(Model model, User user, HttpServletRequest request){
        Result result = new Result();
        RespEntity<User> respEntity = userService.testLogin(user);
        if (respCode.Login.RANKZERO.equals(respEntity.getRespCode())){
            result.setSuccess(false);
            result.setMsg("请先到邮箱激活账号");
            return result;
        }
        if (respCode.Login.TREUUSER.equals(respEntity.getRespCode())){
            HttpSession session = request.getSession();
            session.setAttribute("username",respEntity.getData().getUsername());
            session.setAttribute("account",respEntity.getData().getAccount());
            session.setAttribute("userid",respEntity.getData().getId());
            RouteMaker routeMaker = routeMakerService.queryMaker(respEntity.getData().getId());
            if (routeMaker!=null) {
                if ("0".equals(routeMaker.getStatus())) {
                    session.setAttribute("userrank", UserRank.MAKER.code());
                }
            }
            result.setSuccess(true);
            return result;
        }else{
            result.setSuccess(false);
            result.setMsg("用户名密码错误");
            return result;
        }
    }

    /**
     * 登出账号
     * @param request
     * @return
     */
    @RequestMapping(value = "/logOut")
    public String logOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        return "test/index";
    }

    /**
     * 跳转首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/toIndex")
    public String toIndex(HttpServletRequest request){
        return "test/index";
    }
}
