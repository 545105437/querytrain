package com.train.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wal
 * @date 2018/3/7
 */
@Controller
public class BaseController {

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping("/")
    public String index(){

        return "company/main";
    }

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping("/main")
    public String mainPage(){

        return "company/main";
    }

    /**
     * 跳转到关于
     * @return
     */
    @RequestMapping("/aboutUs")
    public String aboutUs(){

        return "company/aboutUs";
    }

    /**
     * 跳转到提交数据
     * @return
     */
    @RequestMapping("/submitData")
    public String submitData(){

        return "company/submitData";
    }
}
