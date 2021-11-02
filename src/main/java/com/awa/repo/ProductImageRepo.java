package com.awa.repo;
/*
 * @author Awanthi Shalika
 * @since 10/25/2021
 */

import com.awa.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductImageRepo extends JpaRepository<ProductImage, Integer> {
    Optional<ProductImage> findProductImageByImageName(String imageName);
}
