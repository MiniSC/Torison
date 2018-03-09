package back.login.controller;


import back.common.model.ResEntity;
import back.common.model.Result;

import back.login.dao.model.Admin;
import back.login.service.LoginService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    public static final Log log= LogFactory.getLog(LoginController.class);

    @Autowired
    private LoginService loginService;

    /**
     * 初始化登录
     * @return
     */
    @RequestMapping(value = "/toLogin")
    public String toLoginView(){
        return "/Login/Login";
    }

    /**
     * 登录功能实现
     * @param request
     * @param admin
     * @return
     */
    @RequestMapping(value = "/testLogin")
    public String testLogin(HttpServletRequest request, Admin admin, Model model){


        log.info(JSON.toJSONString(admin));
        Result result = new Result();
        HttpSession session = request.getSession();
        ResEntity<Admin> resEntity = loginService.login(admin);
        admin =  resEntity.getData();
        log.info("用户信息======="+JSON.toJSONString(admin));
        if (resEntity.isSuccess()){
            session.setAttribute("username",admin.getAdminName());
            session.setAttribute("userid",admin.getAccount());
            return "/main/index";
        }
        if (!resEntity.isSuccess()){
            model.addAttribute("failMsg",resEntity.getRespMsg());
        }
        return "/Login/Login";
    }

    /**
     * 登出
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.removeAttribute("userid");
        Result result = new Result();
        result.setSuccess(true);
        return "/Login/Login";
    }

    @RequestMapping(value = "/toManage")
    public String toManage(){
        return "/Manage/AddAuthentication";
    }



}
