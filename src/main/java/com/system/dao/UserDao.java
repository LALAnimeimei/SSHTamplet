package com.system.dao;

import com.system.entity.User;
import org.springframework.stereotype.Repository;

public interface UserDao {
    User select(int id);
}

