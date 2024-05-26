package com.ajay.repositotries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ajay.dao.BookACarDto;
import com.ajay.entites.BookACar;

@Repository
public interface BookACarRepo extends JpaRepository<BookACar,Long>
{

	List<BookACar> findAllByUserId(Long userid);

}
