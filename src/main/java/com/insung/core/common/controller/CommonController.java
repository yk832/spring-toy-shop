package com.insung.core.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Pattern;

@Slf4j
@Controller
@RequestMapping(value = "/")
public class CommonController {
    @RequestMapping
    public String viewPage() {
        return "index";
    }

    @GetMapping("table")
    public String dataTableTest() {
        return "user/dataTable";
    }

    @GetMapping("login")
    public String login(@RequestParam(value = "error", required = false)String error,
                        @RequestParam(value = "exception", required = false)String exception,
                        Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/user/login";
    }
}
