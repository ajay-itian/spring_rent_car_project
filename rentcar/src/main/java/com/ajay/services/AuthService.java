package com.ajay.services;

import javax.swing.text.StyledEditorKit.BoldAction;

import com.ajay.dao.SignupRequest;
import com.ajay.dao.UserDao;

public interface AuthService 
{
	
	UserDao createCustomer(SignupRequest signupRequest);
	
	
	boolean hasCustomerWithEmail(String email);
}
