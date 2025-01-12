package com.example.nobsv2.product.headers;

import com.example.nobsv2.product.model.Product;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderController {

    @GetMapping("/headers")
    public String getRegionalResponse(@RequestHeader(required = false, defaultValue = "US") String region){
        if(region.equals("US")){
            return "Welcome to the US site";
        } else if(region.equals("EU")){
            return "Welcome to the EU site";
        } else {
            return "Welcome to the Global site";
        }
    }

    @GetMapping(value = "/headers/product", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Product> getProduct(){
        Product product = new Product();
        product.setId(1);
        product.setName("Product 1");
        product.setDescription("Description 1");
        product.setPrice(100.0);
        return ResponseEntity.ok(product);
    }
}
