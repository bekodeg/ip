package com.example.exam.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super(String.format("Collaborator with id [%s] is not found", id));
    }
    public CustomerNotFoundException(String login) {
        super(String.format("Collaborator with login [%s] is not found or password incorrect", login));
    }
}

