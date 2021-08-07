package com.test.htec.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.test.htec.DTO.CommentCityDTO;
import com.test.htec.DTO.CommentDTO;
import com.test.htec.DTO.TravelDTO;
import com.test.htec.entity.AdvisorUser;
import com.test.htec.entity.Airport;
import com.test.htec.entity.City;
import com.test.htec.entity.Comment;
import com.test.htec.entity.Route;
import com.test.htec.repository.AirportRepository;
import com.test.htec.repository.CityRepository;
import com.test.htec.repository.CommentRepository;
import com.test.htec.repository.RouteRepository;
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
	AirportRepository airportRepository;
	
	@Autowired
	RouteRepository routeRepository;
	
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
					
					comment.setCity(city.getId());
					commentDTO.setCityId(city.getId());
					comment.setUser(user.getId());
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
				
				if (comment.getUser().equals(permisionSystem.checkUserId(token))) {
					comment.setCommentText(commentDTO.getCommentText());
					commentDTO.setCityId(comment.getCity());
					commentDTO.setUserId(comment.getUser());
					
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
				if (comment.getUser().equals(permisionSystem.checkUserId(token))) {
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

		for (City city : cityList) {
			CommentCityDTO commentCityDTO = new CommentCityDTO(city);
			if (Objects.nonNull(numberComments)) {
				Pageable numberOfComments = PageRequest.of(0, numberComments);
				List<Comment> commentList = commentRepository.findAllByCityOrderByIdDesc(city.getId(), numberOfComments);
				commentCityDTO.setCommentList(commentList);
			} else {
				List<Comment> commentList = commentRepository.findAllByCityOrderByIdDesc(city.getId());
				commentCityDTO.setCommentList(commentList);
			}
			
			commentCityList.add(commentCityDTO);
		}
		return commentCityList;
	}
	@Override
	public CommentCityDTO getOneByName(String cityName, Integer numberComments){
		
		if (Objects.nonNull(cityRepository.findOneByCityName(cityName))) {
			City city = cityRepository.findOneByCityName(cityName);
	
				CommentCityDTO commentCityDTO = new CommentCityDTO(city);
				if (Objects.nonNull(numberComments)) {
					Pageable numberOfComments = PageRequest.of(0, numberComments);
					List<Comment> commentList = commentRepository.findAllByCityOrderByIdDesc(city.getId(), numberOfComments);
					commentCityDTO.setCommentList(commentList);
				} else {
					List<Comment> commentList = commentRepository.findAllByCityOrderByIdDesc(city.getId());
					commentCityDTO.setCommentList(commentList);
				}
	
			return commentCityDTO;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found");
		}
	}
	@Override
	public TravelDTO findCheapestFlight(String sourceCity, String destinationCity) {

		
		TravelDTO travelDTO = new TravelDTO();
		
		travelDTO.setSourceCity(sourceCity);
		travelDTO.setDestinationCity(destinationCity);
		
		if (Objects.nonNull(airportRepository.findOneByCity("\""+sourceCity+"\"")) && Objects.nonNull(airportRepository.findOneByCity("\""+destinationCity+"\""))) {
		
			Airport sourceAirport = airportRepository.findOneByCity("\""+sourceCity+"\"");
			Airport destinationAirport = airportRepository.findOneByCity("\""+destinationCity+"\"");
			
			String sourceIata = sourceAirport.getIata().substring(1, sourceAirport.getIata().length()-1);
			
			String destinationIata = destinationAirport.getIata().substring(1, destinationAirport.getIata().length()-1);
			
			List<Route> routeList = routeRepository.findAllBySourceAirportAndDestinationAirportOrderByPriceAsc(sourceIata, destinationIata);
			
			travelDTO.setRouteList(routeList);
			
			return travelDTO;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cities not found");
		}
	}
	
}
