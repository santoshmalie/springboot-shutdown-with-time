package com.mentor.budget.exception;

/**
 * Custom exception for Spend Request
 */
public class SpendException extends RuntimeException {

    public SpendException() {
        super("Exception during spend request processing. Please verify request details");
    }
}