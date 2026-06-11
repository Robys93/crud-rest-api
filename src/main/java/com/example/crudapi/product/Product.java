package com.example.crudapi.product;

import com.example.crudapi.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Product entity. Price uses {@link BigDecimal} to avoid floating-point
 * rounding issues on money.
 */
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseEntity {

    private String name;
    private BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
