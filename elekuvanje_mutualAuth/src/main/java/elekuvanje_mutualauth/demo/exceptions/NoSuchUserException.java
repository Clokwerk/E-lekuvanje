package com.elekuvanje.elekuvanje.exceptions;

public class NoSuchUserException extends Exception {
    public NoSuchUserException() {
        super("No such user");
    }
}
