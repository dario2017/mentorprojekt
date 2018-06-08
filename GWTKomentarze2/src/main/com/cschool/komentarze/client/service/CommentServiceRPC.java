package main.com.cschool.komentarze.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import main.com.cschool.komentarze.shared.CommentDto;

@RemoteServiceRelativePath("comment")
public interface CommentServiceRPC extends RemoteService {
	
	void addComment(CommentDto comment);
	
	void removeComment(CommentDto comment);
	
	void updateComment(CommentDto comment);
	
	List<CommentDto> getComments();
}
