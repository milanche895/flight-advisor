package com.test.htec.DTO;

import java.util.List;

import com.test.htec.entity.City;
import com.test.htec.entity.Route;

public class TravelDTO {
	
	private String sourceCity;
	
	private String destinationCity;
	
	private List<Route> routeList;
	
	public TravelDTO() {
		
	}
	public TravelDTO(City city) {
		this.sourceCity = city.getCityName();
		this.routeList = city.getRouteList();
	}

	public String getSourceCity() {
		return sourceCity;
	}

	public void setSourceCity(String sourceCity) {
		this.sourceCity = sourceCity;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public List<Route> getRouteList() {
		return routeList;
	}

	public void setRouteList(List<Route> routeList) {
		this.routeList = routeList;
	}
	
}
