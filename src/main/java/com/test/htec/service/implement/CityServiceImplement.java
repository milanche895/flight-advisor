package com.test.htec.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.test.htec.DTO.CityDTO;
import com.test.htec.entity.Airport;
import com.test.htec.entity.City;
import com.test.htec.entity.Route;
import com.test.htec.repository.AirportRepository;
import com.test.htec.repository.CityRepository;
import com.test.htec.repository.RouteRepository;
import com.test.htec.service.CityService;

@Service
public class CityServiceImplement implements CityService {
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	AirportRepository airportRepository;
	
	@Autowired
	RouteRepository routeRepository;
	
	@Autowired
	PermisionSystem permisionSystem;
	
	@Autowired
	MessageSource messageSource;

	@Override
	public CityDTO newCity(CityDTO cityDTO, String token) {
		
		if (permisionSystem.checkAdministratorAccess(token)) {
		
			City city = new City();
			
			if (Objects.nonNull(cityDTO.getCityName()) && Objects.nonNull(cityDTO.getCountry()) && Objects.nonNull(cityDTO.getDescription())){
				
				if (Objects.isNull(cityRepository.findOneByCityName(cityDTO.getCityName()))) {
					city.setCityName(cityDTO.getCityName());
					city.setCountry(cityDTO.getCountry());
					city.setDescription(cityDTO.getDescription());
					
					cityRepository.save(city);
					
					return cityDTO;
				} else {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City already exist");
				}
	
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please insert all data!");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
		}
	}
	@Override
	public CityDTO updateCityAirportAndRoute(String token, Long id) {
		
		if (permisionSystem.checkAdministratorAccess(token)) {
			
			if (Objects.nonNull(cityRepository.findOneById(id))) {
			
				City city = cityRepository.findOneById(id);
				
				String findCity = "\""+city.getCityName()+"\"";
				
				if (Objects.nonNull(airportRepository.findAllByCity(findCity))) {
					
					List<Airport> airportList = airportRepository.findAllByCity(findCity);
					
					city.setAirportList(airportList);
					
					List<Route> allRouteList = new ArrayList<Route>();
					
					for (Airport air : airportList) {
						String iata = air.getIata().substring(1, air.getIata().length()-1);
						String icao = air.getIcao().substring(1, air.getIcao().length()-1);
						List<Route> routeList = routeRepository.findAllBySourceAirportOrDestinationAirport(iata, icao);
						allRouteList.addAll(routeList);
						System.out.println(iata);
						System.out.println(icao);
					}
					city.setRouteList(allRouteList);
					CityDTO cityDTO = new CityDTO(city);
					
					cityRepository.save(city);
					
					return cityDTO;
				} else {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Airports doesn`t exist");
				}
				
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
		}
		
	}
	@Override
	public CityDTO updateCity(CityDTO cityDTO, String token) {
		if (permisionSystem.checkAdministratorAccess(token)) {
			if (Objects.nonNull(cityRepository.findOneById(cityDTO.getId()))) {
				
				City city = cityRepository.findOneById(cityDTO.getId());
				
				if (Objects.nonNull(cityDTO.getCityName())) {
					city.setCityName(cityDTO.getCityName());
				} else {
					cityDTO.setCityName(city.getCityName());
				}
				if (Objects.nonNull(cityDTO.getCountry())) {
					city.setCountry(cityDTO.getCountry());
				} else {
					cityDTO.setCountry(city.getCountry());
				}
				if (Objects.nonNull(cityDTO.getDescription())) {
					city.setDescription(cityDTO.getDescription());
				} else {
					cityDTO.setDescription(city.getDescription());
				}
				cityDTO.setAirportList(city.getAirportList());
				cityDTO.setRouteList(city.getRouteList());
				
				cityRepository.save(city);
				
				return cityDTO;
				
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
		}
	}
	@Override
	public List<City> getAllCities(String token){
		
		List<City> citiesList = cityRepository.findAll();
		
		return citiesList;
	}

}
