package com.insung.core.common.security;

import com.insung.core.common.mapper.CommonMapper;
import com.insung.core.common.security.SignedUser;
import com.insung.core.dto.UserDto;
import com.insung.core.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.Singular;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class SecurityUserDetailService implements UserDetailsService {
    private final UserMapper userMapper;
    private final CommonMapper commonMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(" userName ===============> : "+ username);
        Optional<UserDto> user = userMapper.findUser(username);
        log.info("user Data : {} ", user);
        SignedUser signedUser = null;

        try {
            if (user.isPresent()) {
                signedUser = createSignedUser(user.get());
                Collection<GrantedAuthority> authorities = commonMapper.getAuthority(user.get().getUser_id());
                log.info("===================>" +  authorities);
                signedUser.setAuthorities(authorities);
            } else {
                // TODO 로그인 시도한 아이디가 없을 경우 예외처리 구현해야함.
                //  UserDetailsService 에서 예외 처리를 하지 말고 authentication filter에서 걸러지도록 구현하자
                log.info("user null!!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("" + e.toString());
        }

        return signedUser;

    }

    private SignedUser createSignedUser(UserDto user) {

        SignedUser signeduser = new SignedUser(user);
        return signeduser;
    }
}
