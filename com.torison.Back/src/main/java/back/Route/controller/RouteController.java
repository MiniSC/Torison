package back.Route.controller;

import back.Route.dao.model.Route;
import back.Route.dao.model.RoutePic;
import back.Route.service.RoutePicService;
import back.Route.service.RouteService;
import back.common.model.DataGrid;
import back.common.model.Result;
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

    /**
     * 初始化路线信息
     * @return
     */
    @RequestMapping(value = "/toListRoute")
    public String toListRoute(){
        return "/Route/RouteManage";
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
        DataGrid dataGrid = new DataGrid();
        List<Route> listRoute = routeService.listRoute(route);
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
            route = routeService.listRoute(route).get(0);
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
}
