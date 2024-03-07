package com.dev.bank.models.response;

import com.dev.bank.models.common.BaseResponse;

public class AuthLoginResponse extends BaseResponse {

    private Integer userId;
    private String token;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
