package com.test.controller;

import com.common.Enum.Path;
import com.model.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@Controller
public class userController {

    @RequestMapping("/index")
    public String test(Model model) {
        Map<String, Object> model2 = new HashMap<String, Object>();

        return "/test/test1";
    }

    @RequestMapping("/listRouteMake")
    public String listRouteMaker() {
        return "test/Route/MakeRoute";
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

        return null;
    }
}