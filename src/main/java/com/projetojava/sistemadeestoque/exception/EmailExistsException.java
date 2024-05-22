package com.projetojava.sistemadeestoque.exception;

import java.io.Serial;

public class EmailExistsException extends Exception {

    public EmailExistsException(String msg) {
        super(msg);
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
