package com.system.dao;

import com.system.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

public interface UserDao {
    UserEntity select(int id);
    Query query(String s);
    Session getSession();
    Serializable save(Object s);
}

