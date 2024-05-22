package com.projetojava.sistemadeestoque.exception;

import java.io.Serial;

public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
