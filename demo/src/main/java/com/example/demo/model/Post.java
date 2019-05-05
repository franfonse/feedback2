package com.example.demo.model;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="post")
public class Post {

	// Attributes
	
	@Id
	@GeneratedValue
	private long idpost;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userid")
	private User user;
	
	private Date date;

	private String comment;
	
	
	// Contructors
	
	
	public Post() {
	}
	
	
	public Post(User user, Date date, String comment) {
		this.user = user;
		this.date = date;
		this.comment = comment;
	}
	
	
	
	// Geters & setters
	
	
	public long getIdpost() {
		return idpost;
	}

	public void setIdpost(long idpost) {
		this.idpost = idpost;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
