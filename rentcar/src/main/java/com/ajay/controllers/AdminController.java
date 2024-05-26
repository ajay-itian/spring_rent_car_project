package com.ajay.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajay.dao.CarDto;
import com.ajay.services.admin.AdminServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController 
{
	
	private final AdminServices adminServices;
	
	@PostMapping("/car")
	public ResponseEntity<?> postCar(@ModelAttribute CarDto carDto) 
	{
		
		boolean success=adminServices.postCar(carDto);
		if(success)
			return ResponseEntity.status(HttpStatus.CREATED).build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
	}
	
	@GetMapping("/cars")
	public ResponseEntity<List<CarDto>> getAllCars()
	{
		List<CarDto> carDtos=adminServices.getAllCars();
		
		return ResponseEntity.ok(carDtos);
	}
	
	
	@DeleteMapping("/car/{carId}")
	public ResponseEntity<Void> deleteCar(@PathVariable Long carId)
	{
		
		adminServices.deleteCar(carId	);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("/car/{carId}")
	public ResponseEntity<CarDto> getCarById(@PathVariable Long  carId)
	{
		CarDto carDto = adminServices.getCarById(carId);
		
		if(carDto!=null)return ResponseEntity.ok(carDto);
		return ResponseEntity.notFound().build();
	}
	
	
	@PutMapping("/car/{carId}")
	public ResponseEntity<?> updateCar(@PathVariable Long carId,@ModelAttribute CarDto carDto) throws IOException
	{
		boolean success=adminServices.updateCar(carId, carDto);
		if(success)return ResponseEntity.ok().build();
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/car/bookings")
	public ResponseEntity<?> getAllBookings()
	{
		return ResponseEntity.ok(adminServices.getAllBookings());
	}

	@GetMapping("/car/bookings/{bookingId}/{status}")
	public ResponseEntity<?> changeBookStatus(@PathVariable Long bookingId, @PathVariable String status) {
	    boolean success = adminServices.changebookingstatus(bookingId, status);
	    if (success) {
	        return ResponseEntity.ok().build();
	    }
	    return ResponseEntity.notFound().build();
	}

}
