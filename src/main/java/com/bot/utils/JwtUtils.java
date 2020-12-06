package com.bot.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    private final String SECRET_KEY;
    private final SignatureAlgorithm signatureAlgorithm;

    public JwtUtils(String SECRET_KEY, SignatureAlgorithm signatureAlgorithm) {
        this.SECRET_KEY = Base64.encodeBase64String(SECRET_KEY.getBytes());
        this.signatureAlgorithm = signatureAlgorithm;
    }

    public String encode(String iss, long ttlMillis, Map<String, Object> claims) {
        if (claims == null) {
            claims = new HashMap<>();
        }

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setSubject(iss)
                .signWith(signatureAlgorithm, SECRET_KEY);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    public Claims decode(String jwtToken) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    public boolean isVerify(String jwtToken) {
        Algorithm algorithm = null;
        if (signatureAlgorithm == SignatureAlgorithm.HS256) {
            algorithm = Algorithm.HMAC256(Base64.decodeBase64(SECRET_KEY));
        } else {
            throw new RuntimeException();
        }

        JWTVerifier verifier = JWT.require(algorithm).build();
        verifier.verify(jwtToken);

        return true;
    }

    public static void main(String[] args){
        JwtUtils util = new JwtUtils("tom", SignatureAlgorithm.HS256);

        Map<String, Object> map = new HashMap<>();
        map.put("username", "tom");
        map.put("password", "123456");
        map.put("age", 20);

        String jwtToken = util.encode("tom", 30000, map);

        System.out.println(jwtToken);
        /*
        util.isVerify(jwtToken);
        System.out.println("合法");
        */

        util.decode(jwtToken).forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
