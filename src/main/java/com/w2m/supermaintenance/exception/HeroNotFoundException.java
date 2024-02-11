package com.w2m.supermaintenance.exception;

public class HeroNotFoundException extends RuntimeException {

    public HeroNotFoundException(String message) {
        super(message);
    }


    public HeroNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
