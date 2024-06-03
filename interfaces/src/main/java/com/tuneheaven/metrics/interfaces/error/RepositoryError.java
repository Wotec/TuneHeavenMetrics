package com.tuneheaven.interfaces.error;

public class RepositoryError {
    private final String message;

    public RepositoryError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
