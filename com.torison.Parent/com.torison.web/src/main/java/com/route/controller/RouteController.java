package com.route.controller;

import com.alibaba.fastjson.JSON;
import com.common.Enum.Path;
import com.model.Result;
import com.torison.Route.model.Route;
import com.torison.route.RoutePicService;
import com.torison.route.RouteService;
import com.torison.route.model.RouteForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping(value = "/route")
public class RouteController {

    private static final Log log = LogFactory.getLog(RouteController.class);

    @Autowired
    private RouteService routeService;

    @Autowired
    private RoutePicService routePicService;

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
    public Result route(HttpServletRequest request,RouteForm routeForm,
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



}
