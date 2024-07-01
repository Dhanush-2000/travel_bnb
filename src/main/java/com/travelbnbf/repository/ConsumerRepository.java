package com.travelbnbf.repository;

import com.travelbnbf.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

   boolean existsByUsername(String username);
   boolean existsByEmail(String email);

   Optional<Consumer> findByUsername(String username);
}