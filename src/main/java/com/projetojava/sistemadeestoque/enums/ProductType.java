package com.projetojava.sistemadeestoque.enums;

public enum ProductType {
    ALIMENTOS("Alimentos"),
    HIGIENE("Higiene"),
    BEBIDAS("Bebidas"),
    LIMPEZA("Limpeza"),
    MATERIAL_ESCOLAR("Material escolar");

    @SuppressWarnings("unused")
    private String productType;

    private ProductType(String productType) {
        this.productType = productType;
    }

}
