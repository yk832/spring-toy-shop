package com.insung.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = "/common")
public class CommonController {

    @RequestMapping(value = "/main")
    public String viewPage() {
        log.info("common view page 호출!!!@@@@@@@@@@@@@@@@@@sss");
        return "index";
    }
}
