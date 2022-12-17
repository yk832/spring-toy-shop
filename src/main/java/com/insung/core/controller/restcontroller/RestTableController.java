package com.insung.core.controller.restcontroller;


import com.insung.core.dto.UserDto;
import com.insung.core.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class RestTableController {

    private final UserMapper userMapper;

    @GetMapping("/idCheck")
    public String idCheck(@RequestParam String userId) throws Exception {
        Optional<UserDto> user = userMapper.findUser(userId);
        if(user.isPresent()){
            log.info("user : {} " , user.get().toString());
            return "2";
        }
        return "1";
    }
}
