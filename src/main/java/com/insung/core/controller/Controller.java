package com.insung.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping(value = "/con/main")
    public String viewPage() {
        log.info("view page 호출!!!@@@@@@@@@@@@@@@@@@sss");
        return "index";
    }

    @GetMapping("/admin/index")
    public String admin() {
        log.info("admin 호출!!!@@@@@@@@@@@@@@@@@@sss");
        return "index";
    }

}
