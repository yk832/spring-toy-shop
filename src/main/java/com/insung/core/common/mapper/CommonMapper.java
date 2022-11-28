package com.insung.core.common.mapper;

import com.insung.core.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
    public interface CommonMapper {

        List<GrantedAuthority> getAuthority(String user_id);

        int insertAuth(Map<String,Object> user);
}
