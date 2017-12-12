package com.system.dao;

import com.system.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

public interface UserDao {
    User select(int id);
    Query query(String s);
    Session getSession();
}

