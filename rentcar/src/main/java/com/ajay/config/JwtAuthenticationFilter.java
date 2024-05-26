	package com.ajay.config;

import java.io.IOException;

import org.apache.catalina.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.ajay.services.jwt.UserService;
import com.ajay.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	 
	    private final UserService userDetailsService;

	   
	    private  final JwtUtils jwtUtil;

	    @Override
	    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain chain)
	            throws ServletException, IOException {

	        final String authHeader = request.getHeader("Authorization");

	    final    String userEmail;
	      final  String jwt ;

	        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader,"Bearer ")) {
	          chain.doFilter(request, response);
	          return;
	        }
	        	
	        jwt = authHeader.substring(7);
	        userEmail = jwtUtil.extractUsername(jwt);

	        if (StringUtils.isNotEmpty(userEmail)
	        		
	        		&& SecurityContextHolder.getContext().getAuthentication()==null)
	        {

	            UserDetails userDetails = this.userDetailsService.userDetailsService().
	            		loadUserByUsername(userEmail);

	            if (jwtUtil.isTokenValid(jwt, userDetails)) {
	            	
	            	
	            	SecurityContext context=SecurityContextHolder.createEmptyContext();
	                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
	                        userDetails, null, userDetails.getAuthorities());
	                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                context.setAuthentication(authToken);
	                SecurityContextHolder.setContext(context);
	            }
	        }
	        chain.doFilter(request, response);
	    }

}
