package com.example.crudapi.product;

import com.example.crudapi.common.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements GenericMapper<Product, ProductDto> {

    @Override
    public ProductDto toDto(Product entity) {
        return new ProductDto(entity.getId(), entity.getName(), entity.getPrice());
    }

    @Override
    public Product toEntity(ProductDto dto) {
        Product product = new Product(dto.getName(), dto.getPrice());
        product.setId(dto.getId());
        return product;
    }
}
