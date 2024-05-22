package com.projetojava.sistemadeestoque.model;

import com.projetojava.sistemadeestoque.enums.ProductType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "O nome não pode ser vazio")
    @NotNull
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProductType type;

    @Column(name = "quantity")
    @NotNull
    private Integer quantity;

    @Column(name = "buyPrice")
    @NotNull
    private Double buyPrice;

    @Column(name = "sellPrice")
    @NotNull
    private Double sellPrice;

    @Column(name = "whereStored")
    private String WhereStored;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getWhereStored() {
        return WhereStored;
    }

    public void setWhereStored(String whereStored) {
        WhereStored = whereStored;
    }

}
