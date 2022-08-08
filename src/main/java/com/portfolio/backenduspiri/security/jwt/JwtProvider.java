package com.portfolio.backenduspiri.security.jwt;

import com.portfolio.backenduspiri.security.entity.PrincipalUser;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
    
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    
    @Value("${portfolio.app.jwt.secret}")
    private String secret;
    @Value("${portfolio.app.jwt.expiration}")
    private int expiration;
    
    public String generateToken(Authentication authentication){
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        return Jwts.builder().setSubject(principalUser.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+expiration*1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    
    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
    
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            logger.error("[JwtProvider] - Malformated Token");
        }catch (UnsupportedJwtException e){
            logger.error("[JwtProvider] - Unsupported Token");
        }catch (ExpiredJwtException e){
            logger.error("[JwtProvider] - Expired Token");
        }catch (IllegalArgumentException e){
            logger.error("[JwtProvider] - Empty Token");
        }catch (SignatureException e){
            logger.error("[JwtProvider] - Unvalid Token");
        }
        return false;
    }
    
}
