package com.sekoding.example.demo.model.response;

import org.springframework.http.HttpStatus;

public class FailedResponse {

    private int status;
    private String message;

    public FailedResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
