package com.test.htec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.htec.entity.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {

	public List<Airport> findAllByCity(String city);
	
	public Airport findOneByCity(String city);
}
