package main.com.cschool.komentarze.server.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;

import main.com.cschool.komentarze.server.model.Comment;
import main.com.cschool.komentarze.server.session.SessionManager;
import main.com.cschool.komentarze.shared.CommentDto;
import main.com.cschool.komentarze.utility.CommentDtoConverter;

public class CommentDB implements CommentDao {

	private final SessionManager manager;
	
	public CommentDB(SessionManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void addComment(CommentDto comment) {
		Comment comm = CommentDtoConverter.dtoToDao(comment);
		manager.getSession()
			.save(comm);
		
	}

	@Override
	public void removeComment(CommentDto comment) {
		Comment comm = CommentDtoConverter.dtoToDao(comment);
		manager.getSession()
			.delete(comm);
	}

	@Override
	public void updateComment(CommentDto comment) {
		Comment comm = CommentDtoConverter.dtoToDao(comment);	
		manager.getSession()
			.update(comm);
	}

	@Override
	public List<CommentDto> getAllComments() {
		Criteria criteria = manager.getSession().createCriteria(Comment.class);
		List<CommentDto> commList = new ArrayList<>();
		List<Comment> daoList = criteria.list();
		for (Comment each: daoList) {
			commList.add(CommentDtoConverter.daoToDto(each));
		}
		return commList;
	}

}
