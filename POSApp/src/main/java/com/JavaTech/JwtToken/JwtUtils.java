package com.JavaTech.JwtToken;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	    private static final long TOKEN_VALIDITY= 1000;
	    //private final static String SECRET_KEY="AbEdgh3sf24sdrf3422abbadadadd2131dsfsfsdfasdad";
	    private final static String SECRET_KEY="5367566B5970337367639792F423F4528482B4D6251655468576D5A71347437";

		public String generateToken(UserDetails userDetails) {
			Map<String,Object> claims=new HashMap<>();
			return doGenerateToken(claims,userDetails.getUsername());
		}
		
		private String doGenerateToken(Map<String,Object> claims,String subject) {
			 
			return Jwts.builder().setClaims(claims).setSubject(subject)
					.setIssuedAt(new Date(System.currentTimeMillis())).
					setExpiration(new Date(System.currentTimeMillis()+TOKEN_VALIDITY *60*30))
					//.signWith(SignatureAlgorithm.HS256,TextCodec.BASE64.encode(SECRET_KEY))
					.signWith(getSignKey(), SignatureAlgorithm.HS256)
				    .compact();
		}

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
	}
	public Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}
	public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
		Claims claims= extractAllClaims(token);
		return claimsResolver.apply(claims);
		
		
	}

    public boolean validateToken(String token, UserDetails userDetails) {
        final String extractedUsername = extractUsername(userDetails.getUsername());
        return (extractedUsername.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
    	return extractExpiration(token).before(new Date());
    }
    
    private Key getSignKey() {
    	byte[] keybytes=Decoders.BASE64.decode(SECRET_KEY);
    	return Keys.hmacShaKeyFor(keybytes);
    }
}
