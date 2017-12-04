package com.system.service;


import com.system.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface UserService {
    User select(int id);
}
