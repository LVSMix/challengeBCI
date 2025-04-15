package com.bci.infraestructure.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("El correo ya registrado: " + email);
    }
}
