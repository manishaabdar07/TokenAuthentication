package com.token.auth.user.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.token.auth.user.service.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTGenerator {

	// token validity time
	public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60 * 1000;

	private String secret = "BXTzRlVD0jwuqQsXp1jgz+QVwRTB9DLlJPNIywm/IVzdh77vKCriR7TP6AvdMDIH";

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	// retrieve username from jwt token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}

	/**
	 * Check whether the created token expired or not. *
	 * 
	 * @param tokenCreationDate
	 * @return true or false
	 */
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// while creating the token -
	// 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
	// 2. Sign the JWT using the HS512 algorithm and secret key.
	// 3. According to JWS Compact
	// Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	// compaction of the JWT to a URL-safe string
	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		Map<String, Object> map = new HashMap<>();
		map.put("id", userPrincipal.getId());
		map.put("role", userPrincipal.getRole());
		map.put("username", userPrincipal.getUsername());
		return Jwts.builder().claim("id", userPrincipal.getId()).claim("role", userPrincipal.getRole())
				.claim("username", userPrincipal.getUsername()).setSubject((userPrincipal.getEmail()))
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	// validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		UserDetailsImpl userPrincipal = (UserDetailsImpl) userDetails;
		return (username.equals(userPrincipal.getEmail()) && !isTokenExpired(token));
	}

}
