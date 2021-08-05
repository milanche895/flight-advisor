package com.test.htec.service;

import java.util.List;

import com.test.htec.DTO.CityDTO;
import com.test.htec.entity.City;

public interface CityService {

	CityDTO newCity(CityDTO cityDTO, String token);

	List<City> getAllCities(String token);

}
