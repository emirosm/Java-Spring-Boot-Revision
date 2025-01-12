package com.example.nobsv2.exceptions;

public enum ErrorMessages {
    PRODUCT_NOT_FOUND("Product not found!"), NAME_REQUIRED("Name is required!"), PRICE_REQUIRED("Price is required and can't be negative!");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
