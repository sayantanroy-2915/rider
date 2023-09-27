package com.example.rider.controller;

import com.example.rider.model.dto.LoginReqDTO;
import com.example.rider.model.dto.RegisterDTO;
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

    @GetMapping("/")
    public String home() {
        return "Authorization Page";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO body) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(body));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReqDTO body) {
        try {
            return ResponseEntity.ok(authService.login(body));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
