package com.insung.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insung.core.dto.UserDto;
import com.insung.core.dto.UserSaveForm;
import com.insung.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
@Controller
@RequestMapping(value = "/shop/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/home")
    public String userHome() {
        return "/user/index";
    }

    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("user", new UserDto());
        log.info("회원가입 폼 요청");
        return "user/joinForm";
    }


    /**
     *
     * @param userSaveForm
     * @param request
     * @param response
     *
     * 회원가입시 유저권한 세팅을 위해 UserDto 객체 이외 데이터도 받아오기 위해 Map으로 받아온다.
     * - 유저 권한 설정
     * 관리자가 회원정보 관리를 할 때 같은 Mapper 를 공용으로 사용하기 위해 Map 으로 파싱한다.
     *
     * @return
     */
    @PostMapping("/join")
    public String postJoin(@Validated @ModelAttribute("user") UserSaveForm userSaveForm, BindingResult bindingResult,
                           HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.info("회원가입 요청 정보 : {} ",userSaveForm);
        log.info("objectName={}", bindingResult.getObjectName());
        log.info("target={}", bindingResult.getTarget());
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "user/joinForm";
        }

        //create UserDto instance
        UserDto userDto = new UserDto(userSaveForm);
        log.info("UserDto 정보 : {} ",userDto);
        // Object to Map
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> paramMap = objectMapper.convertValue(userDto, Map.class);
        userService.join(paramMap);
        log.info("회원가입 완료 요청 : ");

        return "redirect:/shop/v1/user/home";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ModelAndView errorHandler(Exception e, HttpServletRequest request) {
        log.info("request : {}", request);
        // TODO 아이디 , 이메일 중복체크 -> 중복일 경우 화면 데이터를 유지하면서 클라이언트에게 중복된 값이라는 메세지를 띄우도록 개발 하기..
        // TODO 떠오르는 방법 1. 세션에 저장 해놓고 가져온다?..
        return new ModelAndView("index");
    }


}
