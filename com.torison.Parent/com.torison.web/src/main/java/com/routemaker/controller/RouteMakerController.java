package com.routemaker.controller;

import com.common.Enum.Path;
import com.torison.routeMaker.model.RouteMaker;
import com.torison.routemaker.RouteMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/routeMaker")
public class RouteMakerController {

    @Autowired
    private RouteMakerService routeMakerService;

    @RequestMapping(value = "listInsertMaker")
    public String listInsertMaker(HttpServletRequest request){
        return "/test/Maker/RouteMaker";

    }

    @RequestMapping(value="saveMaker")
    public String saveMaker(HttpServletRequest request, String makerName, String introduce,
                            MultipartFile file1,
                            MultipartFile file2) {
        List<String> picPath = new ArrayList<>();
        List<MultipartFile> files = new ArrayList<>();
        files.add(file1);files.add(file2);
        for (MultipartFile file:files) {
            if (!file.isEmpty()) {
                try {
                    String fileName = UUID.randomUUID().toString();
                    BufferedOutputStream out = new BufferedOutputStream(
                            new FileOutputStream(new File(Path.PicURL.PicUpload + fileName + ".jpg")));
                    out.write(file.getBytes());
                    picPath.add(fileName + ".jpg");
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
        }
            RouteMaker routeMaker = new RouteMaker();
            routeMaker.setIntroduce(introduce);
            routeMaker.setUserid(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
                routeMaker.setPic1(picPath.get(0));
                routeMaker.setPic2(picPath.get(1));
            routeMaker.setIntroduce(introduce);
            routeMakerService.insertMaker(routeMaker);
        return null;
    }

}
