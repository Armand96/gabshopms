package com.gaboot.gabshop.product.product;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductSpec {
    public Specification<ProductEntity> filterByNameAndSku(String name, String sku) {
        return (root, query, criteriaBuilder) ->  {
            Specification<ProductEntity> spec = Specification.where(null);
            if(name != null && !name.isEmpty()) {
                spec = spec.and((root1, query1, cb1) -> cb1.like(cb1.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if(sku != null && !sku.isEmpty()) {
                spec = spec.and((root1, query1, cb1) -> cb1.like(cb1.lower(root.get("sku")), "%" + sku.toLowerCase() + "%"));
            }

            return spec.toPredicate(root, query, criteriaBuilder);
        };
    }
}
