package main.com.cschool.komentarze.shared;

import java.util.Arrays;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CommentDto implements IsSerializable {
	
	private long id;
	private String comment;
	private String user;
	private String date;
	private String shape;
	private int[][] coord;
	
	public CommentDto() {
		
	}
	
	public CommentDto(long id, String comment, String user, String date, String shape, int[][] coord) {
		super();
		this.id = id;
		this.comment = comment;
		this.user = user;
		this.date = date;
		this.shape = shape;
		this.coord = coord;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

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
		CommentDto other = (CommentDto) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", comment=" + comment + ", user=" + user + ", date=" + date + ", shape="
				+ shape + ", coord=" + Arrays.toString(coord) + "]";
	}


	
	
}
