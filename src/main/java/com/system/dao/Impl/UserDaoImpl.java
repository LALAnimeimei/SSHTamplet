package com.system.dao.Impl;


import com.system.dao.UserDao;
import com.system.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession(){
        return this.sessionFactory.openSession();
    }

    public UserEntity select(int id){
        return (UserEntity) getSession().get(UserEntity.class,id);
    }

    public Query query(String s){
        return (Query) getSession().createQuery(s);
    }

    public Serializable save(Object s){
        return  getSession().save(s);
    }

}
