package com.insung.core.service;

import com.insung.core.dto.UserDto;
import com.insung.core.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;
    @Override
    public UserDto join(UserDto user) {
        return userMapper.join(user);
    }
}
