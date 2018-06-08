package main.com.cschool.komentarze.server.dao;

import java.util.List;

import main.com.cschool.komentarze.server.model.Comment;
import main.com.cschool.komentarze.shared.CommentDto;

public interface CommentDao {
	void addComment(CommentDto comment);
	
	void removeComment(CommentDto comment);
	
	void updateComment(CommentDto comment);
	
	List<CommentDto> getAllComments();
	
	
}
