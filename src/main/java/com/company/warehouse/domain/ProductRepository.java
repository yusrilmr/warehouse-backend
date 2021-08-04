package com.company.warehouse.domain;

import org.springframework.data.repository.CrudRepository;

/**
 * Product entity repository
 */
public interface ProductRepository extends CrudRepository<Product, Long> {
}
