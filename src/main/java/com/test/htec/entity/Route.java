package com.test.htec.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "route")
public class Route {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "airline")
	private String airline;
	
	@Column(name = "airline_id")
	private Long airlineId;
	
	@Column(name = "source_airport")
	private String sourceAirport;
	
	@Column(name = "source_airport_id")
	private Long sourceAirportId;
	
	@Column(name = "destination_airport")
	private String destinationAirport;
	
	@Column(name = "destination_airport_id")
	private Long destinationAirportId;
	
	@Column(name = "codeshare")
	private String codeshare;
	
	@Column(name = "stops")
	private Integer stops;
	
	@Column(name = "equipment")
	private String equipment;
	
	@Column(name = "price")
	private Double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public Long getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(Long airlineId) {
		this.airlineId = airlineId;
	}

	public String getSourceAirport() {
		return sourceAirport;
	}

	public void setSourceAirport(String sourceAirport) {
		this.sourceAirport = sourceAirport;
	}

	public Long getSourceAirportId() {
		return sourceAirportId;
	}

	public void setSourceAirportId(Long sourceAirportId) {
		this.sourceAirportId = sourceAirportId;
	}

	public String getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(String destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public Long getDestinationAirportId() {
		return destinationAirportId;
	}

	public void setDestinationAirportId(Long destinationAirportId) {
		this.destinationAirportId = destinationAirportId;
	}

	public String getCodeshare() {
		return codeshare;
	}

	public void setCodeshare(String codeshare) {
		this.codeshare = codeshare;
	}

	public Integer getStops() {
		return stops;
	}

	public void setStops(Integer stops) {
		this.stops = stops;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
