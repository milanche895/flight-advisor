package com.test.htec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.htec.entity.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {
	List<Route> findAllBySourceAirportOrDestinationAirport(String sourceAirport, String destinationAirport);
	
	List<Route> findAllBySourceAirportAndDestinationAirportOrderByPriceAsc(String sourceAirport, String destinationAirport);
}
