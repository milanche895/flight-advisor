package com.test.htec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.htec.entity.AdvisorUser;


@Repository
public interface UserRepository extends JpaRepository<AdvisorUser, Long> {
	
	public AdvisorUser findOneByUserName(String userName);
	
	public AdvisorUser findOneById(Long id);
}
