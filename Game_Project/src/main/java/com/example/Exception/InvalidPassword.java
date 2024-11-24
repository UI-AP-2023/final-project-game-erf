package com.example.Exception;

public class InvalidPassword extends Exception {

    public InvalidPassword(String message) {
        super(message);
    }
    public InvalidPassword() {
        super("InvalidPassword");
    }

}
