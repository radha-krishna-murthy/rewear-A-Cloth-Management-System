package com.clothDonation.rewear.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {
    private final String secret = "my-super-secret-key-long-enough-for-32-bits";
    private final SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256) // ✅ Correct algorithm
                .compact();
    }
    public String extractUserNameFromToken(String token)
    {
    Claims body=extractClaim(token);
    return body.getSubject();
    }
    public Claims extractClaim(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
    public  boolean isTokenExpired(String token)
    {
        return extractClaim(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String username, UserDetails userDetails,String token) {
        //first check user is same as  in usedetails
        //token not expired
       return username.equals(userDetails.getUsername()) && !isTokenExpired(token);


    }
}
