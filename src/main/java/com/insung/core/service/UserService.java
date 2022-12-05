package com.insung.core.service;

import com.insung.core.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface UserService {
    Boolean join(Map<String,Object> user, BindingResult bindingResult) throws Exception;
}
