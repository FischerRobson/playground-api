package com.fischer.playgroundapi.exceptions;

public class InvalidGameException extends RuntimeException{
    public InvalidGameException() {
        super("Invalid game invite");
    }
}
