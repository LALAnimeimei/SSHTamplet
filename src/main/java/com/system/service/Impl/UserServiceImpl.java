package com.system.service.Impl;


import com.system.dao.UserDao;
import com.system.entity.UserEntity;
import com.system.service.UserService;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserDao userDao;

    public Session getSession(){return  userDao.getSession();}
    public UserEntity select(int id){
        return userDao.select(id);
    }
    public Query query(String s){
        return userDao.query(s);
    }
    @Transactional
    public Serializable save(Object s){
        return userDao.save(s);
    }
}
