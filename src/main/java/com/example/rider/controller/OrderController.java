package com.example.rider.controller;

import com.example.rider.model.entity.Order;
import com.example.rider.repository.OrderRepository;
import com.example.rider.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/")
    public String orderHome() {
        return "Order please...";
    }

    @GetMapping("/get-orders")
    public ResponseEntity<?> getOrders(@RequestParam String city) {
        List<Order> data = service.findPlacedOrders(city);
        if(data.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
        return ResponseEntity.ok(data);
    }

    @PutMapping("/assign-rider")
    public ResponseEntity<?> assignRider(@RequestParam(name = "order") Long orderId, @RequestParam(name = "rider") Long riderId) {
        if (service.assignRider(orderId,riderId))
            return ResponseEntity.ok(null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @PutMapping("/pick-up")
    public ResponseEntity<?> pickup(@RequestParam(name = "order") Long orderId) {
        service.pickUp(orderId);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/deliver")
    public ResponseEntity<?> deliver(@RequestParam(name = "order") Long orderId) {
        service.deliver(orderId);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/get-delivered-orders")
    public ResponseEntity<?> getDeliveredOrders(@RequestParam(name = "rider") Long riderId) {
        List<Order> data = service.getDeliveredOrders(riderId);
        if(data.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
        return ResponseEntity.ok(data);
    }
}
