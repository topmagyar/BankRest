package com.dev.bank.services;

import com.dev.bank.dao.UserDao;
import com.dev.bank.models.dao.User;
import com.dev.bank.models.request.AuthLoginRequest;
import com.dev.bank.models.request.AuthRegisterRequest;
import com.dev.bank.models.response.AuthLoginResponse;
import com.dev.bank.models.response.AuthRegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.time.ZonedDateTime;

@Service
public class AuthenticationService {

    @Autowired
    private UserDao userDao;

    public AuthLoginResponse login(AuthLoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        AuthLoginResponse response = new AuthLoginResponse();

        if (email == null || email.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("Email field is required");

            return response;
        }

        if (password == null || password.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("Your password can't be null or empty");

            return response;
        }

        //TODO Replace with real login process here
        response.setSuccess(true);
        response.setUserId(123);
        response.setToken("WillBeGeneratedInFuture");

        return response;
    }

    public AuthRegisterResponse register(AuthRegisterRequest request) {
        AuthRegisterResponse response = new AuthRegisterResponse();

        ZonedDateTime birthdayDate = request.getBirthday();
        if (!isValidAge(birthdayDate)) {
            response.setSuccess(false);
            response.setMessage("Unfortunately, we're not able to register your account as you're under 18");

            return response;
        }

        //TODO Implement save data from request
        User user = new User();
        user.setEmail("abcd@gmail.com");
//        user.setPassword("123123213");
        user.setFirstName("ABC");
        user.setLastName("DCE");

        Integer userId = userDao.save(user);

        response.setSuccess(true);
        response.setUserId(userId);

        return response;
    }

    private Integer getAge(ZonedDateTime birthdayDate) {
        Period period = Period.between(birthdayDate.toLocalDate(), ZonedDateTime.now().toLocalDate());

        return period.getYears();
    }

    private boolean isValidAge(ZonedDateTime birthdayDate) {
        if (birthdayDate == null) {
            return false;
        }

        return getAge(birthdayDate) >= 18;
    }
}
