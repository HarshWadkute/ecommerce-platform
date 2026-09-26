package com.ecommerce.monolith.product;

import com.ecommerce.monolith.common.exception.DuplicateResourceException;
import com.ecommerce.monolith.common.exception.ResourceNotFoundException;
import com.ecommerce.monolith.product.dto.ProductMapper;
import com.ecommerce.monolith.product.dto.ProductRequest;
import com.ecommerce.monolith.product.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse create(ProductRequest request) {
        // build a Product entity from the request (Builder pattern, like User)
        if(productRepository.existsByName(request.getName())){
            throw new DuplicateResourceException("Product name already exists");
        }

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .price(request.getPrice())
                .stock(request.getStock())
                .image(request.getImage())
                .build();
        // save it via productRepository

        Product savedProduct = productRepository.save(product);
        // return the mapped response
        return ProductMapper.toResponse(savedProduct);
    }

    @Override
    public ProductResponse getById(Long id) {
        // find by id, orElseThrow with ResourceNotFoundException
        Product product = productRepository.findById(id).orElseThrow(()->ResourceNotFoundException.create("Product",id));
        // return mapped response
        return ProductMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> search(String category, BigDecimal minPrice, BigDecimal maxPrice) {
        // start with a List<Product> — either findAll() or findAllByCategory(category)
        //   depending on whether category was provided
        List<Product> products;
        if (category==null) {
            products= productRepository.findAll();
        } else {
            products=productRepository.findAllByCategory(category);
        }
        // then, if minPrice/maxPrice were provided, narrow that list with

        //   .stream().filter(...) checking p.getPrice() against the range

        if (minPrice != null && maxPrice != null) {
            products = products.stream()
                    .filter(p -> p.getPrice().compareTo(minPrice) >= 0 && p.getPrice().compareTo(maxPrice) <= 0)
                    .toList();
        }

        return products.stream().map(ProductMapper::toResponse).toList();
        // finally map the resulting List<Product> to List<ProductResponse>
        //   (hint: .stream().map(ProductMapper::toResponse).toList())
    }
}