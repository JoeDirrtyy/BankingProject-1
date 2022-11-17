package com.banking.banking.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
public class ErrorDetail {
    //attributes for the error message
private int code;

private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ErrorDetail{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
