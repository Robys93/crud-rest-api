package com.example.crudapi.product;

import com.example.crudapi.common.GenericService;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends GenericService<Product, ProductDto, Long> {

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected String resourceName() {
        return "Product";
    }
}
