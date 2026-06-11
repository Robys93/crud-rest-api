package com.example.crudapi.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * What the API exchanges for a Product.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    @NotBlank(message = "name must not be blank")
    private String name;

    @NotNull(message = "price must not be null")
    @Positive(message = "price must be greater than zero")
    private BigDecimal price;
}
