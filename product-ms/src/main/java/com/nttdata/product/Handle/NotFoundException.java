package com.nttdata.product.Handle;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}