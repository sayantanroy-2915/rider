package com.example.rider.repository;

import com.example.rider.model.entity.Order;
import com.example.rider.model.entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT o FROM Order o WHERE o.city = :city AND o.status = 'placed'")
	Optional<List<Order>> findPlacedOrders(@Param("city") String city);

	@Query("SELECT o FROM Order o WHERE o.riderId = :riderId AND o.status = 'delivered'")
	Optional<List<Order>> getDeliveredOrders(@Param("riderId") Long riderId);

}
