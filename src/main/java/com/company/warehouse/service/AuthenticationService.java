package com.company.warehouse.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;

public class AuthenticationService {
    static final long EXPIRATION_TIME = 864_000_00; // 1 day in milliseconds
    static final String SIGNING_KEY = "SecretKey";
    static final String PREFIX = "Bearer";

    /**
     * Add token to Authorization header
     */
    static public void addToken(HttpServletResponse res, String username) {
        String jwtToken = createToken(username);
        res.addHeader("Authorization", PREFIX + " " + jwtToken);
        res.addHeader("Access-Control-Expose-Headers", "Authorization");
    }

    /**
     * Create jwt token with username
     * @param username
     * @return
     */
    static public String createToken(String username) {
        return Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
                .compact();
    }

    /**
     * Get token from Authorization header
     * @return
     */
    static public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(SIGNING_KEY)
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();
            if (user != null)
                return new UsernamePasswordAuthenticationToken(user, null, emptyList());
        }
        return null;
    }
}
