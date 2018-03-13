package com.test.controller;

import com.common.Enum.Path;
import com.model.Result;
import com.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.*;
import java.util.*;

@Controller
public class MainController {

    @RequestMapping("/index")
    public String test(Model model,HttpServletRequest request) {

        if (request.getSession().getAttribute("userid")!=null){
        return "/test/index";}
        return "/login/toLogin";
    }

    @RequestMapping("/listRouteMake")
    public String listRouteMaker() {
        return "test/Route/MakeRoute";
    }
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request, String makerName, String introduce, MultipartFile file) {

       /* if (!file.isEmpty()) {
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

        }*/
        return null;
    }

    @RequestMapping(value = "/route", method = RequestMethod.POST)
    @ResponseBody
    public Result route(HttpServletRequest request, String makerName, String introduce, MultipartFile file) {

        return null;
    }
}
