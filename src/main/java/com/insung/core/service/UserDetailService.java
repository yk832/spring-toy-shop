package com.insung.core.service;

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
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(" userName ===============> : "+ username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("1234");
        encode = "{bcrypt}" + encode;
        System.out.println("encode ============> " + encode);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin").equals(username)) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("USER"));
        }

        String id = "admin";
        return new User(username,encode,authorities);
    }
}
