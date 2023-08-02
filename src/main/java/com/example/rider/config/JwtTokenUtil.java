package com.example.rider.config;

import com.example.rider.model.Rider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.tokenValidity}")
    private long tokenValidity;

    public String generateToken(Rider rider) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", rider.getId());
        claims.put("name", rider.getName());
        claims.put("phone", rider.getPhone());
        if (rider.getEmail() != null)
            claims.put("email", rider.getEmail());
        Date expiry = new Date(System.currentTimeMillis() + tokenValidity);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

//    public Map<String, Object> validateToken(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(secret)
//                .parseClaimsJws(token)
//                .getBody();
//        Map<String, Object> claimsMap = new HashMap<>();
//        claimsMap.put("id", claims.get("id", Integer.class));
//        claimsMap.put("name", claims.get("name", String.class));
//        claimsMap.put("phone", claims.get("phone", String.class));
//        if (claims.containsKey("email"))
//            claimsMap.put("email", claims.get("email",String.class));
//        claimsMap.put("issued_at", claims.getIssuedAt());
//        claimsMap.put("expiry_date", claims.getExpiration());
//        return claimsMap;
//    }

    public boolean isTokenExpired(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        Date expirationDate = claims.getExpiration();
        return expirationDate.before(new Date());
    }
}
