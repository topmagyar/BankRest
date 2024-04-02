package com.dev.bank.dao.impl;

import com.dev.bank.dao.UserDao;
import com.dev.bank.models.dao.User;
import com.dev.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository repository;

    @Override
    public Integer save(User user) {
        User createdUser = null;

        try {
            createdUser = repository.save(user);
        } catch (Exception e) {
            System.out.println("We got exception here: " + e.getMessage());
            return 0;
        }

        return createdUser.getId();
    }

    @Override
    public User findByEmail(String email) {
        User user = null;

        try {
            user = repository.findByEmail(email);
        } catch (Exception e) {
            System.out.println("We got exception here: " + e.getMessage());
        }
        return user;
    }
}
