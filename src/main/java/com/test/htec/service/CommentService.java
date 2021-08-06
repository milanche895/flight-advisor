package com.test.htec.service;

import java.util.List;

import com.test.htec.DTO.CommentCityDTO;
import com.test.htec.DTO.CommentDTO;

public interface CommentService {

	CommentDTO newComment(CommentDTO commentDTO, String token);

	CommentDTO updateComment(CommentDTO commentDTO, String token);

	String deleteComment(Long id, String token);

	List<CommentCityDTO> getAllCities(Integer numberComments);

}
