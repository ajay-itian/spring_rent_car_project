package com.ajay.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajay.dao.BookACarDto;
import com.ajay.dao.CarDto;
import com.ajay.services.customer.CustomerServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController 
{
	@Autowired
	private final CustomerServices customerServices;
	
	
	@GetMapping("/cars")
	public ResponseEntity<List<CarDto>> getAllCars()
	{
		List<CarDto> carDtos=customerServices.getAllCars();
		
		return ResponseEntity.ok(carDtos);
	}

	@GetMapping("/car/{id}")
	public ResponseEntity<CarDto> getCarById(@PathVariable Long id)
	{
		CarDto carDto=customerServices.getCarById(id);
		if(carDto!=null)return ResponseEntity.ok(carDto);
		return ResponseEntity.notFound().build();
		
	}
	
	@PostMapping("/car/book/{id}")
	public ResponseEntity<?> bookACar(@PathVariable Long id, @RequestBody BookACarDto bookACarDto) {
		
		System.out.println("Postmapping car id"+id);
		log.info("{}"+bookACarDto);
		boolean success=customerServices.bookACar(id, bookACarDto);
		if(success) return ResponseEntity.ok().build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	
	@GetMapping("/car/bookings/{userId}")
	public ResponseEntity<?> getBookingByUserId(@PathVariable Long userId)
	{
	    return ResponseEntity.ok(customerServices.getBookingsByUserId(userId));
	}

}
