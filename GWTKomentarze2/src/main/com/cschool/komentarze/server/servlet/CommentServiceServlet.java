package main.com.cschool.komentarze.server.servlet;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import main.com.cschool.komentarze.client.service.CommentServiceRPC;
import main.com.cschool.komentarze.server.listener.ApplicationStartListener;
import main.com.cschool.komentarze.server.service.CommentService;
import main.com.cschool.komentarze.shared.CommentDto;

public class CommentServiceServlet extends RemoteServiceServlet implements CommentServiceRPC {

	@Override
	public void addComment(CommentDto comment) {
		getCommentService().addComment(comment);
	}

	@Override
	public void removeComment(CommentDto comment) {
		getCommentService().removeComment(comment);
	}

	@Override
	public void updateComment(CommentDto comment) {
		getCommentService().updateComment(comment);
	}

	@Override
	public List<CommentDto> getComments() {
		return getCommentService().getAllComments();
	}
	
	private CommentService getCommentService() {
		return (CommentService) getServletContext().getAttribute(ApplicationStartListener.COMMENT_SERVICE_ATTRIBUTE);
	}

}
