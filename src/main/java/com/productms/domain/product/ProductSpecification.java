package com.productms.domain.product;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static Specification<Product> products(String query, BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (query != null) {
                List<Predicate> predicatesText = new ArrayList<>();
                predicatesText.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + query.toLowerCase() + "%"));
                predicatesText.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + query.toLowerCase() + "%"));
                predicates.add(criteriaBuilder.or(predicatesText.toArray(new Predicate[0])));
            }
            if (maxPrice != null) {
                predicates.add(criteriaBuilder.le(root.get("price"), maxPrice));
            }
            if (minPrice != null) {
                predicates.add(criteriaBuilder.ge(root.get("price"), minPrice));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
