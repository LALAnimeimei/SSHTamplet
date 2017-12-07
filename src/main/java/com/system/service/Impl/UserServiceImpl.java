package com.system.service.Impl;


import com.system.dao.Impl.UserDaoImpl;
import com.system.dao.UserDao;
import com.system.entity.User;
import com.system.service.UserService;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserDao userDao;

    public User select(int id){
        return userDao.select(id);
    }
    public Query query(String s){
        return userDao.query(s);
    }
}
