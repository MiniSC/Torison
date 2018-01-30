package com.route.controller;

import com.alibaba.fastjson.JSON;
import com.model.Result;
import com.route.model.RouteForm;
import com.torison.model.Route;
import com.torison.route.RouteService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/route")
public class RouteController {

    private static final Log log = LogFactory.getLog(RouteController.class);

    @Autowired
    private RouteService routeService;

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
      /*。。。。。参数判断是否为空*/
      Route route = routeForm.transTo(new Route());
      log.info("开始添加路线："+JSON.toJSONString(route));
      if(routeService.inserRoute(route)!=0){
          result.setSuccess(true);
          result.setMsg("添加成功");
      }


      return result;
    }
}
