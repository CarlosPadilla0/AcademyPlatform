package com.hitss.AcademyPlatform.security.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitss.AcademyPlatform.security.SimpleGrantedAuthorityJsonCreator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.hitss.AcademyPlatform.security.TokenJWTConfig.*;

public class JwtValidationFilter extends BasicAuthenticationFilter {


	public JwtValidationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String path = request.getRequestURI();
		if (path.equals("/login") || path.equals("/api/auth/register")) {
			chain.doFilter(request, response);
			return; 
		}

		String header = request.getHeader(HEADER_AUTHORIZATION);
		if (header == null || !header.startsWith(TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		String token = header.replace(TOKEN_PREFIX, "");

		try {
			Claims claims = Jwts.parser()
					.verifyWith(SECRET_KEY) 
					.build()
					.parseSignedClaims(token)
					.getPayload();

			String username = claims.getSubject();
			Object authoritiesClaims = claims.get("authorities");

			Collection<? extends GrantedAuthority> authorities = Arrays.asList(
					new ObjectMapper()
					.addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
					.readValue(authoritiesClaims.toString(), SimpleGrantedAuthority[].class));

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(request, response);

		} catch (Exception e) {
			Map<String, String> body = new HashMap<>();
			body.put("error", e.getMessage());
			body.put("message", "El token JWT no es valido o ha expirado");

			response.getWriter().write(new ObjectMapper().writeValueAsString(body));
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType(CONTENT_TYPE);
		}
	}
}
