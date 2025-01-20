package com.example.nobsv2.product.model;

public class UpdateProductCommand {
    private final Integer id;
    private final Product product;

    public UpdateProductCommand(Integer id, Product product) {
        this.id = id;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }
}
