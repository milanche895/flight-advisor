package com.test.htec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.htec.DTO.CityDTO;
import com.test.htec.entity.City;
import com.test.htec.service.CityService;

@RestController
@RequestMapping("/advisor/auth")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@PostMapping(value = "/newcity", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CityDTO> newCity(@RequestBody CityDTO cityDTO, @RequestHeader("Authorization") String token){
		return new ResponseEntity<CityDTO>(cityService.newCity(cityDTO, token),HttpStatus.OK);
	}
	@GetMapping("/allcities")
	public ResponseEntity<List<City>> getAllCities(@RequestHeader("Authorization") String token){
		return new ResponseEntity<List<City>>(cityService.getAllCities(token),HttpStatus.OK);
	}

}
