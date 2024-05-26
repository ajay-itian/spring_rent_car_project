package com.ajay.services.admin;

import java.io.IOException;
import java.util.List;

import com.ajay.dao.BookACarDto;
import com.ajay.dao.CarDto;

public interface AdminServices {
	
	
	boolean postCar(CarDto carDto);
	
	List<CarDto> getAllCars();
	
	void deleteCar(Long carId);
	
	CarDto getCarById(Long id);
	
	boolean updateCar(Long cardId,CarDto carDto);
	
	List<BookACarDto> getAllBookings();
	
	boolean changebookingstatus(Long bookingId,String status);
	
}
