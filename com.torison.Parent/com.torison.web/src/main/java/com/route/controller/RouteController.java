package com.route.controller;

import com.alibaba.fastjson.JSON;
import com.common.Enum.Path;
import com.model.Result;
import com.model.User;
import com.route.form.RouteListForm;
import com.torison.Route.model.Route;
import com.torison.Route.model.RoutePic;
import com.torison.RouteCollect.model.RouteCollection;
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
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;

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
    @ResponseBody
    public Result route(HttpServletRequest request, @Valid RouteForm routeForm, BindingResult bindingResult,
                        MultipartFile file1,
                        MultipartFile file2,
                        MultipartFile file3) {

      Result result = new Result();
      log.info("路线信息入参:"+JSON.toJSONString(routeForm));
      if (routeForm.getRoutename().isEmpty()||routeForm.getRoutename().equals("")){
          result.setSuccess(false);
          result.setMsg("路线名称不能为空");
      }
      /**最后使用前补全所有必须参数是否为空的判断*/
      Route route = routeForm.transTo(new Route());
      log.info("开始添加路线："+JSON.toJSONString(route));
      int routeID;
        /**
         * 添加路线并且获取保存ID
         */
      if((routeID=routeService.inserRoute(routeForm))!=0){
          log.info("主键"+routeID);
          List<MultipartFile> files = new ArrayList<>();
          files.add(file1);files.add(file2);files.add(file3);
          List<String> routePaths = new ArrayList<>();
          /**
           * 图片上传使用多线程上传
           */
          ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,100,200, TimeUnit.MICROSECONDS,new LinkedBlockingDeque<>(400),new ThreadPoolExecutor.CallerRunsPolicy());
          threadPoolExecutor.execute(
                  ()->{
                      for (MultipartFile file:files) {
                          if (file!=null) {
                              try {
                                  String fileName = UUID.randomUUID().toString();
                                  BufferedOutputStream out = new BufferedOutputStream(
                                          new FileOutputStream(new File(Path.PicURL.PicUpload + fileName + ".jpg")));
                                  routePaths.add(fileName+".jpg");
                                  out.write(file.getBytes());
                                  out.flush();
                                  out.close();
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
                  });
      }
      return result;

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
            com.model.RouteListForm routeListForm = new com.model.RouteListForm();
            routeListForm.setIntroduce(route.getRouteintroduce());
            routeListForm.setMaker(route.getRoutefromid());
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
    public String getRouteDetail(Model model, Integer routeId){
        Route route = routeService.selectRouteById(routeId);
        model.addAttribute("route",route);
        return "test/Route/ListRoute";
    }


    /**
     * 删除我制作的路线信息
     * @param routeId
     * @return
     */
    @RequestMapping(value = "/deleteRoute")
    public String deleteRoute(int routeId){
        routeService.deleteRoute(routeId);
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
            listRoute.add(routeListForm);
        }
        result.setSuccess(true);
        result.setObj(listRoute);
        return result;

    }







}
