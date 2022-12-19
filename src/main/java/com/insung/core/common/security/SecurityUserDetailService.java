package com.insung.core.common.security;

import com.insung.core.common.mapper.CommonMapper;
import com.insung.core.common.security.SignedUser;
import com.insung.core.dto.UserDto;
import com.insung.core.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.Singular;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
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
        // TODO Optional 로 바꾸기..
        SignedUser signedUser = null;

        try {
            if (user.isPresent()) {
                signedUser = createSignedUser(user.get());
                Collection<GrantedAuthority> authorities = commonMapper.getAuthority(user.get().getUser_id());
                log.info("===================>" +  authorities);
                signedUser.setAuthorities(authorities);
            } else {
                log.info("user null!!!!!");
                // TODO UsernameNotFoundException을 던져도 BadCredentialsException 로 바뀐다.. 찾아보자
                throw new UsernameNotFoundException("존재하지 않는 유저");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            log.info("@@@@@@@@@@@@@@@@@@@@ " + e.toString());
            throw new UsernameNotFoundException("로그인 정보 조회 중 오류가 발생하였습니다.");
        }

        return signedUser;

    }

    private SignedUser createSignedUser(UserDto user) {

        SignedUser signeduser = new SignedUser(user);
        return signeduser;
    }
}
