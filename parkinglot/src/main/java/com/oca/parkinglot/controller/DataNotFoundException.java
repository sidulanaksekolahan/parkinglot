package com.oca.parkinglot.controller;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException() {
    }

    public DataNotFoundException(String message) {
        super(message);
    }
}
