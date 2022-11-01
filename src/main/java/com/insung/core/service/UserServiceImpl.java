package com.insung.core.service;

import com.insung.core.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService{
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        public ArrayList<HashMap<String, Object>> findAll() {
            return  userDao.findAll();
    }
}
