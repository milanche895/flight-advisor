package com.test.htec.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.htec.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	Comment findOneById(Long id);
	
	Page<Comment> findAll(Pageable page);
}
