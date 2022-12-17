package com.insung.core.service;

import com.insung.core.common.mapper.CommonMapper;
import com.insung.core.dto.UserDto;
import com.insung.core.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

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
    private final  BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Boolean join(Map<String,Object> paramMap,BindingResult bindingResult) throws Exception{
        // TODO 유저 아이디,이메일 중복 검사
        if(!validationByUserInfo(paramMap, bindingResult)) return false;

        String pwd = paramMap.get("pwd").toString();

        log.info("암호화 전 pwd : {}",pwd);
        String pwdEncr = bCryptPasswordEncoder.encode(pwd);
        log.info("암호화 후 pwd : {}", pwdEncr);
        paramMap.put("pwd", pwdEncr);

        Boolean result = userMapper.join(paramMap);

        log.info("insert success : " + result);

        if (result) {
            int chk = commonMapper.insertAuth(paramMap);
            log.info("auth check : " + chk);
        }

        return true;
    }


    public Boolean validationByUserInfo(Map<String, Object> paramMap, BindingResult bindingResult) {
        Optional<String> userid = userMapper.validationUserID(paramMap);
        Optional<String> userEmail = userMapper.validationUserEmail(paramMap);
        System.out.println("userid = " + userid);
        System.out.println("userEmail = " + userEmail);
        // 중복 체크
        if (!userid.isEmpty()) {
            System.out.println("id fails");
            bindingResult.rejectValue("user_id", "닉네임 중복 오류", "이미 사용중인 닉네임 입니다.");
            return false;
        } else if (!userEmail.isEmpty()) {
            // TODO 디비에서 이베일도 유니크key로 변경해줘야함.. 현재 유니크하지 않아서 중복 검사가 불가능..
            System.out.println("email fails");
            bindingResult.rejectValue("email_addr", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
            return false;
        }

        return true;
    }

}
