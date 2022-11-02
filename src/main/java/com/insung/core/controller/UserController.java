package com.insung.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping("/join")
    public String getJoin() {
        log.info("회원가입 폼 요청");
        return "user/join";
    }

    @PostMapping("/join")
    public String postJoin() {
        log.info("회원가입 완료 요청");
        return "user/join";
    }

}
