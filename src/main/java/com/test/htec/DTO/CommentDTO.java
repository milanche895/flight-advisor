package com.test.htec.DTO;

import com.test.htec.entity.Comment;

public class CommentDTO {

	private Long id;
	
	private Long userId;
	
	private Long cityId;
	
	private String commentText;
	
	public CommentDTO(){
		
	}
	public CommentDTO(Comment comment) {
		this.id = comment.getId();
		this.userId = comment.getUser().getId();
		this.cityId = comment.getCity().getId();
		this.commentText = comment.getCommentText();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
}
