package com.example.crudapi.product;

import com.example.crudapi.common.InMemoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository extends InMemoryRepository<Product> {
}
