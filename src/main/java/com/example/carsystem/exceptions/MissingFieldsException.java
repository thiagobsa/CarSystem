package com.example.carsystem.exceptions;

public class MissingFieldsException extends RuntimeException {
    public MissingFieldsException(String msg) {
        super(msg);
    }
}
