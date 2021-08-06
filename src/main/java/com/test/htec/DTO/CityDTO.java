package com.test.htec.DTO;

import java.util.List;

import com.test.htec.entity.Airport;
import com.test.htec.entity.City;
import com.test.htec.entity.Route;

public class CityDTO {
	
	private Long id; 
	
	private String cityName;
	
	private String country;
	
	private String description;
	
	private List<Airport> airportList;
	
	private List<Route> routeList;
	
	public CityDTO() {
		
	}
	public CityDTO(City city) {
		
		this.id = city.getId();
		this.cityName = city.getCityName();
		this.country = city.getCountry();
		this.description = city.getDescription();
		this.airportList = city.getAirportList();
		this.routeList = city.getRouteList();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Airport> getAirportList() {
		return airportList;
	}
	public void setAirportList(List<Airport> airportList) {
		this.airportList = airportList;
	}
	public List<Route> getRouteList() {
		return routeList;
	}
	public void setRouteList(List<Route> routeList) {
		this.routeList = routeList;
	}
	
}
