package com.routemaker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/routeMaker")
public class RouteMakerController {

    @RequestMapping(value = "listInsertMaker")
    public String listInsertMaker(HttpServletRequest request){
        return "/test/Maker/RouteMaker";

    }

}
