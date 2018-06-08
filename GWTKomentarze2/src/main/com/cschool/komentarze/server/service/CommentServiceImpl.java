package main.com.cschool.komentarze.server.service;

import java.util.List;

import main.com.cschool.komentarze.server.dao.CommentDao;
import main.com.cschool.komentarze.server.model.Comment;
import main.com.cschool.komentarze.server.session.SessionManager;
import main.com.cschool.komentarze.shared.CommentDto;

public class CommentServiceImpl extends AbstractService implements CommentService {

	private final CommentDao commentDao;
	
	public CommentServiceImpl(SessionManager manager, CommentDao commentDao) {
		super(manager);
		
		if(commentDao == null) {
			throw new IllegalArgumentException("CommentDao cannot be null");
		}
		
		this.commentDao = commentDao;
	}	
	
	@Override
	public void addComment(CommentDto comment) {
		doInTransaction(() -> {
			commentDao.addComment(comment);
			return null;
		});		
	}

	@Override
	public void removeComment(CommentDto comment) {
		doInTransaction(() ->{
			this.commentDao.removeComment(comment);
			return null;
		});
		
	}

	@Override
	public void updateComment(CommentDto comment) {
		doInTransaction(() ->{
			this.commentDao.updateComment(comment);
			return null;
		});	
	}

	@Override
	public List<CommentDto> getAllComments() {
		return doInTransaction(() -> this.commentDao.getAllComments());
	}

}
