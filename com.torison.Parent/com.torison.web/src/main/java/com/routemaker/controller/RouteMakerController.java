package com.routemaker.controller;

import com.common.Enum.Path;
import com.model.Result;
import com.model.RouteListForm;
import com.torison.Route.model.Route;
import com.torison.route.RoutePicService;
import com.torison.route.RouteService;
import com.torison.route.model.RouteForm;
import com.torison.routeMaker.model.RouteMaker;
import com.torison.routemaker.RouteMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/routeMaker")
public class RouteMakerController {

    @Autowired
    private RouteMakerService routeMakerService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private RoutePicService routePicService;

    @RequestMapping(value = "listInsertMaker")
    public String listInsertMaker(HttpServletRequest request){
        return "/test/Maker/RouteMaker";

    }

    /**
     * 保存申请信息
     * @param request
     * @param makerName
     * @param introduce
     * @param file1
     * @param file2
     * @return
     */
    @RequestMapping(value="saveMaker")
    public String saveMaker(HttpServletRequest request, String makerName, String introduce,
                            MultipartFile file1,
                            MultipartFile file2) {
        Result result = new Result();
        List<String> picPath = new ArrayList<>();
        List<MultipartFile> files = new ArrayList<>();
        files.add(file1);files.add(file2);
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
                    picPath.add(fileName + ".jpg");
                    out.flush();
                    out2.flush();
                    out.close();
                    out2.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    result.setMsg("上传失败," + e.getMessage());
                    return "/test/Maker/MakerUploadFail";
                } catch (IOException e) {
                    e.printStackTrace();
                    result.setMsg("上传失败," + e.getMessage());
                    return "/test/Maker/MakerUploaded";
                }
            }
        }
            RouteMaker routeMaker = new RouteMaker();
            routeMaker.setIntroduce(introduce);
            routeMaker.setUserid(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
                routeMaker.setPic1(picPath.get(0));
                routeMaker.setPic2(picPath.get(1));
            routeMaker.setStatus("2");
            routeMakerService.insertMaker(routeMaker);
            result.setSuccess(true);
        return "/test/Maker/MakerUploaded";
    }



}
