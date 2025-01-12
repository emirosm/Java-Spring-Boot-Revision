package com.example.nobsv2.product;

import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.model.ErrorResponse;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.model.UpdateProductCommand;
import com.example.nobsv2.product.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private final CreateProductService createProductService;
    @Autowired
    private final GetProductsService getProductsService;
    @Autowired
    private final DeleteProductService deleteProductService;
    @Autowired
    private final UpdateProductService updateProductService;
    @Autowired
    private final GetProductService getProductService;

    private final SearchProductService searchProductService;

    public ProductController(CreateProductService createProductService, GetProductsService getProductsService, DeleteProductService deleteProductService, UpdateProductService updateProductService, GetProductService getProductService, SearchProductService searchProductService) {
        this.createProductService = createProductService;
        this.getProductsService = getProductsService;
        this.deleteProductService = deleteProductService;
        this.updateProductService = updateProductService;
        this.getProductService = getProductService;
        this.searchProductService = searchProductService;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
        return createProductService.execute(product);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return getProductsService.execute(null);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        return getProductService.execute(id);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        return updateProductService.execute(new UpdateProductCommand(id, product));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        return deleteProductService.execute(id);
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<ProductDTO>> searchProductByName(@RequestParam String name) {
        return searchProductService.execute(name);
    }

}
