package com.dev.bank.services;

import com.dev.bank.models.request.AuthLoginRequest;
import com.dev.bank.models.response.AuthLoginResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

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
}
