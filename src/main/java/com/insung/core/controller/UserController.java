package com.insung.core.controller;

import com.insung.core.dto.UserDto;
import com.insung.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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


    /**
     *
     * @param pramMap
     * @param request
     * @param response
     *
     * 회원가입시 유저권한 세팅을 위해 UserDto 객체 이외 데이터도 받아오기 위해 Map으로 받아온다.
     * - 유저 권한 설정
     *
     * @return
     */
    @PostMapping("/join")
    public ModelAndView postJoin(UserDto userDto, @RequestParam Map<String,Object> pramMap, HttpServletRequest request, HttpServletResponse response) throws Exception{
        log.info("회원가입 정보 : {} ",pramMap.toString());
        userService.join(pramMap);
        log.info("회원가입 완료 요청 : ");
        return null;
    }
}
