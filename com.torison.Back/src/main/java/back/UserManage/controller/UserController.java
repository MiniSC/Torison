package back.UserManage.controller;

import back.UserManage.dao.model.RouteMaker;
import back.UserManage.dao.model.RouteMakerForm;
import back.UserManage.dao.model.User;
import back.UserManage.service.RMService;
import back.UserManage.service.UserService;
import back.common.model.DataGrid;
import back.common.model.Result;
import back.login.dao.JPA.AdminJPA;
import back.login.dao.model.Admin;
import back.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    RMService rmService;

    @Autowired
    private AdminJPA adminJPA;

    @Autowired
    private LoginService loginService;


    /**
     * 初始化用户管理界面
     * @return
     */
    @RequestMapping("/toListUser")
    public String toListUser(){
        return "/User/UserManage";
    }

    /**
     * 查询用户信息
     * @param user
     * @return
     */
    @RequestMapping("/listUser")
    @ResponseBody
    public DataGrid listUser(User user){

        DataGrid dataGrid = new DataGrid();
        List<User> listUser = userService.listUser(user);
        dataGrid.setRows(listUser);
        dataGrid.setTotal(listUser.size());
        return dataGrid;

    }

    /**
     * 用户详情
     * @param model
     * @param userid
     * @return
     */
    @RequestMapping("/listUserDetail")
    public String listUserDetail(Model model,String userid){
        User userReq = new User();
        userReq.setUserId(Integer.parseInt(userid));
        model.addAttribute("user",userService.listUser(userReq).get(0));
        return "User/UserDetail";
    } /**
     * 跳转修改密码
     * @param
     * @param
     * @return
     */
    @RequestMapping("/tochangepwd")
    public String tochangepwd(){

        return "User/changepwd";
    }

    @RequestMapping("/changepwd")
    @ResponseBody
    public Result changepwd(HttpServletRequest request , String pwd){
        HttpSession session = request.getSession();
        String userAccount = session.getAttribute("userid").toString();
        Result result = new Result();
        Admin admin = new Admin();
        admin.setAccount(userAccount);
        admin.setPassword(pwd);
        loginService.updatepwd(admin);
          result.setSuccess(true);
        return result;
    }

    /**
     * 冻结账号
     * @param userId
     * @return
     */
    @RequestMapping("/freezeUser")
    @ResponseBody
    public Result freezeUser(String userId){
        System.out.println(userId);
        Result result = new Result();
        if (userService.updateUser(userId)!=0){
            result.setSuccess(true);
            return result;
        }
        return result;
    }

    /**
     * 解冻账号
     * @param userId
     * @return
     */
    @RequestMapping("/reFreezeUser")
    @ResponseBody
    public Result reFreezeUser(String userId){
        System.out.println(userId);
        Result result = new Result();
        if (userService.updateUserReFreeze(userId)!=0){
            result.setSuccess(true);
            return result;
        }
        return result;
    }


}
