package com.ajay.dao;


import com.ajay.enums.UserRole;

import lombok.Data;

@Data
public class UserDao 
{
	
	
	private long userid;
	private String name;
	private String email;
	private String password;
	private UserRole userrole;

}
