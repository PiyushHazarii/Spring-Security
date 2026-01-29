package me.security.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.util.Date;

@Component
public class JWTUtil {
    private static final String SECRET_KEY =
            "sldfkjsdlkfjslkdjflskdjflsdkjfklsdjflksdjflskjdflskdjflskdjflsdkjflsdkfjlsdkfjsldkfja@#";

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // valid for one hour

    public String generateToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUserNameFromToken(String token) {
        // this is the double check when anyone call this mehtod with extracting the token then it will does for t
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return extractClaims(token).getSubject();
    }

        private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)   // your SECRET KEY
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean validateTokenm(String username, UserDetails userDetails, String token) {
        // check if username is same as userDetails
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        //  check if the token is not expired
    }

    private boolean isTokenExpired(String token) {
       return extractClaims(token).getExpiration().before(new Date());
    }

}
