package com.test.htec.service.implement;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.test.htec.DTO.CityDTO;
import com.test.htec.entity.Airport;
import com.test.htec.entity.City;
import com.test.htec.repository.AirportRepository;
import com.test.htec.repository.CityRepository;
import com.test.htec.service.CityService;

@Service
public class CityServiceImplement implements CityService {
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	AirportRepository airportRepository;
	
	@Autowired
	PermisionSystem permisionSystem;
	
	@Autowired
	MessageSource messageSource;

	@Override
	public CityDTO newCity(CityDTO cityDTO, String token) {
		
		if (permisionSystem.checkAdministratorAccess(token)) {
		
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
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
		}
	}
	@Override
	public CityDTO updateCity( String token, Long id) {
		
		if (permisionSystem.checkAdministratorAccess(token)) {
			
			if (Objects.nonNull(cityRepository.findOneById(id))) {
			
				City city = cityRepository.findOneById(id);
				
				String findCity = "\""+city.getCityName()+"\"";
				
				if (Objects.nonNull(airportRepository.findAllByCity(findCity))) {
					
					List<Airport> airportList = airportRepository.findAllByCity(findCity);
					
					city.setAirportList(airportList);
					CityDTO cityDTO = new CityDTO(city);
					
					cityRepository.save(city);
					
					return cityDTO;
				} else {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Airports doesn`t exist");
				}
				
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found");
		}
		
	}
	@Override
	public List<City> getAllCities(String token){
		List<City> citiesList = cityRepository.findAll();
		
		return citiesList;
	}
	@Override
	public List<Airport> getAirlineByCity(){
		List<Airport> airlineByCity = airportRepository.findAllByCity("\"Madang\"");
		
		return airlineByCity;
	}
}
