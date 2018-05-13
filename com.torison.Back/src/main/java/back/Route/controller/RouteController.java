package back.Route.controller;

import back.Route.dao.model.Route;
import back.Route.dao.model.RoutePic;
import back.Route.service.RoutePicService;
import back.Route.service.RouteService;
import back.UserManage.dao.model.User;
import back.UserManage.service.UserService;
import back.common.model.DataGrid;
import back.common.model.Result;
import back.util.EmailSentService;
import back.util.model.MailCode;
import back.util.model.MailMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private RoutePicService routePicService;

    @Autowired
    private EmailSentService emailSentService;


    @Autowired
    private UserService userService;
    /**
     * 初始化路线信息
     * @return
     */
    @RequestMapping(value = "/toListRoute")
    public String toListRoute(){
        return "/Route/RouteManage";
    }

    /**
     * 初始化已通过路线信息--禁开
     * @return
     */
    @RequestMapping(value = "/toListRouteDelete")
    public String toListRoutePass(){
        return "/Route/RouteDeleteManage";
    }

    /**
     * 返回路线信息
     * 最后改为分页查询
     * @param route
     * @return
     */
    @RequestMapping(value = "/getRouteList")
    @ResponseBody
    public DataGrid getRouteList(Route route){
        route.setStatus(2);
        DataGrid dataGrid = new DataGrid();
        List<Route> listRoute = routeService.listRoute(route);
        dataGrid.setRows(listRoute);
        dataGrid.setTotal(listRoute.size());
        return dataGrid;
    }

    /**
     * 返回已通过路线信息
     * 最后改为分页查询
     * @param route
     * @return
     */
    @RequestMapping(value = "/getRouteListPass")
    @ResponseBody
    public DataGrid getRouteListPass(Route route){
        DataGrid dataGrid = new DataGrid();
        route.setStatus(0);
        List<Route> listRoute = routeService.listRoutePass(route);
        dataGrid.setRows(listRoute);
        dataGrid.setTotal(listRoute.size());
        return dataGrid;
    }

    /**
     * 查看路线
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "toRouteDetail")
    public String toRouteDetail(Model model,String id){
        Route route = new Route();
        route.setRouteId(Integer.parseInt(id));
        List<Route> listroute = routeService.listRoute(route);
        if (listroute.size()!=0) {
            route = listroute.get(0);
        }
        RoutePic routePic = new RoutePic();
        routePic.setRouteid(Integer.parseInt(id));
        List<RoutePic> listRoutepic = routePicService.listRoutePic(routePic);
        if (!listRoutepic.isEmpty()){
            routePic = listRoutepic.get(0);
        }
        model.addAttribute("route",route);
        model.addAttribute("routepic",routePic);
        return "/Route/RouteDetail";
    }

    /**
     * 审查
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "toAuditRoute")
    public String toAuditRoute(Model model,String id){
        Route route = new Route();
        route.setRouteId(Integer.parseInt(id));
        List<Route> listroute = routeService.listRoute(route);
        if (listroute.size()!=0) {
            route = routeService.listRoute(route).get(0);
        }
        model.addAttribute("route",route);
        return "/Route/RouteAudit";
    }

    /**
     * 禁开
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "toDeleteRoute")
    public String toDeleteRoute(Model model,String id){
        Route route = new Route();
        route.setRouteId(Integer.parseInt(id));
        List<Route> listroute = routeService.listRoute(route);
        if (listroute.size()!=0) {
            route = routeService.listRoute(route).get(0);
        }
        model.addAttribute("route",route);
        return "/Route/RouteDelete";
    }

    /**
     * 删除路线
     * @param routeId
     * @return
     */
    @RequestMapping("/deleteRoute")
    @ResponseBody
    public Result deleteRoute(String msg, String auditStatus, String routeId){
        int intRouteId = Integer.parseInt(routeId);
        int intStatus = Integer.parseInt(auditStatus);
        Result result = new Result();
        Route routeForQuest = new Route();
        routeForQuest.setRouteId(intRouteId);
        Route route = routeService.listRoutePass(routeForQuest).get(0);
        User userForQuest = new User();
        userForQuest.setUserId(Integer.parseInt(route.getRouteFromId()));
        User user = userService.listUser(userForQuest).get(0);
        if (routeService.updateRoute(intStatus, intRouteId)){

                emailSentService.sendSimpleEmail(user.getEmail(),"【背包出行】路线禁开通知","您的路线被禁开，原因："+msg);
                result.setRespMsg("路线禁开操作成功");
                result.setSuccess(true);
                return result;

        }

        result.setRespMsg("审核失败，请联系管理员");
        result.setSuccess(false);
        return result;
    }

    /**
     * 审核通过
     * @param msg
     * @return
     */
    @RequestMapping("/auditRoute")
    @ResponseBody
    public Result auditRoute(String msg, String auditStatus, String routeId){

        int intRouteId = Integer.parseInt(routeId);
        int intStatus = Integer.parseInt(auditStatus);
        Result result = new Result();
        Route routeForQuest = new Route();
        routeForQuest.setRouteId(intRouteId);
        Route route = routeService.listRoute(routeForQuest).get(0);
        User userForQuest = new User();
        userForQuest.setUserId(Integer.parseInt(route.getRouteFromId()));
        User user = userService.listUser(userForQuest).get(0);
        if (routeService.updateRoute(intStatus, intRouteId)){
            if (intStatus == 0) {
                emailSentService.sendSimpleEmail(user.getEmail(),"【背包出行】路线审核通知","您的路线审核已经通过："+msg);
                result.setRespMsg("审核通过操作成功");
                result.setSuccess(true);
                return result;
            }
            if (intStatus == 1){
                emailSentService.sendSimpleEmail(user.getEmail(),"【背包出行】路线审核通知","您的路线审核未通过："+msg+"请及时修改路线信息");
                result.setRespMsg("审核不通过操作成功");
                result.setSuccess(true);
                return result;
            }
        }

        result.setRespMsg("审核失败，请联系管理员");
        result.setSuccess(false);
        return result;
    }
}
