package back.UserManage.controller;

import back.UserManage.dao.model.User;
import back.UserManage.service.UserService;
import back.common.model.DataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.crypto.Data;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


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
}
