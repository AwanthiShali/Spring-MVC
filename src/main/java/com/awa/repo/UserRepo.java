package com.awa.repo;
/*
 * @author Awanthi Shalika
 * @since 10/25/2021
 */

import com.awa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
