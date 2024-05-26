package com.ajay.dao;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;


import lombok.Data;

@Data
public class CarDto 
{
	
	private Long carid;
	
	private String name;
	private String color;
	private String transmission;
	private String brand;
	private String type;
	private Date year;
	private String description;
	private Integer price;
	
	private MultipartFile image; 
	
	private byte[] returnedImage;

}
