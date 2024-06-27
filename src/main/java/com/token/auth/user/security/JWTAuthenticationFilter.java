package com.token.auth.user.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.token.auth.user.service.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JWTGenerator jwtHelper;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (request.getServletPath().contains("/api/")) {
			filterChain.doFilter(request, response);
			return;
		}

		String requestHeader = request.getHeader("Authorization");
		String username = null;
		String token = null;

		if (requestHeader == null || !requestHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		token = requestHeader.substring(7);
		try {
			username = this.jwtHelper.getUsernameFromToken(token);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ExpiredJwtException e) {
			System.out.println("jwt token expired");
			throw new ServletException("token_expired");
		} catch (MalformedJwtException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			// fetch user detail from username
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			Boolean validateToken = jwtHelper.validateToken(token, userDetails);
			
			if (validateToken) {
				// set the authentication
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else {
				logger.info("Validation fails !!");
			}
		}
		filterChain.doFilter(request, response);
	}

}
