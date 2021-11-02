package com.awa.repo;
/*
 * @author Awanthi Shalika
 * @since 10/25/2021
 */

import com.awa.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
