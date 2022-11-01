package com.insung.core.controller;

import com.insung.core.dto.ResponseDTO;
import com.insung.core.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewPage() {
        return "view page";
    }
}
