package com.w2m.supermaintenance.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.w2m.supermaintenance.security.services.UserDetailsImpl;

import java.util.Date;

import javax.xml.bind.DatatypeConverter;

@Log4j2
@Service
public class JwtUtils {

    @Value("${w2m.supermaintenance.jwtSecret}")
    private String jwtSecret;

    @Value("${w2m.supermaintenance.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        String generatedToken = Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // 1 day expiration
                .signWith(SignatureAlgorithm.HS512, getSecretBytes())
                .compact();   
        return generatedToken;        
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(getSecretBytes()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        try {
            return Jwts.parser().setSigningKey(getSecretBytes()).parseClaimsJws(token).getBody().getSubject();
        } catch (SignatureException e) {
            throw new RuntimeException("Invalid JWT signature: " + e.getMessage());
        }
    }

    private byte[] getSecretBytes() {
        String base64Key = DatatypeConverter.printBase64Binary(jwtSecret.getBytes());
        return DatatypeConverter.parseBase64Binary(base64Key);
    }
}
