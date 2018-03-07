package com.train.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wal
 * @date 2018/3/7
 */
@Controller
public class BaseController {

    @RequestMapping("/main")
    public String mainPage(){

        return "main";
    }

    @RequestMapping("/aboutUs")
    public String aboutUs(){

        return "aboutUs";
    }

    @RequestMapping("/submitData")
    public String submitData(){

        return "submitData";
    }
}
