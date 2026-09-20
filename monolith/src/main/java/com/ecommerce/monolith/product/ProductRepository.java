package com.ecommerce.monolith.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    List<Product> findAllByCategory(String category);

    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);


}
