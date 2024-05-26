package com.ajay.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ajay.dao.SignupRequest;
import com.ajay.dao.UserDao;
import com.ajay.entites.User;
import com.ajay.enums.UserRole;
import com.ajay.repositotries.UserRepo;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService
{

	private final UserRepo userRepo;

	
	@PostConstruct
	public void createAdminAccount() {
		
	User adminaccount =	userRepo.findByUserrole(UserRole.ADMIN);
	
			if(adminaccount==null)
			{
				User newAdminAccount =new User();
				
				newAdminAccount.setName("Admin");
				newAdminAccount.setUserrole(UserRole.ADMIN);
				newAdminAccount.setEmail("admin@test.com");
				newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin"));
				userRepo.save(newAdminAccount);
			}
	}
	
	
	@Override
	public UserDao createCustomer(SignupRequest signupRequest) {
		// TODO Auto-generated method stub
		User user=new User();
		user.setEmail(signupRequest.getEmail());
		user.setName(signupRequest.getName());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
		user.setUserrole(UserRole.CUSTOMER);
		User createCustomer =	userRepo.save(user);
		UserDao createUserDao =new UserDao();
		createUserDao.setUserid(createCustomer.getId());
		createUserDao.setName(createCustomer.getName());
		createUserDao.setEmail(createCustomer.getEmail());
		createUserDao.setUserrole(createCustomer.getUserrole());
		return createUserDao;
	}

	@Override
	public boolean hasCustomerWithEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findFirstByEmail(email).isPresent();
	}

}
