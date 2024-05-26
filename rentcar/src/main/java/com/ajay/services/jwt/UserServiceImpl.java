package com.ajay.services.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ajay.repositotries.UserRepo;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepo userRepo;
	
	@Override
	public UserDetailsService userDetailsService() {
		// TODO Auto-generated method stub
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				// TODO Auto-generated method stub
				return userRepo.findFirstByEmail(username).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
			}
		};
	}

}
