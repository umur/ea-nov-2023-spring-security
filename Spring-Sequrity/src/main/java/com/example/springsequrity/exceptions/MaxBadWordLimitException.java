package com.example.springsequrity.exceptions;

public class MaxBadWordLimitException extends RuntimeException {
    public MaxBadWordLimitException(String message) {
        super(message);
    }
    public MaxBadWordLimitException(String message, Throwable cause) {
        super(message, cause);
    }
    public MaxBadWordLimitException() {
        super();
    }
}
