package com.insung.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping("/join")
    public String userJoin() {
        log.info("user join 호출!!!@@@@@@@@@@@@@@@@@@sss");
        return "user/join";
    }

}
