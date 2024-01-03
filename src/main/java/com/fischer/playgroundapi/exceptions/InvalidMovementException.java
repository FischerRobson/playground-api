package com.fischer.playgroundapi.exceptions;

public class InvalidMovementException extends RuntimeException{
    public InvalidMovementException() {
        super("Invalid movement");
    }
}
