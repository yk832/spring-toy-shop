package com.insung.core.service;

import com.insung.core.common.mapper.CommonMapper;
import com.insung.core.dto.UserDto;
import com.insung.core.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
    @Service
    @RequiredArgsConstructor
    public class UserServiceImpl implements UserService{

        private final UserMapper userMapper;

        private final CommonMapper commonMapper;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        @Override
    public void join(Map<String,Object> paramMap) throws Exception{
        String pwd = paramMap.get("pwd").toString();

        log.info("암호화 전 pwd : {}",pwd);
        String pwdEncr = bCryptPasswordEncoder.encode(pwd);
        log.info("암호화 후 pwd : {}", pwdEncr);
        pwdEncr = "{bcrypt}" + pwdEncr;
        paramMap.put("pwd", pwdEncr);

        int result = userMapper.join(paramMap);

        log.info("insert success : " + result);

        if (result > 0) {
            int chk = commonMapper.insertAuth(paramMap);
            log.info("auth check : " + chk);
        }
    }
}
