package com.w2m.supermaintenance.models;

import java.util.Date;
import lombok.Getter;

@Getter
public class CustomError {
    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final Date timestamp;

    public CustomError(int status, String error, String message, String path, Date timestamp) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.timestamp = timestamp;
    }
}
