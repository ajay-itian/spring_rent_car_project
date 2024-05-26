package com.ajay.dao;

import java.util.Date;

import com.ajay.enums.BookCarStatus;

import lombok.Data;

@Data
public class BookACarDto 
{
	private Long id;
	private Long userId;
	private Date fromDate;
	private Date toDate;
	
	private Long days;
	private Long price;
	

	private BookCarStatus bookCarStatus;

	private String email;
	private String username;
}
