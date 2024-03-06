package com.dev.bank.models.common;

public class BaseResponse {

    private Boolean success;
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
