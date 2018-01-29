package com.test.controller;

import com.Enum.Path;
import com.model.Result;
import com.model.Route;
import freemarker.template.Template;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.Date;
import java.util.*;

@Controller
public class userController {

    @RequestMapping("/index")
    public String test(Model model) {
        Map<String, Object> model2 = new HashMap<String, Object>();

        List<Route> listroute = new ArrayList<>();
        Route route = new Route();
        Route route2 = new Route();
        Route route3 = new Route();
        route.setIntroduce("老许");
        route.setMaker("张三");
        route.setPath("static/pic/3.jpg");
        route2.setIntroduce("老许");
        route2.setMaker("张三");
        route2.setPath("static/pic/3.jpg");
        route3.setIntroduce("老许");
        route3.setMaker("张三");
        route3.setPath("static/pic/3.jpg");
        listroute.add(route);
        listroute.add(route2);
        listroute.add(route3);
        model.addAttribute("routelist",listroute);
        return "/test/test1";
    }

    @RequestMapping("/listRouteMake")
    public String listRouteMaker() {
        return "test/RouteMaker";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request, String makerName, String introduce, MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(Path.PicURL.PicUpload+"1.jpg")));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }

        }
        return null;
    }

    @RequestMapping(value = "/route", method = RequestMethod.POST)
    @ResponseBody
    public Result route(HttpServletRequest request, String makerName, String introduce, MultipartFile file) {

        List<Route> listroute = new ArrayList<>();
        Route route = new Route();
        Route route2 = new Route();
        Route route3 = new Route();
        route.setIntroduce("老许");
        route.setMaker("张三");
        route.setPath("static/pic/3.jpg");
        route2.setIntroduce("老许");
        route2.setMaker("张三");
        route2.setPath("static/pic/3.jpg");
        route3.setIntroduce("老许");
        route3.setMaker("张三");
        route3.setPath("static/pic/3.jpg");
        listroute.add(route);
        listroute.add(route2);
        listroute.add(route3);
        Result result = new Result();
        result.setObj(listroute);
        result.setSuccess(true);
        return result;
    }
}