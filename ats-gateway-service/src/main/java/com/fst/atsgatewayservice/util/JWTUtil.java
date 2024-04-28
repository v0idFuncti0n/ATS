package com.fst.atsgatewayservice.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    private static final String SECRET = "2aidBfF6amTd083JSggVA2Auth4vEUELtBiUpl3WrE82aidBfF6amTd083JSggVA2Auth4vEUELtBiUpl3WrE8";

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET).build().parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
    }

    public boolean isInvalid(String token) {
        return this.isTokenExpired(token);
    }

}