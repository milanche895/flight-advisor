package com.test.htec.service.implement;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.test.htec.DTO.CityDTO;
import com.test.htec.entity.City;
import com.test.htec.repository.CityRepository;
import com.test.htec.service.CityService;

@Service
public class CityServiceImplement implements CityService {
	
	@Autowired
	CityRepository cityRepository;

	@Override
	public CityDTO newCity(CityDTO cityDTO, String token) {
		
		City city = new City();
		
		if (Objects.nonNull(cityDTO.getCityName()) && Objects.nonNull(cityDTO.getCountry()) && Objects.nonNull(cityDTO.getDescription())){
			
			city.setCityName(cityDTO.getCityName());
			city.setCountry(cityDTO.getCountry());
			city.setDescription(cityDTO.getDescription());
			
			cityRepository.save(city);
			
			return cityDTO;

		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Test");
		}
	}
	@Override
	public List<City> getAllCities(String token){
		List<City> citiesList = cityRepository.findAll();
		
		return citiesList;
	}
}
