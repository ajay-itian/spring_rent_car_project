package com.ajay.services.customer;

import java.util.List;

import com.ajay.dao.BookACarDto;
import com.ajay.dao.CarDto;

public interface CustomerServices 
{

	
	List<CarDto> getAllCars();
	
	CarDto getCarById(Long id);
	
	boolean bookACar(Long id,BookACarDto bookACarDto);
	
	List<BookACarDto> getBookingsByUserId(Long userid);
}
