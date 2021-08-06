package com.test.htec.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.test.htec.DTO.CommentCityDTO;
import com.test.htec.DTO.CommentDTO;
import com.test.htec.entity.AdvisorUser;
import com.test.htec.entity.City;
import com.test.htec.entity.Comment;
import com.test.htec.repository.CityRepository;
import com.test.htec.repository.CommentRepository;
import com.test.htec.repository.UserRepository;
import com.test.htec.service.CommentService;

@Service
public class CommentServiceImplement implements CommentService {
	
	@Autowired
	PermisionSystem permisionSystem;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	EntityManager em;

	@Override
	public CommentDTO newComment(CommentDTO commentDTO, String token) {
		if (permisionSystem.checkRegularUserAccess(token)) {
			
			if (Objects.nonNull(commentDTO.getCityId()) && Objects.nonNull(commentDTO.getCommentText())) {
				
				if (Objects.nonNull(cityRepository.findOneById(commentDTO.getCityId()))) {
					
					Comment comment = new Comment();
					
					City city = cityRepository.findOneById(commentDTO.getCityId());
					AdvisorUser user = userRepository.findOneById(permisionSystem.checkUserId(token));
					
					comment.setCity(city);
					commentDTO.setCityId(city.getId());
					comment.setUser(user);
					commentDTO.setUserId(user.getId());
					comment.setCommentText(commentDTO.getCommentText());
					
					commentRepository.save(comment);
					
					commentDTO.setId(comment.getId());
					
					return commentDTO;
				} else {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found");
				}
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please insert all and correct data");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
		}
	}
	@Override
	public CommentDTO updateComment(CommentDTO commentDTO, String token) {
		if (permisionSystem.checkRegularUserAccess(token)) {
			if (Objects.nonNull(commentRepository.findOneById(commentDTO.getId()))){
				Comment comment = commentRepository.findOneById(commentDTO.getId());
				
				if (comment.getUser().getId().equals(permisionSystem.checkUserId(token))) {
					comment.setCommentText(commentDTO.getCommentText());
					commentDTO.setCityId(comment.getCity().getId());
					commentDTO.setUserId(comment.getUser().getId());
					
					commentRepository.save(comment);
					
					return commentDTO;
				} else {
					throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can update only your commnets! ");
				}
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
		}
	}
	@Override
	public String deleteComment(Long id, String token) {
		if (permisionSystem.checkRegularUserAccess(token)) {
			if (Objects.nonNull(commentRepository.findOneById(id))) {
				Comment comment = commentRepository.findOneById(id);
				if (comment.getUser().getId().equals(permisionSystem.checkUserId(token))) {
					commentRepository.delete(comment);
					
					return "Comment is delete";
				} else {
					throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can delete only your commnets! ");
				}
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
		}
	}
	@Override
	public List<CommentCityDTO> getAllCities(Integer numberComments){
		List<City> cityList = cityRepository.findAll();
		List<CommentCityDTO> commentCityList = new ArrayList<CommentCityDTO>();
		Pageable numberOfComments = PageRequest.of(0, numberComments);

		for (City city : cityList) {
			CommentCityDTO commentCityDTO = new CommentCityDTO();
			Page<Comment> commentList = commentRepository.findAll(numberOfComments);
			commentCityDTO.setCommentList(commentList);
			
			commentCityList.add(commentCityDTO);
		}
		return commentCityList;
	}
}
