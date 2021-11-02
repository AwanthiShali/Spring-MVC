package com.awa.repo;
/*
 * @author Awanthi Shalika
 * @since 10/25/2021
 */

import com.awa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    Optional<Product> findProductByName(String name);
}
