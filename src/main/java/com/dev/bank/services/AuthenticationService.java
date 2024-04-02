package com.dev.bank.services;

import com.dev.bank.dao.UserDao;
import com.dev.bank.models.dao.User;
import com.dev.bank.models.request.AuthLoginRequest;
import com.dev.bank.models.request.AuthRegisterRequest;
import com.dev.bank.models.response.AuthLoginResponse;
import com.dev.bank.models.response.AuthRegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.time.ZonedDateTime;

@Service
public class AuthenticationService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

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
        User user = userDao.findByEmail(email);
//        if (user == null) {
//            response.setSuccess(false);
//            response.setMessage("User with email: '" + email + "' doesn't exist in the system");
//
//            return response;
//        }
//
//        if (!passwordEncoder.matches(password, expectedHashedPassword)) {
//            response.setSuccess(false);
//            response.setMessage("Password is incorrect");
//
//            return response;
//        }

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            response.setSuccess(false);
            response.setMessage("Wrong credentials");

            return response;
        }

        response.setSuccess(true);
        response.setUserId(user.getId());
        response.setToken(tokenService.generateToken(user.getId()));

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

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(hashedPassword);
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setAge(getAge(request.getBirthday()));
        newUser.setPhoneNumber(request.getPhoneNumber());

        Integer userId = userDao.save(newUser);

        if (userId == null || userId == 0) {
            response.setSuccess(false);
            response.setMessage("Something went wrong and we're not able to register your account");

            return response;
        }

        response.setSuccess(true);
        response.setUserId(userId);
        response.setToken(tokenService.generateToken(userId));

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
