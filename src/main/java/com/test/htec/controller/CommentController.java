package com.test.htec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.htec.DTO.CommentCityDTO;
import com.test.htec.DTO.CommentDTO;
import com.test.htec.DTO.TravelDTO;
import com.test.htec.service.CommentService;

@RestController
@RequestMapping("advisor/comment/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("new")
	public ResponseEntity<CommentDTO> newComment(@RequestBody CommentDTO commentDTO, @RequestHeader("Authorization") String token){
		return new ResponseEntity<CommentDTO>(commentService.newComment(commentDTO, token), HttpStatus.OK);
	}
	@PutMapping("update")
	public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDTO, @RequestHeader("Authorization") String token){
		return new ResponseEntity<CommentDTO>(commentService.updateComment(commentDTO, token),HttpStatus.OK);
	}
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable(name = "id") Long id, @RequestHeader("Authorization") String token){
		return new ResponseEntity<String>(commentService.deleteComment(id, token),HttpStatus.OK);
	}
	@GetMapping("all")
	public ResponseEntity<List<CommentCityDTO>> getAllCities(@RequestParam(required = false) Integer numberComments){
		return new ResponseEntity<List<CommentCityDTO>>(commentService.getAllCities(numberComments), HttpStatus.OK);
	}
	@GetMapping("city")
	public ResponseEntity<CommentCityDTO> getAllCities(@RequestParam(required = true) String cityName, @RequestParam(required = false) Integer numberComments){
		return new ResponseEntity<CommentCityDTO>(commentService.getOneByName(cityName, numberComments), HttpStatus.OK);
	}
	@GetMapping("travel")
	public ResponseEntity<TravelDTO> findCheapestFlight(@RequestParam(required = true) String sourceCity, @RequestParam(required = false) String destinationCity){
		return new ResponseEntity<TravelDTO>(commentService.findCheapestFlight(sourceCity, destinationCity), HttpStatus.OK);
	}

}
