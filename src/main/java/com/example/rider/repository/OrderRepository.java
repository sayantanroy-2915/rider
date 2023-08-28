package com.example.rider.repository;

import com.example.rider.model.external.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Override
    Optional<Order> findById(Long orderId);
    
}
