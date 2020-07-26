package com.mentor.budget.exception;

public class SpendException extends RuntimeException {

    public SpendException() {
        super("Exception during spend request processing. Please verify request details");
    }
}