package com.services.apigateway.util;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    static final String SECRETS ="ykItSdStBwwwiGIL5Iw6Wo5ZtPg5UicokfEyoS1G3CQ=";

    public String validateToken(String token){
        return Jwts.parser()
                .setSigningKey(SECRETS)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }
}
