package com.dev.bank.models.response;

import com.dev.bank.models.common.BaseResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

public class AuthLoginResponse extends BaseResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
