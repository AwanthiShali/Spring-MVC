package com.awa.exception;
/*
 * @author Awanthi Shalika
 * @since 10/25/2021
 */

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
