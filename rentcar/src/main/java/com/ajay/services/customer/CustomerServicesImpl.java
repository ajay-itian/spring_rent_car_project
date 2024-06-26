package com.ajay.services.customer;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ajay.dao.BookACarDto;
import com.ajay.dao.CarDto;
import com.ajay.entites.BookACar;
import com.ajay.entites.Car;
import com.ajay.entites.User;
import com.ajay.enums.BookCarStatus;
import com.ajay.repositotries.BookACarRepo;
import com.ajay.repositotries.CarRepo;
import com.ajay.repositotries.UserRepo;
import com.ajay.services.admin.AdminServicesImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServicesImpl implements CustomerServices{

	
	private final CarRepo carrepo;
	private final UserRepo userrepo;
	private final BookACarRepo bookACarRepo;
	
	
	

	@Override
	public List<CarDto> getAllCars() {
		// TODO Auto-generated method stub
		
		
		return carrepo.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
	}

	@Override
	public CarDto getCarById(Long id) {
		// TODO Auto-generated method stub
		
		Optional<Car> optionalcar=carrepo.findById(id);
		
		return optionalcar.map(Car::getCarDto).orElse(null);
	}

	@Override
	public boolean bookACar(Long carid, BookACarDto bookACarDto) {
	 
		log.info("car id",carid);
		log.info("{}"+bookACarDto);
	    log.info("User: {}", userrepo.findById(bookACarDto.getUserId()));
	    log.info("Car: {}", carrepo.findById(carid));

	    Optional<User> optionalUser = userrepo.findById(bookACarDto.getUserId());
	    Optional<Car> optionalCar = carrepo.findById(carid);
	   

	    if (optionalCar.isPresent() && optionalUser.isPresent()) {
	        BookACar bookACar = new BookACar();
	        long diffInMilliseconds = bookACarDto.getToDate().getTime() - bookACarDto.getFromDate().getTime();
	        long days = TimeUnit.MILLISECONDS.toDays(diffInMilliseconds);
	        bookACar.setDays(days);
	        bookACar.setUser(optionalUser.get());
	        bookACar.setCar(optionalCar.get());
	        bookACar.setPrice(optionalCar.get().getPrice() * days);
	        bookACar.setFromDate(bookACarDto.getFromDate()); // Shouldn't this be set from bookACarDto?
	        bookACar.setToDate(bookACarDto.getToDate()); // Shouldn't this be set from bookACarDto?
	        bookACar.setBookCarStatus(BookCarStatus.PENDING);
	        bookACarRepo.save(bookACar);
	        return true;
	    }

	    return false;
	}

	@Override
	public List<BookACarDto> getBookingsByUserId(Long userid) {
		// TODO Auto-generated method stub
		return bookACarRepo.findAllByUserId(userid).stream().map(BookACar::getCarDto).collect(Collectors.toList());
	}


}
