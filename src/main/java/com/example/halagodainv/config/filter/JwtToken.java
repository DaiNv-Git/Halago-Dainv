package com.example.halagodainv.config.filter;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Slf4j
@Component
public class JwtToken {
    private final String JWT_SECRET = "morHn10/05/2023@!123!!!###$$%%(())";
    private final long JWT_EXPIRATION = 30 * 24 * 3600;
    //lay token
    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        // Tạo chuỗi json web token từ userName của user.
        return Jwts.builder().setClaims(new HashMap<>())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)// phan quyen user
                .compact();
    }
    //refresh token de lay token moi thay the token het han
    public String generateRefreshToken(UserDetails userDetails) {
        return Jwts.builder().setClaims(new HashMap<>())
                .setSubject(userDetails.getUsername())
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
    }
    // Lấy thông tin user từ jwt
    public String getUserNameFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
