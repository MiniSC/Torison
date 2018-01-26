package com.test.controller;

import freemarker.template.Template;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class userController {

    @RequestMapping("/index")
    public String test(Model model){
        Map<String, Object> model2 = new HashMap<String, Object>();

        model2.put("message", "这是测试的内容。。。");
        model2.put("toUserName", "张三");
        model2.put("fromUserName", "老许");

        model.addAttribute("model",model2);
        return "/test/test1";
    }

    @RequestMapping("/listRouteMake")
    public String listRouteMaker(){
        return "test/RouteMaker";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(HttpServletRequest request,String makerName, String introduce, MultipartFile file){

        try{
            //上传目录地址
            String path = userController.class.getClassLoader().getResource("").getPath();
            path = path+"/static/pic";
            String uploadDir =path; /*"C:/Users/dongjj/Desktop/Torison/com.torison.Parent/com.torison.web/src/main/resources/static/pic";*/
            File dir = new File(uploadDir);
            if (!dir.exists()){
                dir.mkdir();
            }
            String filename= file.getOriginalFilename();
            File serverFile = new File(uploadDir+filename);
            file.transferTo(serverFile);
        }catch (IOException e){
            e.printStackTrace();
        }

        return "上传成功";
    }

}
