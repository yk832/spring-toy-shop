package com.insung.core.mapper;

import com.insung.core.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
@Repository
public interface UserMapper {
    UserDto join(UserDto userDto);
}
