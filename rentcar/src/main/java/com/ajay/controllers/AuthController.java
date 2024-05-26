package com.ajay.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajay.dao.AuthenticationRequest;
import com.ajay.dao.AuthenticationResponse;
import com.ajay.dao.SignupRequest;
import com.ajay.dao.UserDao;
import com.ajay.entites.User;
import com.ajay.repositotries.UserRepo;
import com.ajay.services.AuthService;
import com.ajay.services.jwt.UserService;
import com.ajay.utils.JwtUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController 
{
	private final AuthService authService;
	private final UserService userService;
	private final JwtUtils jwtUtils;
	private final UserRepo userRepo;
	private  final AuthenticationManager authenticationManager;
	
	
	
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> createCustomer(@RequestBody SignupRequest signupRequest)
	{
		
		if(authService.hasCustomerWithEmail(signupRequest.getEmail()))
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Email Already Exists.Try Again another email");
	UserDao createUserDao=	authService.createCustomer(signupRequest);
	
	if(createUserDao == null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
	return ResponseEntity.status(HttpStatus.CREATED).body(createUserDao);
	
	}
	
	
	
	@PostMapping("/login")
	public AuthenticationResponse createAuthenticationResponse(@RequestBody AuthenticationRequest authenticationRequest)
	throws BadCredentialsException,DisabledException,UsernameNotFoundException
	{
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), 
					authenticationRequest.getPassword()));
			
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("incorect username and password");
		}
		
		final UserDetails userDetails=userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
		Optional<User> optionaluser=userRepo.findFirstByEmail(userDetails.getUsername());
		
		final String jwt=jwtUtils.generateToken(userDetails);
		AuthenticationResponse authenticationResponse=new AuthenticationResponse();
		
		if(optionaluser.isPresent())
		{
			authenticationResponse.setJwt(jwt);
			authenticationResponse.setUserid(optionaluser.get().getId());
			authenticationResponse.setUserRole(optionaluser.get().getUserrole());
		}
		
		return authenticationResponse;
		
		
	}
}
