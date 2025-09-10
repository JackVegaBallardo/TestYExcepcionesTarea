package com.nttdata.dockerized.postgresql.Handle;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}