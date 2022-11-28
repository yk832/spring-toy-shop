package com.insung.core.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.regex.Pattern;

@Slf4j
@Controller
@RequestMapping(value = "/")
public class CommonController {
    @RequestMapping
    public String viewPage() {
        return "index";
    }



//    @PostMapping("tt")
//    public String ggg(@Validated @ModelAttribute("test") TestDto testDto, BindingResult bindingResult) {
//        log.info("Test dto");
//        if (bindingResult.hasErrors()) {
//            return "/user/test";
//        }
//
//        //문자열에 공백 혹은 특수문자가 입력된 경우
//        String pattern = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$";
//        if(!Pattern.matches(pattern, testDto.getName())){
//            System.out.println("공백 혹은 특수문자가 입력되었습니다.");
//            bindingResult.rejectValue("name", null, "공백은 불가능해");
//            return "/user/test";
//        }
//
//        if (testDto.getName().length() > 10 || testDto.getName().length() < 3) {
//            bindingResult.rejectValue("name", null, "3~10자리로 입력");
//            return "/user/test";
//        }
//
//        return "index";
//    }

}
