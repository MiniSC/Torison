package back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = "/toIndex")
    public String toIndex(Model model){
        return "main/index";
    }
    @RequestMapping(value = "/")
    public String ii(){
        return  "index";
    }
}
