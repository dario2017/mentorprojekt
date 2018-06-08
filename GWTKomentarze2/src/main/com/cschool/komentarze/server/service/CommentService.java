package main.com.cschool.komentarze.server.service;

import java.util.List;

import main.com.cschool.komentarze.server.model.Comment;
import main.com.cschool.komentarze.shared.CommentDto;

public interface CommentService {
	void addComment(CommentDto comment);
	
	void removeComment(CommentDto comment);
	
	void updateComment(CommentDto comment);
	
	List<CommentDto> getAllComments();
}
