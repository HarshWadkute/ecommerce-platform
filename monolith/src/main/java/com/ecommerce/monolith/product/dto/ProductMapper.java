package com.ecommerce.monolith.product.dto;

import com.ecommerce.monolith.product.Product;

public class ProductMapper {

    private ProductMapper() {
        // utility class, prevent instantiation
    }

    public static ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .price(product.getPrice())
                .stock(product.getStock())
                .image(product.getImage())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}