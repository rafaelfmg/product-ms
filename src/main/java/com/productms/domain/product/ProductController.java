package com.productms.domain.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    public ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductDto create(@Valid @RequestBody ProductDto product) {
        return productService.create(product);
    }

    @PutMapping("/{id}")
    ProductDto update(@PathVariable String id, @Valid @RequestBody ProductDto product) {
        return productService.update(id, product);
    }

    @GetMapping("/{id}")
    ProductDto get(@PathVariable String id) {
        return productService.get(id);
    }

    @GetMapping
    List<ProductDto> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/search")
    List<ProductDto> searchProducts(@RequestParam(name = "q", required = false) String query,
                                    @RequestParam(name = "min_price", required = false) BigDecimal minPrice,
                                    @RequestParam(name = "max_price", required = false) BigDecimal maxPrice) {
        return productService.getByFilter(query, minPrice, maxPrice);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        productService.delete(id);
    }
}
