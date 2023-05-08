package com.example.shawarma.dto;

import com.example.shawarma.entity.Basket;
import com.example.shawarma.entity.Customer;
import com.example.shawarma.entity.Product;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Optional.of;

@Data
@Accessors(chain = true)
public class BasketDto {
    private Long id;
    private Long customerId;
    private Set<Long> products = new HashSet<>();

    public static BasketDto toDto(Basket basket) {
        return new BasketDto()
                .setId(basket.getId())
                .setCustomerId(of(basket.getCustomer())
                        .map(Customer::getId)
                        .orElse(null))
                .setProducts(basket.getProducts().stream()
                        .map(Product::getId)
                        .collect(Collectors.toSet()));

    }
}
