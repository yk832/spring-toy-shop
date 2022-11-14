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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SecurityUserDetailService implements UserDetailsService {
    private final UserMapper userMapper;
    private final CommonMapper commonMapper;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(" userName ===============> : "+ username);
        UserDto user = userMapper.findUser(username);
        log.info("user Data : {} ", user);

        SignedUser signedUser = null;
        try {

            if (user != null) {
                signedUser = createSignedUser(user);
                Collection<GrantedAuthority> authorities = commonMapper.getAuthority(user.getUser_id());
                log.info("===================>" +  authorities);
                signedUser.setAuthorities(authorities);

            } else {
                log.info("user null !!!!!!!!!!!");
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
