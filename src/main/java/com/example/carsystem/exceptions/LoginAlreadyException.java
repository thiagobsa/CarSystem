package com.example.carsystem.exceptions;

public class LoginAlreadyException extends RuntimeException {
    public LoginAlreadyException(String msg) {
        super(msg);
    }
}