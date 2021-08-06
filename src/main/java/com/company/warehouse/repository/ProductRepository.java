package com.company.warehouse.repository;

import com.company.warehouse.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Product entity repository
 */
public interface ProductRepository extends JpaRepository<Product, Long> { }
