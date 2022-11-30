package com.insung.core.service;

import com.insung.core.common.mapper.CommonMapper;
import com.insung.core.dto.UserDto;
import com.insung.core.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

        validationByUserInfo(paramMap);

//        int result = userMapper.join(paramMap);
//
//        log.info("insert success : " + result);
//
//        if (result > 0) {
//            int chk = commonMapper.insertAuth(paramMap);
//            log.info("auth check : " + chk);
//        }
    }

    private void validationByUserInfo(Map<String, Object> paramMap) {
        Optional<String> userid = userMapper.validationUserID(paramMap);
        Optional<String> userEmail = userMapper.validationUserEmail(paramMap);
        System.out.println("userid = " + userid);
        System.out.println("userEmail = " + userEmail);
        // TODO 검증실패 익셉션 만들기..
        //  id, email 한번에 검증 할 수 있는 로직으로 바꾸기
        userid.orElseThrow(new UserValidationFailException());

    }
}
