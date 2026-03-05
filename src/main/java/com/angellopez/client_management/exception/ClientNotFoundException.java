package com.angellopez.client_management.exception;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(Long id) {
        super("ClientService not found with id " + id);
    }
}
