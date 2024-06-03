package com.tuneheaven.interfaces.error;

public class RestApiFailure {
    private final Integer code;
    private final String message;

    public RestApiFailure(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
