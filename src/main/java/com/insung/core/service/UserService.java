package com.insung.core.service;

import com.insung.core.dto.UserDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface UserService {
    void join(Map<String,Object> user) throws Exception;
}
