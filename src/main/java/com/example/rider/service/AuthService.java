package com.example.rider.service;

import com.example.rider.model.LoginReqDTO;
import com.example.rider.model.Rider;
import com.example.rider.model.LoginResDTO;
import com.example.rider.model.RegisterDTO;
import com.example.rider.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {

    @Autowired
    private RiderRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public Rider register(RegisterDTO rider) {
        rider.setPassword(passwordEncoder.encode(rider.getPassword()));
        return repo.save(new Rider(rider));
    }

    public LoginResDTO login(LoginReqDTO loginReqDTO) {
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginReqDTO.getCred(), loginReqDTO.getPassword())
            );
            String token = tokenService.generateJwt(auth);
            return new LoginResDTO(repo.findByContact(loginReqDTO.getCred()).get(), token);
        } catch (AuthenticationException e) {
            return new LoginResDTO(null,null);
        }
    }
}
