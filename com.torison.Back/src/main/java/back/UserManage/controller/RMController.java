package back.UserManage.controller;

import back.UserManage.dao.model.RouteMaker;
import back.UserManage.dao.model.RouteMakerForm;
import back.UserManage.service.RMService;
import back.common.model.ConfirmStatus;
import back.common.model.DataGrid;
import back.common.model.Result;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;



@Controller
@RequestMapping(value = "/usermanage")
public class RMController {

    public static final Log log = LogFactory.getLog(RMController.class);

    @Autowired
    private RMService rmService;

    @RequestMapping(value = "/toUndoPass")
    public String toUndoPass(){
        return "/Manage/UndoPass";
    }
    /**
     * 查询方法，后面要改成分页形式
     * 这里方法名需要修改为listNotpassedApply
     * 目前使用Example进行动态查询
     * @param routeMaker
     * @return
     */
    @RequestMapping(value = "/listApply")
    @ResponseBody
    public DataGrid listApply(RouteMaker routeMaker){
        DataGrid dataGrid = new DataGrid();
        routeMaker.setUserStatus("2");
        List<RouteMakerForm> ls = rmService.findMakerByConditioins(routeMaker);
        dataGrid.setTotal(ls.size());
        dataGrid.setRows(ls);
        return dataGrid;
    }

    /**
     * 查询方法，后面要改成分页形式
     * 这里方法名需要修改为
     * 目前使用Example进行动态查询
     * @param routeMaker
     * @return
     */
    @RequestMapping(value = "/listPassedApply")
    @ResponseBody
    public DataGrid listPassedApply(RouteMaker routeMaker){
        DataGrid dataGrid = new DataGrid();
        routeMaker.setUserStatus("0");
        List<RouteMakerForm> ls = rmService.findMakerByConditioins(routeMaker);
        dataGrid.setTotal(ls.size());
        dataGrid.setRows(ls);
        return dataGrid;
    }

    /**
     * 初始化详情审核
     * @param id
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/toListDetail")
    public String toListDetail(String id, Model model, HttpServletRequest request){
        /**
         *
         * 查询用户信息和审核照片然后显示
         */
        HttpSession session = request.getSession();
        RouteMaker routeMaker = new RouteMaker();
        routeMaker.setUserId(Integer.parseInt(id));
        List<RouteMakerForm> ls = rmService.findMakerByConditioins(routeMaker);
        model.addAttribute("routeMakerForm",ls.get(0));
        session.setAttribute("userid",routeMaker.getUserId());
        return "/Manage/ListDetail";
    }

    /**
     * 通过审核
     * @param id
     * @return
     */
    @RequestMapping(value = "/confirmPass")
    @ResponseBody
    public Result confirm(String id){
        return  rmService.giveAuthentication(id, ConfirmStatus.PASS.code());
    }

    /**
     * 驳回审核
     * @param id
     * @return
     */
    @RequestMapping(value = "/confirmRej")
    @ResponseBody
    public Result confirmRej(String id){
        return  rmService.giveAuthentication(id, ConfirmStatus.REJECT.code());
    }

    /**
     * 撤销权限
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteAuth")
    @ResponseBody
    public Result deleteAuth(String id){
        return  rmService.giveAuthentication(id, ConfirmStatus.DELETE.code());
    }



}
