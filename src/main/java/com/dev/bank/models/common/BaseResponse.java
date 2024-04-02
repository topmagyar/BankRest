package com.dev.bank.models.common;

import com.fasterxml.jackson.annotation.JsonInclude;

public class BaseResponse {

    private Boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }
}
