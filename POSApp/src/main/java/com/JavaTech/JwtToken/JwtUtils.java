package com.JavaTech.JwtToken;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

@Component
public class JwtUtils {

	private String secretKey = "abbadadadd";
	
	private static final long TOKEN_VALIDITY= 1000;
	   // @Value("${jwt_secret}")
	    private final static String SECRET_KEY="A@bEdgh3sf24sdrf3422abbadadadd2131dsfsfsdfasdad";

		public String generateToken(UserDetails userDetails) {
			Map<String,Object> claims=new HashMap<>();
			return doGenerateToken(claims,userDetails.getUsername());
		}
		
		private String doGenerateToken(Map<String,Object> claims,String subject) {
			 
			return Jwts.builder().setClaims(claims).setSubject(subject)
					.setIssuedAt(new Date(System.currentTimeMillis())).
					setExpiration(new Date(System.currentTimeMillis()+TOKEN_VALIDITY *30))
					.signWith(SignatureAlgorithm.HS256,TextCodec.BASE64.encode(SECRET_KEY))
				    .compact();
		}

   /* public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }*/

    public boolean validateToken(String token, String username) {
       // final String extractedUsername = extractClaims(token).getSubject();
       // return (extractedUsername.equals(username) && !isTokenExpired(token));
    	return false;
    }

    private boolean isTokenExpired(String token) {
      //  return extractClaims(token).getExpiration().before(new Date());
    	return false;
    }
}
