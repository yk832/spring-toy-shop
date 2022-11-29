package com.insung.core.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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


}
