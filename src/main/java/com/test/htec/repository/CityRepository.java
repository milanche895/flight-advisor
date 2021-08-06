package com.test.htec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.htec.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	
	public City findOneById(Long id);

}
