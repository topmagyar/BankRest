package com.dev.bank.controllers.client;

import com.dev.bank.models.request.AuthLoginRequest;
import com.dev.bank.models.response.AuthLoginResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthenticationController {

    @PostMapping("/login") //http://localhost:8080/auth/login
    AuthLoginResponse login(AuthLoginRequest request);
}
