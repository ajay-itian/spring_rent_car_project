package com.ajay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ajay.enums.UserRole;
import com.ajay.services.jwt.UserService;
import com.ajay.services.jwt.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration 
{
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final UserService userservice;
	private final UserServiceImpl userServiceImpl;
	

	
	
	@Bean
	public SecurityFilterChain securityfilterchain(HttpSecurity http)throws Exception
	{
		http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(request->
		request
		.requestMatchers("/api/auth/**").permitAll()
		.requestMatchers("/api/admin/**").hasAnyAuthority(UserRole.ADMIN.name())
		.requestMatchers("/api/user/**").hasAnyAuthority(UserRole.CUSTOMER.name())
		.anyRequest().authenticated())
		.sessionManagement(manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(AuthenticationProvider())
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		
		return http.build();
		
	}



	@Bean
	public AuthenticationProvider AuthenticationProvider() {
		
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userservice.userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		// TODO Auto-generated method stub
		return authenticationProvider;
	}



	@Bean
	public PasswordEncoder passwordEncoder() {
		// TODO Auto-generated method stub
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception
	{
		return config.getAuthenticationManager();
	}
}
