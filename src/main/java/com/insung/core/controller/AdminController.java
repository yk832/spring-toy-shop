package com.insung.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @GetMapping("/index")
    public String admin() {
        log.info("admin page 호출!!!");
        return "index";
    }

}
