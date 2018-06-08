package main.com.cschool.komentarze.server.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import main.com.cschool.komentarze.server.dao.CommentDB;
import main.com.cschool.komentarze.server.model.Comment;
import main.com.cschool.komentarze.server.service.CommentService;
import main.com.cschool.komentarze.server.service.CommentServiceImpl;
import main.com.cschool.komentarze.server.session.SessionManager;
import main.com.cschool.komentarze.shared.CommentDto;



public class ApplicationStartListener implements ServletContextListener {

	public static final String COMMENT_SERVICE_ATTRIBUTE =  "commentService";
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("Wchodzimy do context listenera");
		SessionManager manager = new SessionManager();
		
		CommentService commentService = new CommentServiceImpl(manager, new CommentDB(manager));
		event.getServletContext().setAttribute(COMMENT_SERVICE_ATTRIBUTE, commentService);
		
//		Comment comm = new Comment();
//		comm.setComment("Pierwszy komentarz");
//		System.out.println(comm.toString());
		
//		CommentDto commDto = new CommentDto();
//		commDto.setComment("dto pierwszy");
//		commDto.setUser("Darek");
//		
//		CommentDto commDto2 = new CommentDto();
//		commDto2.setComment("wazny komentarz");
//		commDto2.setUser("dd");
//		
//		commentService.addComment(commDto);
//		commentService.addComment(commDto2);
		
		
	}

}
