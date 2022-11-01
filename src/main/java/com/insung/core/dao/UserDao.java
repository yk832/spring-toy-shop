package com.insung.core.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
@Repository
public interface UserDao {
    ArrayList<HashMap<String, Object>> findAll();
}
