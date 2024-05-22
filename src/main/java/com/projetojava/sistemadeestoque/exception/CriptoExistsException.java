package com.projetojava.sistemadeestoque.exception;

import java.io.Serial;

public class CriptoExistsException extends Exception {
    public CriptoExistsException(String msg) {
        super(msg);
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
