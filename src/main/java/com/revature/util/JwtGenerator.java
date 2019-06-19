package com.revature.util;

import java.util.Date;

import com.revature.models.User;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtGenerator {
	
	private static String createJwt(User subject) {
		
		// The Jwt Signature Algorithm used to sign the token
		SignatureAlgorithm sigAlg = SignatureAlgorithm.HS256;
		
		long nowMillis = System.currentTimeMillis();
		
		// Configure the Jwt and build the token
		JwtBuilder builder = Jwts.builder()
							.setId(Integer.toString(subject.getUserId()))
							.setSubject(subject.getUserUsername())
							.setIssuer("AustinAPI")
							.claim("role", subject.getUserRole())
							.setExpiration(new Date(nowMillis + JwtConfig.EXPIRATION))
							.signWith(sigAlg, JwtConfig.SIGNING_KEY);
		
		return builder.compact();
	}
	
	private JwtGenerator() {
		super();
	}

}
