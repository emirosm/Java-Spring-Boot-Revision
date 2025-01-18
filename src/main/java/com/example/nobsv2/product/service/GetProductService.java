package com.example.nobsv2.product.service;

import com.example.nobsv2.Query;
import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class GetProductService implements Query<Integer, ProductDTO> {
    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(GetProductService.class);
    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Cacheable("ProductCache")
    public ResponseEntity<ProductDTO> execute(Integer input) {
        Optional<Product> productOptional = productRepository.findById(input);
        logger.info("Product with id: " + input + " was found: " + productOptional.isPresent());
        if(productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ProductDTO(productOptional.get()));
        }
        throw new ProductNotFoundException();
    }

}
