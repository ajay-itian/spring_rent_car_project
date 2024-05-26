package com.ajay.services.admin;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ajay.dao.BookACarDto;
import com.ajay.dao.CarDto;
import com.ajay.entites.BookACar;
import com.ajay.entites.Car;
import com.ajay.enums.BookCarStatus;
import com.ajay.repositotries.BookACarRepo;
import com.ajay.repositotries.CarRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServicesImpl implements AdminServices
{
	
	private final CarRepo carRepo;
	private final BookACarRepo bookACarRepo;

	@Override
	public boolean postCar(CarDto carDto) {
		// TODO Auto-generated method stub
		
		try {
			Car car=new Car();
			car.setName(carDto.getName());
			car.setBrand(carDto.getBrand());
			car.setColor(carDto.getColor());
			car.setPrice(carDto.getPrice());
			car.setType(carDto.getType());
			car.setTransmission(carDto.getTransmission());
			car.setDescription(carDto.getDescription());
			car.setYear(carDto.getYear());
			car.setImage(carDto.getImage().getBytes());
			carRepo.save(car);
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;

		}
	}

	@Override
	public List<CarDto> getAllCars() {
		// TODO Auto-generated method stub
		return carRepo.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
	}

	@Override
	public void deleteCar(Long carId) {
		// TODO Auto-generated method stub
		carRepo.deleteById(carId);
	}

	@Override
	public CarDto getCarById(Long id) {
		// TODO Auto-generated method stub
		Optional<Car> optionalcar=carRepo.findById(id);
		
		return optionalcar.map(Car::getCarDto).orElse(null);

}
	
	@Override
	public boolean updateCar(Long carId, CarDto carDto) {
	    Optional<Car> optionalCar = carRepo.findById(carId);
	    if (optionalCar.isPresent()) {
	        Car existingCar = optionalCar.get();

	        existingCar.setName(carDto.getName());
	        existingCar.setBrand(carDto.getBrand());
	        existingCar.setColor(carDto.getColor());
	        existingCar.setPrice(carDto.getPrice());
	        existingCar.setType(carDto.getType());
	        existingCar.setTransmission(carDto.getTransmission());
	        existingCar.setDescription(carDto.getDescription());
	        existingCar.setYear(carDto.getYear());

	        try {
	            if (carDto.getImage() != null)
	                existingCar.setImage(carDto.getImage().getBytes());
	        } catch (IOException e) {
	            log.error("Error occurred while reading image bytes", e);
	            // Handle the error, maybe throw a custom exception or return false
	            return false;
	        }

	        carRepo.save(existingCar);

	        log.info("Updated car: {}", existingCar);

	        return true;
	    }

	    return false;
	}

	@Override
	public List<BookACarDto> getAllBookings() {
		// TODO Auto-generated method stub
		return bookACarRepo.findAll().stream().map(BookACar::getCarDto).collect(Collectors.toList());
	}

	
	@Override
	public boolean changebookingstatus(Long id, String status) {
	    Optional<BookACar> optionalBookACar = bookACarRepo.findById(id);
	    if (optionalBookACar.isPresent()) {
	        BookACar existingBookACar = optionalBookACar.get();
	        if (status.equals("Approve")) {
	            existingBookACar.setBookCarStatus(BookCarStatus.APPROVED);
	        } else if (status.equals("Reject")) {
	            existingBookACar.setBookCarStatus(BookCarStatus.REJECTED);
	        }
	        bookACarRepo.save(existingBookACar);
	        return true;
	    }
	    return false;
	}



	

}
