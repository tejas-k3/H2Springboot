package com.tejsk3.productservice.service;

import com.tejsk3.productservice.dto.ProductRequest;
import com.tejsk3.productservice.dto.ProductResponse;
import com.tejsk3.productservice.model.Product;
import com.tejsk3.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // This annotation handles ProductRepository construction
@Slf4j // This is for logging purposes
public class ProductService {
    private final ProductRepository productRepository;


    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProduceResponse).toList();
    }

    private ProductResponse mapToProduceResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
