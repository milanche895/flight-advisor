package com.test.htec.DTO;

import java.util.List;

import org.springframework.data.domain.Page;

import com.test.htec.entity.City;
import com.test.htec.entity.Comment;

public class CommentCityDTO {
	
	private String cityName;
	
	private String country;
	
	private String description;
	
	private Page<Comment> commentList;
	
	public CommentCityDTO() {
		
	}
	public CommentCityDTO(City city) {
		this.cityName = city.getCityName();
		this.country = city.getCountry();
		this.description = city.getDescription();
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
	public Page<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(Page<Comment> commentList2) {
		this.commentList = commentList2;
	}

	
}
