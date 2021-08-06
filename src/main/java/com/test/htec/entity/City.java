package com.test.htec.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String cityName;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "description")
	private String description;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "city_airport",
        joinColumns = @JoinColumn(name = "city_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "airport_id", referencedColumnName = "airport_id"))
	private List<Airport> airportList;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "city_route",
        joinColumns = @JoinColumn(name = "city_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "route_id", referencedColumnName = "id"))
	private List<Route> routeList;

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
