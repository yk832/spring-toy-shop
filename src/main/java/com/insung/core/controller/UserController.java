package com.insung.core.controller;

import com.insung.core.dto.UserDto;
import com.insung.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/join")
    public String getJoin() {
        log.info("회원가입 폼 요청");
        return "user/join";
    }

    @PostMapping("/join")
    public ModelAndView postJoin(UserDto user, HttpServletRequest request, HttpServletResponse response) {
        log.info("user : {} " + user);
        log.info("회원가입 완료 요청");
        return null;
    }

}
