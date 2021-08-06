package com.test.htec.service;

import java.util.List;

import com.test.htec.DTO.CityDTO;
import com.test.htec.entity.Airport;
import com.test.htec.entity.City;

public interface CityService {

	CityDTO newCity(CityDTO cityDTO, String token);

	List<City> getAllCities(String token);

	CityDTO updateCityAirportAndRoute(String token, Long id);

	CityDTO updateCity(CityDTO cityDTO, String token);

}
