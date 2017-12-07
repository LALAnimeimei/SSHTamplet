package com.system.dao.Impl;


import com.system.dao.UserDao;
import com.system.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        return this.sessionFactory.getCurrentSession();
    }

    public User select(int id){
        return (User)getSession().get(User.class,id);
    }

    public Query query(String s){
        return (Query) getSession().createQuery(s);
    }

}
