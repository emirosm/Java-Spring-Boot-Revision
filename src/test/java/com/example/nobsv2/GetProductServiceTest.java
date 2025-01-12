package com.example.nobsv2;

import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.service.GetProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductService getProductService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_get_product_then_return_product_dto() {
        // given
        Product product = new Product();
        product.setId(1);
        product.setName("Apple iPhone 14");
        product.setDescription("Latest model with 256GB storage A15 Bionic chip, and improved camera system");
        product.setPrice(9.99);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        // when
        ResponseEntity<ProductDTO> responseEntity = getProductService.execute(1);

        // then
        assertEquals(ResponseEntity.ok(new ProductDTO(product)), responseEntity);
        verify(productRepository, Mockito.times(1)).findById(1);
    }

    @Test
    public void given_product_does_not_exist_when_get_product_then_throw_product_not_found_exception() {
        // given
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        // when and then
        assertThrows(ProductNotFoundException.class, () -> getProductService.execute(1));
        verify(productRepository, Mockito.times(1)).findById(1);
    }
}
