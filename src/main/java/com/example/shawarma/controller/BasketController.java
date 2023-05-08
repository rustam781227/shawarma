package com.example.shawarma.controller;

import com.example.shawarma.dto.BasketDto;
import com.example.shawarma.entity.Basket;
import com.example.shawarma.entity.Customer;
import com.example.shawarma.entity.Product;
import com.example.shawarma.repository.BasketRepository;
import com.example.shawarma.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BasketController {
    private final BasketRepository basketRepository;
    private final CustomerRepository customerRepository;

    public BasketController(BasketRepository basketRepository, CustomerRepository customerRepository) {
        this.basketRepository = basketRepository;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/customer/{id}/basket")
    public ResponseEntity<BasketDto> addProduct(@PathVariable Long id, @RequestBody Product product) {
        Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (customer.getBasket() == null) {
            customer.setBasket(new Basket().setCustomer(customer));
            basketRepository.save(customer.getBasket());
        }
        customer.getBasket().getProducts().add(product);
        customerRepository.save(customer);
        return ResponseEntity.ok(BasketDto.toDto(customer.getBasket()));
    }

    @DeleteMapping("/customer/{id}/basket")
    public ResponseEntity<Long> checkout(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (customer.getBasket() != null) {
            basketRepository.delete(customer.getBasket());
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
