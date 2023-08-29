package com.example.rider.controller;

import com.example.rider.model.external.Order;
import com.example.rider.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    OrderRepository orderRepo;

    @GetMapping("/")
    public String orderHome() {
        return "Order please...";
    }

    @PostMapping("/new")
    public Order newOrder() {
        return orderRepo.save(new Order());
    }

//    @PostMapping(value = "/")
//    private getOrders() {
//
//    }
}
