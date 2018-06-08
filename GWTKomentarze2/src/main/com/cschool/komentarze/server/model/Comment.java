package main.com.cschool.komentarze.server.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment_table")
public class Comment {
	
	private long id;
	private String comment;
	private String user;
	private String date;
	private String shape;
	private int[][] coord;
	
	public Comment() {
		
	}
	
	public Comment(long id, String comment, String user, String date, String shape, int[][] coord) {
		super();
		this.id = id;
		this.comment = comment;
		this.user = user;
		this.date = date;
		this.shape = shape;
		this.coord = coord;
	}

	@Id
	@GeneratedValue
	@Column(name = "comment_id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "comment_note", length = 64)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Column(name = "user_comment", length = 64)
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Column(name = "comment_date", length = 64)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	@Column(name = "shape", length = 64)
	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}
	
	@Column(name = "coord")
	public int[][] getCoord() {
		return coord;
	}

	public void setCoord(int[][] coord) {
		this.coord = coord;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", comment=" + comment + ", date=" + date + ", shape=" + shape + ", coord="
				+ Arrays.toString(coord) + "]";
	}
	
	
}
