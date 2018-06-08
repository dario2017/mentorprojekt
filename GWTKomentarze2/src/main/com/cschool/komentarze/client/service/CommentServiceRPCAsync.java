package main.com.cschool.komentarze.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import main.com.cschool.komentarze.shared.CommentDto;

public interface CommentServiceRPCAsync {

	void getComments(AsyncCallback<List<CommentDto>> callback);

	void addComment(CommentDto comment, AsyncCallback<Void> callback);

	void removeComment(CommentDto comment, AsyncCallback<Void> callback);

	void updateComment(CommentDto comment, AsyncCallback<Void> callback);

}
