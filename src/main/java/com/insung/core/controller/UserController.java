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
@RequestMapping(value = "/shop/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

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
        //문자열에 공백 혹은 특수문자가 입력된 경우
        String pattern = "^[a-zA-Z]{1}[a-z0-9_]{4,15}$";
        if(!Pattern.matches(pattern, userSaveForm.getUser_id())){
            bindingResult.rejectValue("user_id", null, "5~15자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.");
            return "/user/joinForm";
        }

//        if (userSaveForm.getUser_id().length() > 10 || userSaveForm.getUser_id().length() < 3) {
//            bindingResult.rejectValue("name", null, "3~10자리로 입력");
//            return "/user/joinForm";
//        }



        //create UserDto instance
        UserDto userDto = new UserDto(userSaveForm);
        log.info("UserDto 정보 : {} ",userDto);
        // Object to Map
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> paramMap = objectMapper.convertValue(userDto, Map.class);
//        userService.join(paramMap);
        log.info("회원가입 완료 요청 : ");

//        Item savedItem = itemRepository.save(item);
//        redirectAttributes.addAttribute("itemId", savedItem.getId());
//        redirectAttributes.addAttribute("status", true);
//        return "redirect:/validation/v3/items/{itemId}";
        return "/user/index";
    }



}
