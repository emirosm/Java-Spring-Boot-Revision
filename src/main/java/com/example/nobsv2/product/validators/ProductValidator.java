package com.example.nobsv2.product.validators;

import com.example.nobsv2.exceptions.ErrorMessages;
import com.example.nobsv2.exceptions.ProductNotValidException;
import com.example.nobsv2.product.model.Product;

public class ProductValidator {
    private ProductValidator() {
    }

    public static void execute(Product product){
        if(product.getName().isEmpty()){
            throw new ProductNotValidException(ErrorMessages.NAME_REQUIRED.getMessage());
        }

        if(product.getPrice() == null || product.getPrice() < 0){
            throw new ProductNotValidException(ErrorMessages.PRICE_REQUIRED.getMessage());
        }
    }
}
