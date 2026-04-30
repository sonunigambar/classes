package com.services.identityservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class JwtToken {

     static final String SECRETS ="ykItSdStBwwwiGIL5Iw6Wo5ZtPg5UicokfEyoS1G3CQ=";

    public String generateToken(String userName, Collection<? extends GrantedAuthority> authorities) {//ADMIN, USER
        List<String> roles = authorities.stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.toList());
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        return createToken(userName, claims);
    }

    private String createToken(String userName,Map<String, Object>  roles) {
        return Jwts.builder().setClaims(roles)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*606*60))
                .setSubject(userName)
                .signWith(getSignkey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignkey() {
        byte[] decode = Decoders.BASE64.decode(SECRETS);
        return Keys.hmacShaKeyFor(decode);
    }

    public String validateToken(String token){
        return Jwts.parser()
                .setSigningKey(SECRETS)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }
}
            