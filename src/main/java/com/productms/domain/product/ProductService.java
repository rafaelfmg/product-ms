package com.productms.domain.product;

import com.productms.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductDto create(ProductDto productDto) {
        Product product = productMapper.dtoToEntity(productDto)
        ;return productMapper.entityToDto(productRepository.save(product));
    }

    public ProductDto update(String id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(NotFoundException::new);
        product.setDescription(productDto.getDescription());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());

        return productMapper.entityToDto(productRepository.save(product));
    }

    public ProductDto get(String id) {
        return productMapper.entityToDto(productRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    public List<ProductDto> getAll() {
        return productMapper.entitiesToListDto(productRepository.findAll());
    }

    public List<ProductDto> getByFilter(String query, BigDecimal minPrice, BigDecimal maxPrice) {
        return productMapper.entitiesToListDto(productRepository.findAll(ProductSpecification.products(query, minPrice, maxPrice)));
    }

    public void delete(String id) {
        Product product = productRepository.findById(id).orElseThrow(NotFoundException::new);
        productRepository.delete(product);
    }
}
