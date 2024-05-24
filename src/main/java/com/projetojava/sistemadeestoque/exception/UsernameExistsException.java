package com.projetojava.sistemadeestoque.exception;

import java.io.Serial;

public class UsernameExistsException extends Exception {

    public UsernameExistsException(String msg) {
        super(msg);
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
