package com.ajay.entites;

import java.util.Date;


import com.ajay.dao.CarDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Cars")
public class Car 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long carid;
	
	private String name;
	private String color;
	private String transmission;
	private String brand;
	private String type;
	private Date year;
	private String description;
	private Integer price;
	
	@Column(columnDefinition = "longblob")
	private byte[] image;
	
	
	
	public CarDto getCarDto()
	{
		CarDto carDto=new CarDto();
		carDto.setCarid(carid);
		carDto.setName(name);
		carDto.setColor(color);
		carDto.setTransmission(transmission);
		carDto.setDescription(description);
		carDto.setBrand(brand);
		carDto.setType(type);
		carDto.setYear(year);
		carDto.setPrice(price);
		carDto.setReturnedImage(image);
		
		return carDto;
	}

}
