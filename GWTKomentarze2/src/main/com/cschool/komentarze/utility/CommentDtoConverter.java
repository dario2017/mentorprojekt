package main.com.cschool.komentarze.utility;

import main.com.cschool.komentarze.server.model.Comment;
import main.com.cschool.komentarze.shared.CommentDto;

public class CommentDtoConverter {
	
	public CommentDtoConverter() {
		
	}
	
//	ZROBIC TO NA GENERYKACH
	
	public static CommentDto daoToDto(Comment comment) {
		CommentDto comm = new CommentDto();
		comm.setComment(comment.getComment());
		comm.setUser(comment.getUser());
		comm.setCoord(comment.getCoord());
		comm.setDate(comment.getDate());
		comm.setId(comment.getId());
		comm.setShape(comment.getShape());
		
		return comm;
	}
	
	public static Comment dtoToDao(CommentDto comment) {
		Comment comm = new Comment();
		comm.setComment(comment.getComment());
		comm.setUser(comment.getUser());
		comm.setCoord(comment.getCoord());
		comm.setDate(comment.getDate());
		comm.setId(comment.getId());
		comm.setShape(comment.getShape());
		
		return comm;
	}
	

}
