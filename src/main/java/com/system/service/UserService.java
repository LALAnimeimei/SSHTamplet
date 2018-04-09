package com.system.service;


import com.system.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

@Component
public interface UserService {
    @Transactional
    void saveDate(HttpServletRequest request);
    UserEntity select(int id);
    Query query(String s);
    Session getSession();
    Serializable save(Object s);
}
