package main.com.cschool.komentarze.server.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.com.cschool.komentarze.server.dao.CommentDB;
import main.com.cschool.komentarze.server.model.Comment;
import main.com.cschool.komentarze.server.session.SessionManager;
import main.com.cschool.komentarze.shared.CommentDto;

public class CommentJsonCreator {
		
	private List<CommentDto> commentsList;
	
	public List<CommentDto> createCommentList(String path) throws IOException {
		Path jsonPath = Paths.get(path);
		
		byte[] jsonData = Files.readAllBytes(jsonPath);
		ObjectMapper mapper = new ObjectMapper();
		
		TypeReference<List<CommentDto>> mapType = new TypeReference<List<CommentDto>>() {};
		
		List<CommentDto> commList = mapper.readValue(jsonData, mapType);
		
		System.out.println("parsujemy w creatorze");
		
		sendCommentsToDB(commList);
		
		System.out.println("dodano do bazy danych obiekty");
		return commList;
	}
	
	public void sendCommentsToDB(List<CommentDto> commentsList) {
		SessionManager manager = new SessionManager();
		CommentService commentService = new CommentServiceImpl(manager, new CommentDB(manager));
				
		if (!commentsList.isEmpty()) {
			for (CommentDto eachComm: commentsList) {
				commentService.addComment(eachComm);
			}
		}
	}
}
