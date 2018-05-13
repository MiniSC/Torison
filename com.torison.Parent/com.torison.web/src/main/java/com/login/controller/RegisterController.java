package com.login.controller;

import com.alibaba.fastjson.JSON;
import com.common.Util.UUIDUtil;
import com.model.Result;
import com.torison.User.UserService;
import com.torison.User.model.User;
import com.torison.api.PayServiceApi;
import com.torison.api.model.UserEntity;
import com.util.EmailSentService;
import com.util.model.MailCode;
import com.util.model.MailMsg;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    private static final Log log = LogFactory.getLog(RegisterController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PayServiceApi payServiceApi;

    @Autowired
    private EmailSentService emailSentService;

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
        String userAccount = UUIDUtil.generateShortUuid();
        if (!MailCode.MailErrCode.SUCCESS.equals(emailSentService.sendSimpleEmail(user.getEmail(), MailMsg.MAIL_TITLE,MailMsg.MAIL_CONTEXT_TOP+userAccount+"请点击链接验证您的账号：http://127.0.0.1:8002/mailtest?useracc="+userAccount).getRespCode())){
            result.setSuccess(false);
            result.setMsg("邮箱有误，邮件无法送达，请重新填写信息");
            return result;
        }
        /*生成uuid用来表示用户的账号和让用户登录*/
        user.setAccount(userAccount);
        user.setRank("0");
        log.info("=====即将录入的用户信息:"+JSON.toJSONString(user));
        int userid = userService.saveUser(user);
        /*为用户创建一个虚拟支付账户---暗箱操作，只是因为调用不了支付宝*/
        UserEntity userEntity = new UserEntity();
        userEntity.setMoney(0);
        userEntity.setPassWord(user.getPassword());
        userEntity.setUserName(user.getUsername());
        userEntity.setUserID(userid);
        userEntity.setAccount(user.getAccount());
        payServiceApi.addPayEntity(userEntity);
        result.setSuccess(true);
        result.setMsg("注册成功，请前往邮箱验证您的账号");
        return result;

    }


}
