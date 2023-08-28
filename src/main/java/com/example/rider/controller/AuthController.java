package com.example.rider.controller;

import com.example.rider.model.Rider;
import com.example.rider.model.LoginReqDTO;
import com.example.rider.model.LoginResDTO;
import com.example.rider.model.RegisterDTO;
import com.example.rider.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public Rider register(@RequestBody RegisterDTO body) {
        return authService.register(body);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReqDTO body) {
        try {
            System.out.println("In AuthController: "+body);
            return ResponseEntity.ok(authService.login(body));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
