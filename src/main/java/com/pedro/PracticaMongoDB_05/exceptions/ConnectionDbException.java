package com.pedro.PracticaMongoDB_05.exceptions;

public class ConnectionDbException extends RuntimeException {
    public ConnectionDbException(String message) {
        super(message);
    }
}
