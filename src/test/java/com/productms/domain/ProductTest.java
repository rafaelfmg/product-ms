package com.productms.domain;

import com.productms.domain.product.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductTest {

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;
    @Spy
    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setName("Soja");
        productDto.setDescription("Soja a granel");
        productDto.setPrice(BigDecimal.valueOf(300.41));
        Product product = new Product();
        product.setId("aaaauid");
        product.setName("Soja");
        product.setDescription("Soja a granel");
        product.setPrice(BigDecimal.valueOf(300.41));

     //   when(productMapper.dtoToEntity(productDto)).thenReturn(product);
        when(productRepository.save(ArgumentMatchers.any())).thenReturn(product);
        ProductDto result = productService.create(productDto);

        assertEquals(result.getId(), "aaaauid");

    }
}
