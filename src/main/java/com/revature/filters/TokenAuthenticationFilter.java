package com.revature.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.Principal;
import com.revature.util.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@WebFilter("/*")
public class TokenAuthenticationFilter extends HttpFilter {

	private static final long serialVersionUID = 1L;

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// Grab the Jwt from the header of the request
		String header = request.getHeader(JwtConfig.HEADER);
		
		// Check to see if the Jwt is there 
		if (header == null || !header.startsWith(JwtConfig.PREFIX)) {
			
			// Set the request to unauthenticated
			request.setAttribute("isAuthenticated", false);
			
			// send the request and response to the appropriate filter
			chain.doFilter(request, response);
			return;
		}
		
		// If the token passes the appropriate check, strip the prefix from the token
		String token = header.replaceAll(JwtConfig.PREFIX, "");
		
		try {
			
			// Create a claims parser to parse through the token
			Claims claims = Jwts.parser()
							.setSigningKey(JwtConfig.SIGNING_KEY)
							.parseClaimsJws(token)
							.getBody();
			
			// Make a new principal object to store the id, username, and role
			Principal principal = new Principal();
			
			// Set the values of the principal object
			principal.setId(Integer.parseInt(claims.getId()));
			principal.setUsername(claims.getSubject());
			principal.setRole(claims.get("role", Integer.class));
			
			// Set the request attributes to authenticated and store the parsed principal
			request.setAttribute("isAuthenticated", true);
			request.setAttribute("principal", principal);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		// Send the request and response through with the newly parsed principal
		chain.doFilter(request, response);
	}
	
	
}
