package com.example.rider.service;

import com.example.rider.model.entity.Order;
import com.example.rider.model.entity.Rider;
import com.example.rider.repository.OrderRepository;
import com.example.rider.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private RiderRepository riderRepo;

	public void newOrder(Order order) {
		order.setStatus("placed");
		order.setPlacedAt(new Date());
		order.setRiderId(null);
		System.out.println(orderRepo.save(order));
	}

	public List<Order> findPlacedOrders(String city) {
		return orderRepo.findPlacedOrders(city).orElse(Collections.emptyList());
	}

	public boolean assignRider(Long orderId, Long riderId) {
		Order order = orderRepo.findById(orderId).orElse(null);
		if (order != null && order.getRiderId() == null) {
			order.setRiderId(riderId);
			order.setStatus("assigned");
			orderRepo.save(order);
			return true;
		}
		return false;
	}

	public void pickUp(Long orderId) {
		Order order = orderRepo.findById(orderId).orElse(null);
		if (order != null) {
			order.setPickedUpAt(new Date());
			order.setStatus("picked up");
			orderRepo.save(order);
		}
	}

	public void deliver(Long orderId) {
		Order order = orderRepo.findById(orderId).orElse(null);
		if (order != null) {
			order.setDeliveredAt(new Date());
			order.setStatus("delivered");
			orderRepo.save(order);
		}
	}

	public List<Order> getDeliveredOrders(Long riderId) {
		return orderRepo.getDeliveredOrders(riderId).orElse(Collections.emptyList());
	}
}
