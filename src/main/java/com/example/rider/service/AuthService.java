package com.example.rider.service;

import com.example.rider.model.*;
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

    public RiderDetails register(RegisterDTO rider) {
        rider.setPassword("{bcrypt}"+passwordEncoder.encode(rider.getPassword()));
        return new RiderDetails(repo.save(new Rider(rider)));
    }

    public LoginResDTO login(LoginReqDTO loginReqDTO) throws AuthenticationException{
        System.out.println("In AuthService: "+loginReqDTO);
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginReqDTO.getCred(), loginReqDTO.getPassword())
        );
        System.out.println("Authentication: "+auth);
        String token = tokenService.generateJwt(auth);
        return new LoginResDTO(repo.findByContact(loginReqDTO.getCred()).get(), token);
    }
}
