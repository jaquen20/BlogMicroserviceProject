package com.sandeep.api_gateway.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;


@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secretKey ;

    public Claims extractAllClaims(String token){
        Key key= Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    public String getUsernameFrom(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public Date extractExpiration(String token){
        return extractAllClaims(token).getExpiration();
    }
    public  boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public boolean ValidateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return !isTokenExpired(token);
        }catch (Exception e){
            return  false;
        }
    }
    public String getUserIdFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(token).getBody().getSubject();
    }
    public boolean TokenValidation(String token){
        try {
            Claims claims=Jwts.parserBuilder().setSigningKey(secretKey.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return !claims.getExpiration().before(new Date());
        }catch (JwtException exception){
            System.out.println(exception);
            return false;
        }
    }
}