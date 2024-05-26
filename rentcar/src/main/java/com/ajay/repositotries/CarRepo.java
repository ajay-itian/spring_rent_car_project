package com.ajay.repositotries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ajay.entites.Car;

@Repository
public interface CarRepo extends JpaRepository<Car, Long>{
	
	
	

}
