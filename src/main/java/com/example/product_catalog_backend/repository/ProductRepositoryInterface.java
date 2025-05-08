package com.example.product_catalog_backend.repository;

import com.example.product_catalog_backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryInterface extends JpaRepository<Product, Long> {
}
