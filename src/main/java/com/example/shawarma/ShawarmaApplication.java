package com.example.shawarma;

import com.example.shawarma.entity.Customer;
import com.example.shawarma.entity.Product;
import com.example.shawarma.repository.CustomerRepository;
import com.example.shawarma.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShawarmaApplication {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public ShawarmaApplication(CustomerRepository customerRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ShawarmaApplication.class, args);
    }

    @PostConstruct
    public void loadEntities() {
        customerRepository.save(new Customer().setName("Customer 1"));
        customerRepository.save(new Customer().setName("Customer 2"));

        productRepository.save(new Product().setTitle("Shawarma classic"));
        productRepository.save(new Product().setTitle("Shawarma cheesy"));
        productRepository.save(new Product().setTitle("Coca cola"));
        productRepository.save(new Product().setTitle("Fried chicken"));
    }
}
