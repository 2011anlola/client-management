package com.angellopez.client_management.exception;

/**
 * Exception thrown when a client is not found.
 */
public class ClientNotFoundException extends RuntimeException {

    /**
     * Constructs a new ClientNotFoundException with the specified ID.
     * @param id the client ID that was not found
     */
    public ClientNotFoundException(Long id) {
        super("ClientService not found with id " + id);
    }
}