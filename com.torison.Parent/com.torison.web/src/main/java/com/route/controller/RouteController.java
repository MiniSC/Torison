package com.route.controller;

import com.alibaba.fastjson.JSON;
import com.common.Enum.Path;
import com.model.Result;
import com.model.Tourists;
import com.order.model.OrderStatus;
import com.route.form.RouteDetailForm;
import com.route.form.RouteListForm;
import com.route.routeEnum.RouteStatus;
import com.torison.ClickNum.ClickNum;
import com.torison.ClickNumService.ClickNumService;
import com.torison.Order.OrderService;
import com.torison.Order.model.Order;
import com.torison.Route.model.BestEndAddress;
import com.torison.Route.model.Route;
import com.torison.Route.model.RoutePic;
import com.torison.RouteCollect.model.RouteCollection;
import com.torison.User.UserService;
import com.torison.User.model.User;
import com.torison.api.PayServiceApi;
import com.torison.api.model.PayEntity;
import com.torison.api.model.PayEnum;
import com.torison.route.RoutePicService;
import com.torison.route.RouteService;
import com.torison.route.model.RouteForm;
import com.torison.routeCollection.RouteCollectionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/route")
public class RouteController {

    private static final Log log = LogFactory.getLog(RouteController.class);

    @Autowired
    private RouteService routeService;

    @Autowired
    private RoutePicService routePicService;

    @Autowired
    private RouteCollectionService routeCollectionService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayServiceApi payServiceApi;

    @Autowired
    private ClickNumService clickNumService;


    /**
     * 添加路线信息，上传图片到本地
     * @param request
     * @param routeForm
     * @param file1
     * @param file2
     * @param file3
     * @return
     */
    @RequestMapping(value = "/addRoute", method = RequestMethod.POST)
    public String route(HttpServletRequest request, @Valid RouteForm routeForm, BindingResult bindingResult,
                        MultipartFile file1,
                        MultipartFile file2,
                        MultipartFile file3) {

      Result result = new Result();
      log.info("路线信息入参:"+JSON.toJSONString(routeForm));
      if (routeForm.getRoutename().isEmpty()||routeForm.getRoutename().equals("")){
          result.setSuccess(false);
          result.setMsg("路线名称不能为空");
      }
      Route route = routeForm.transTo(new Route());
      route.setStatus(RouteStatus.WAITE.getCode());
      route.setRoutelastpersonnum(route.getRoutemaxpersonnum());
      route.setRoutefromid(request.getSession().getAttribute("userid").toString());
      log.info("开始添加路线："+JSON.toJSONString(route));
      int routeID;
        /**
         * 添加路线并且获取保存ID
         */
      if((routeID=routeService.inserRoute(route))!=0){
          log.info("主键"+routeID);
          List<MultipartFile> files = new ArrayList<>();
          files.add(file1);files.add(file2);files.add(file3);
          List<String> routePaths = new ArrayList<>();
                      for (MultipartFile file:files) {
                          if (!file.isEmpty()) {
                              try {
                                  String fileName = UUID.randomUUID().toString();
                                  BufferedOutputStream out = new BufferedOutputStream(
                                          new FileOutputStream(new File(Path.PicURL.PicUpload + fileName + ".jpg")));
                                  BufferedOutputStream out2 = new BufferedOutputStream(
                                          new FileOutputStream(new File(Path.PicURL.PicUpload2 + fileName + ".jpg")));
                                  out.write(file.getBytes());
                                  out2.write(file.getBytes());
                                  routePaths.add(fileName + ".jpg");
                                  out.flush();
                                  out2.flush();
                                  out.close();
                                  out2.close();
                              } catch (FileNotFoundException e) {
                                  e.printStackTrace();
                              } catch (IOException e) {
                                  e.printStackTrace();
                              }
                          }
                      }
                      if(routePicService.inserPic(routePaths,routeID).isSuccess()){
                          result.setSuccess(true);
                          result.setMsg("添加成功");
                      }

      }
      return "/test/Route/RouteUploadEnd";

    }

    /**
     * 获取路线信息
     * @param request
     * @param makerName
     * @param file
     * @return
     */
    @RequestMapping(value = "/getRoute", method = RequestMethod.POST)
    @ResponseBody
    public Result route(HttpServletRequest request, String makerName, MultipartFile file) {

        Result result = new Result();
        List<Route> listroute = routeService.queryAllRoute();

        List<com.model.RouteListForm> listToList = new ArrayList<>();
        for (Route route:listroute){
            //判断路线是否通过审核
            if (route.getStatus() == RouteStatus.PASS.getCode()) {
                //获取路线制作者信息等进行显示
                User user = userService.getUserById(route.getRoutefromid());
                Integer times = clickNumService.getTimeByRouteId(route.getRouteid());
                com.model.RouteListForm routeListForm = new com.model.RouteListForm();
                routeListForm.setMakername(user.getUsername());
                routeListForm.setRoutename(route.getRoutename());
                routeListForm.setRouteid(route.getRouteid());
                routeListForm.setClicktime(times);
                routeListForm.setPath(routePicService.selectPicByID(route.getRouteid()).getRoutepic1());
                listToList.add(routeListForm);
            }
        }
        result.setSuccess(true);
        result.setObj(listToList);
        return result;
    }/**
     * 获取热门路线信息
     * @param request
     * @param makerName
     * @param file
     * @return
     */
    @RequestMapping(value = "/getHotRoute", method = RequestMethod.POST)
    @ResponseBody
    public Result getHotRoute(HttpServletRequest request, String makerName, MultipartFile file) {

        Result result = new Result();
        List<Route> listroute = routeService.queryHotRoute();

        List<com.model.RouteListForm> listToList = new ArrayList<>();
        for (Route route:listroute){
                //获取路线制作者信息等进行显示
                User user = userService.getUserById(route.getRoutefromid());
                Integer times = clickNumService.getTimeByRouteId(route.getRouteid());
                com.model.RouteListForm routeListForm = new com.model.RouteListForm();
                routeListForm.setMakername(user.getUsername());
                routeListForm.setRoutename(route.getRoutename());
                routeListForm.setRouteid(route.getRouteid());
                routeListForm.setClicktime(times);
                routeListForm.setPath(routePicService.selectPicByID(route.getRouteid()).getRoutepic1());
                listToList.add(routeListForm);

        }
        result.setSuccess(true);
        result.setObj(listToList);
        return result;
    }

   /* *//**
     * 跳转到路线详情界面
     * @return
     *//*
    @RequestMapping("/toListRouteDetail")
    public String toListRouteDetail(){
        return "test/Route/ListRoute";
    }*/
    /**
     * 查看路线的详情
     * @param model
     * @param routeId
     * @return
     */
    @RequestMapping(value = "/listRouteDetail")
    public String togetRouteDetail(Model model, Integer routeId){
        clickNumService.updateTime(routeId);
        model.addAttribute("routeid",routeId);
        return "test/Route/ListRoute";
    }
    /**
     * 查看路线的详情信息
     * @param model
     * @param routeId
     * @return
     */
    @RequestMapping(value = "/listRouteDetailMsg")
    @ResponseBody
    public Result getRouteDetail(Model model, Integer routeId){
        Route route = routeService.selectRouteById(routeId);
        RoutePic routePic = routePicService.selectPicByID(routeId);
        User user = userService.getUserById(route.getRoutefromid());
        RouteDetailForm routeDetailForm = new RouteDetailForm();
        routeDetailForm.setPic1(routePic.getRoutepic1());
        routeDetailForm.setPic2(routePic.getRoutepic2());
        routeDetailForm.setPic3(routePic.getRoutepic3());
        routeDetailForm.setRouteid(route.getRouteid().toString());
        routeDetailForm.setRouteendaddress(route.getRouteendaddress());
        routeDetailForm.setRoutefromaddress(route.getRoutefromaddress());
        routeDetailForm.setRouteintroduce(route.getRouteintroduce());
        routeDetailForm.setRoutelastperson(route.getRoutelastpersonnum().toString());
        routeDetailForm.setRoutemaxperson(route.getRoutemaxpersonnum().toString());
        routeDetailForm.setRoutename(route.getRoutename());
        routeDetailForm.setRoutemakername(user.getUsername());
        routeDetailForm.setRouteprice(route.getRouteneedmoney().toString());
        routeDetailForm.setRoutetime(route.getDeposite());
        routeDetailForm.setChatconsult(route.getChatconsult());
        routeDetailForm.setConditionoverleaf(route.getConditionoverleaf());
        routeDetailForm.setDeadline(route.getDeadline());
        List<RouteDetailForm> routeDetailForms = new LinkedList<>();
        routeDetailForms.add(routeDetailForm);
        Result result = new Result();
        result.setSuccess(true);
        result.setObj(routeDetailForms);
        return result;
    }


    /**
     * 删除我制作的路线信息
     * @param routeId
     * @return
     */
    @RequestMapping(value = "/deleteRoute")
    public String deleteRoute(int routeId){
        routeService.deleteRoute(routeId);
        Order order = new Order();
        order.setRouteid(routeId);
        List<Order> orderList = orderService.listOrderByRouteId(routeId);
        for (Order order1 : orderList) {
            if (order1.getStatus().equals(OrderStatus.PAYED)){
                User user = userService.getUserById(order1.getUserid().toString());
                Route route = routeService.selectRouteById(order1.getRouteid());
                PayEntity payEntity = new PayEntity();
                payEntity.setStatus(PayEnum.PayStatus.INCREASE);
                double allMoney = route.getRouteneedmoney() * order1.getNum();
                payEntity.setMoney(allMoney);
                payEntity.setAccount(user.getAccount());
                payServiceApi.modifyMoney(payEntity);
            }
        }
        return "test/Route/ListMyRoute";
    }


    /**
     * 跳转到我收藏的路线信息界面
     * @return
     */
    @RequestMapping(value = "/tolistCollect")
    public String toListCollect(){
        return "test/RouteCollection/RouteCollection";
    }

    /**
     * 显示我收藏的路线信息
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/listCollectRoute")
    @ResponseBody
    public Result listCollectRoute(HttpServletRequest request, Model model){

        Result result = new Result();
        HttpSession session = request.getSession();
        List<RouteCollection> listCollection = routeCollectionService.listRouteCollection(Integer.parseInt(session.getAttribute("userid").toString()));
        List<RouteListForm> listRoute = new ArrayList<>();
        for (RouteCollection routeCollection : listCollection){
            RouteListForm routeListForm = new RouteListForm();
            Route route = routeService.selectRouteById(routeCollection.getRouteid());
            RoutePic routePic = routePicService.selectPicByID(routeCollection.getRouteid());
            routeListForm.setPic(routePic.getRoutepic1());
            routeListForm.setRoutefrom(route.getRoutefromaddress());
            routeListForm.setRouteend(route.getRouteendaddress());
            routeListForm.setRouteID(route.getRouteid());
            routeListForm.setRoutename(route.getRoutename());
            listRoute.add(routeListForm);
        }
        result.setSuccess(true);
        result.setObj(listRoute);
        return result;
    }


    /**
     * 添加路线的收藏
     * @param request
     * @param routeid
     * @return
     */
    @RequestMapping(value = "/addCollectRoute")
    @ResponseBody
    public Result addCollectRoute(HttpServletRequest request,String routeid){

        System.out.println(routeid);
        HttpSession session = request.getSession();
        Integer userid = Integer.parseInt(session.getAttribute("userid").toString());
        RouteCollection routeCollection = new RouteCollection();
        routeCollection.setRouteid(Integer.parseInt(routeid));
        routeCollection.setUserid(userid);
        Result result = new Result();
        if (routeCollectionService.listRouteCollectionByIDs(userid,Integer.parseInt(routeid))!=null){
            result.setSuccess(false);
            result.setMsg("请不要重复添加");
            return result;
        }
        routeCollectionService.addRouteCollection(routeCollection);
        result.setSuccess(true);
        result.setMsg("添加收藏成功");
        return result;
    }

    /**
     * 删除路线收藏信息
     * @param request
     * @param routeId
     * @return
     */
    @RequestMapping(value = "/deleteCollectRoute")
    public String deleteCollectRoute(HttpServletRequest request , String routeId) {
        System.out.println(routeId);
        System.out.println(request.getSession().getAttribute("userid").toString());
        routeCollectionService.deleteRouteCollection(Integer.parseInt(request.getSession().getAttribute("userid").toString()), Integer.parseInt(routeId));
        return "test/RouteCollection/RouteCollection";
    }

    /**
     * 初始化我制作的路线界面
     * @return
     */
    @RequestMapping(value = "/toGetMyRoute")
    public String toGetMyRoute(){
        return "test/Route/ListMyRoute";
    }

    /**
     * 获取我制作的路线信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/getMyRoute")
    @ResponseBody
    public Result getMyRoute(HttpServletRequest request ){
        Result result = new Result();
        HttpSession session = request.getSession();
        List<RouteListForm> listRoute = new ArrayList<>();
        List<Route> listRouteFromDB = routeService.selectRouteByMakerId(Integer.parseInt(session.getAttribute("userid").toString()));
        for (Route route : listRouteFromDB){
            RouteListForm routeListForm = new RouteListForm();
            RoutePic routePic = routePicService.selectPicByID(route.getRouteid());
            routeListForm.setPic(routePic.getRoutepic1());
            routeListForm.setRoutefrom(route.getRoutefromaddress());
            routeListForm.setRouteend(route.getRouteendaddress());
            routeListForm.setRouteID(route.getRouteid());
            routeListForm.setRoutename(route.getRoutename());
            List<Order> orderList = orderService.listOrderByRouteIdAndStatus(route.getRouteid(),OrderStatus.PAYED);
            int allnum = 0;
            for (Order order : orderList) {
                allnum+=order.getNum();
            }
            double allmoney = allnum * route.getRouteneedmoney();
            routeListForm.setAllmoney(allmoney);
            if (RouteStatus.PASS.getCode() == route.getStatus()){
                routeListForm.setStatusMsg(RouteStatus.PASS.getDesc());
            }
            if (RouteStatus.REJ.getCode() == route.getStatus()){
                routeListForm.setStatusMsg(RouteStatus.REJ.getDesc());
            }
            if (RouteStatus.WAITE.getCode() == route.getStatus()){
                routeListForm.setStatusMsg(RouteStatus.WAITE.getDesc());
            }
            if (RouteStatus.STOP.getCode() == route.getStatus()){
                routeListForm.setStatusMsg(RouteStatus.STOP.getDesc());
            }
            listRoute.add(routeListForm);
        }
        result.setSuccess(true);
        result.setObj(listRoute);
        return result;

    }

    /**
     * 查询最新发布的十条路线
     * @return
     */
    @RequestMapping("/getNewRoute")
    @ResponseBody
    public Result getNewRoute(){
        List<Route> newRoutes = routeService.queryTopTenRoute();
        Result result = new Result();
        result.setObj(newRoutes);
        result.setSuccess(true);
        return result;
    }

    /**
     * 查询最热门的十条路线
     * @return
     */
    @RequestMapping("/getBestEndRoute")
    @ResponseBody
    public Result getBestEndRoute(){
        List<BestEndAddress> newRoutes = routeService.selectBestEndRoute();
        Result result = new Result();
        result.setObj(newRoutes);
        result.setSuccess(true);
        return result;
    }

    /**
     * 初始化路线信息
     * @param routeEndAddress
     * @param model
     * @return
     */
    @RequestMapping("/getRoutesByend")
    public String  getRoutesByend(String routeEndAddress,Model model){
        model.addAttribute("routeendaddress", routeEndAddress);
        return "test/Route/listRoutebyend";
    }

    /**
     * 初始化路线列表信息
     * @param routeEndAddress
     * @return
     */
    @RequestMapping("/getRouteslist")
    @ResponseBody
    public Result getRouteslist(String routeEndAddress){
        Result result = new Result();
        List<Route> routes = routeService.listRoutebyendaddress(routeEndAddress);
        //这里使用了routesform，主要为了显示，不想再多写个bean了
        List<RouteListForm> routesform = new LinkedList<>();
        for (Route route:routes){
            RouteListForm routeListForm = new RouteListForm();
            RoutePic routePic = routePicService.selectPicByID(route.getRouteid());
            routeListForm.setRouteID(route.getRouteid());
            routeListForm.setPic(routePic.getRoutepic1());
            routeListForm.setRouteend(route.getRouteendaddress());
            routeListForm.setRoutefrom(route.getRoutefromaddress());
            routeListForm.setRoutename(route.getRoutename());
            routesform.add(routeListForm);
        }
        result.setSuccess(true);
        result.setObj(routesform);
        return result;
    }

    /**
     * 初始化修改路线信息
     * @return
     */
    @RequestMapping(value = "/toUpdateRoute")
    public String toUpdateRoute(Model model,String routeId){

        Route route = routeService.selectRouteById(Integer.parseInt(routeId));
        model.addAttribute("route",route);
        return "test/Route/UpdateRoute";
    }

    /**
     * 更新路线信息
     * @param
     * @return
     */
    @RequestMapping("/updateRoute")
    @ResponseBody
    public Result getRouteslist( @Valid RouteForm routeForm,HttpServletRequest request,
                                 MultipartFile file1,
                                 MultipartFile file2,
                                 MultipartFile file3){
        Result result = new Result();
        log.info("路线信息入参:"+JSON.toJSONString(routeForm));
        if (routeForm.getRoutename().isEmpty()||routeForm.getRoutename().equals("")){
            result.setSuccess(false);
            result.setMsg("路线名称不能为空");
        }
        Route route = routeForm.transTo(new Route());
        route.setStatus(RouteStatus.WAITE.getCode());
        route.setRoutelastpersonnum(route.getRoutemaxpersonnum());
        route.setRoutefromid(request.getSession().getAttribute("userid").toString());
        log.info("开始更新路线："+JSON.toJSONString(route));
        routeService.updateRoute(route);
            List<MultipartFile> files = new ArrayList<>();
            files.add(file1);files.add(file2);files.add(file3);
            List<String> routePaths = new ArrayList<>();
            for (MultipartFile file:files) {
                if (!file.isEmpty()) {
                    try {
                        String fileName = UUID.randomUUID().toString();
                        BufferedOutputStream out = new BufferedOutputStream(
                                new FileOutputStream(new File(Path.PicURL.PicUpload + fileName + ".jpg")));
                        BufferedOutputStream out2 = new BufferedOutputStream(
                                new FileOutputStream(new File(Path.PicURL.PicUpload2 + fileName + ".jpg")));
                        out.write(file.getBytes());
                        out2.write(file.getBytes());
                        routePaths.add(fileName + ".jpg");
                        out.flush();
                        out2.flush();
                        out.close();
                        out2.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
       routePicService.updatePicById(routePaths,route.getRouteid());

        result.setSuccess(true);
        return result;
    }

    @RequestMapping("/toTourists")
    public String toTourists(String routeId, Model model){
        int intRouteId = Integer.parseInt(routeId);
        Route route = routeService.selectRouteById(intRouteId);
        model.addAttribute("routeName",route.getRoutename());
        model.addAttribute("routeId",routeId);
        return "test/Route/Tourists";
    }

    @RequestMapping("/getTourists")
    @ResponseBody
    public Result getTourists(String routeId){
        //获取路线订单信息
        int intRouteId = Integer.parseInt(routeId);
        List<Order> orderList = orderService.listOrderByRouteId(intRouteId);
        //订单用户信息
        List<Tourists> touristsList = new ArrayList<>();
        orderList.forEach(order -> {
            Tourists tourists = new Tourists();
            User user = userService.getUserById(order.getUserid().toString());
            tourists.setAccount(user.getAccount());
            tourists.setName(user.getUsername());
            if (order.getStatus().equals(OrderStatus.PAYED)) {
                tourists.setPaystatus("已支付");
            }
            if (order.getStatus().equals(OrderStatus.UNPAY)){
                tourists.setPaystatus("未支付");
            }
            touristsList.add(tourists);
        });
        Result result = new Result();
        result.setObj(touristsList);
        result.setSuccess(true);
        return result;
    }





}
