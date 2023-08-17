package com.example.rider.util;

import com.example.rider.model.Rider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTUtil implements Serializable {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.tokenValidity}")
    private long tokenValidity;

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Date extractExpiry(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiry(token).before(new Date());
    }

    private String createToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidity))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    // Specific

    public String generateToken(Long riderId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("RiderID", riderId);
        return createToken(claims);
    }

    private Long getRiderId(Claims claims) {
        return Long.parseLong(claims.get("RiderID").toString());
    }

    public Long getRiderId(String token) {
        return extractClaim(token, this::getRiderId);
    }

    public Boolean validateToken(String token, Long riderId) {
        return getRiderId(token).equals(riderId) && !isTokenExpired(token);
    }
}
