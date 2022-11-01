package com.insung.core.controller;

import com.insung.core.dto.ResponseDTO;
import com.insung.core.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class TestController {

    private UserServiceImpl userService;

    @Autowired
    public TestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResultCode("S0001");
        responseDTO.setRes(userService.findAll());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/view")
    public String viewPage() {
        System.out.println("view page 호출!!!@@@@@@@@@@@@@@@@@@sss");
        return "view page";
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        System.out.println("view page 호출!!!@@@@@@@@@@@@@@@@@@sss");
        return new ResponseEntity<>("responseDTO", HttpStatus.OK);
    }
}
