package main.com.cschool.komentarze;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import main.com.cschool.komentarze.server.model.Comment;
import main.com.cschool.komentarze.server.service.CommentJsonCreator;
import main.com.cschool.komentarze.shared.CommentDto;

public class CommentJsonCreatorTest {

	List<CommentDto> commentsList;
	
	@Test
	public void test() {
		System.out.println("test");
	}
	
	@Test
	public void givenJsonPathShouldCreateCommentObjects() throws IOException {
		String path = "C:/Users/dd/Desktop/C_School/jsony/comments.json";
		
		CommentJsonCreator creator = new CommentJsonCreator();
		commentsList = creator.createCommentList(path);
		
		System.out.println(commentsList.size());
		assertThat(commentsList.isEmpty(), is(false));
	}
}
