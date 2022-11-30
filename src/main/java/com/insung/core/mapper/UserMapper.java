package com.insung.core.mapper;

import com.insung.core.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Mapper
@Repository
public interface UserMapper {
    int join(Map<String,Object> userDto);

    Optional<UserDto> findUser(String userId);

    Optional<String> validationUserID(Map<String, Object> paramMap);
    Optional<String> validationUserEmail(Map<String, Object> paramMap);
}
