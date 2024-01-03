package com.fischer.playgroundapi.exceptions;

public class FullGameException extends RuntimeException{
    public FullGameException() {
        super("The game room is full");
    }
}
