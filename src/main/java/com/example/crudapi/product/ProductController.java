package com.example.crudapi.product;

import com.example.crudapi.common.GenericController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes /products. Note how little code adding a second entity required:
 * this is the payoff of the generic layer.
 */
@RestController
@RequestMapping("/products")
@Tag(name = "Products", description = "CRUD operations on products")
public class ProductController extends GenericController<Product, ProductDto, Long> {

    public ProductController(ProductService service) {
        super(service);
    }
}
