package com.dev.bank.dao;

import com.dev.bank.models.dao.User;

public interface UserDao {

    Integer save(User user);
    User findByEmail(String email);
}
