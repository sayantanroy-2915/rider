package com.example.rider.controller;

import com.example.rider.model.Rider;
import com.example.rider.model.RiderRegDTO;
import com.example.rider.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public Rider register(@RequestBody RiderRegDTO body) {
        return authService.register(body);
    }
}
