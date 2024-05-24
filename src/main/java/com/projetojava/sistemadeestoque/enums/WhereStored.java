package com.projetojava.sistemadeestoque.enums;

public enum WhereStored {
    SALA_1("Sala 2"),
    SALA_2("Sala 2"),
    SALA_3("Sala 2"),
    SALA_4("Sala 2"),
    SALA_5("Sala 2"),
    SALA_6("Sala 2");

    @SuppressWarnings("unused")
    private String whereStored;

    private WhereStored(String whereStored) {
        this.whereStored = whereStored;
    }

}
