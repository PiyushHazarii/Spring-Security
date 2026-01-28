package me.security.util;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {
    private static final String SECRET_KEY =
            "sldfkjsdlkfjslkdjflskdjflsdkjfklsdjflksdjflskjdflskdjflskdjflsdkjflsdkfjlsdkfjsldkfja@#";

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // valid for one hour

    public static String generateToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
