package com.insung.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping(value = "/con/main")
    public String viewPage() {
        System.out.println("view page 호출!!!@@@@@@@@@@@@@@@@@@sss");
        return "index";
    }

    @RequestMapping(value = "/admin/index")
    public String admin() {
        System.out.println("admin 호출!!!@@@@@@@@@@@@@@@@@@sss");
        return "index";
    }

}
