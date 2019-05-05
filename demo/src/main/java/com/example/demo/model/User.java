package com.example.demo.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	// Attributes
	
	@Id
	@GeneratedValue
	private long iduser;
	
	private String username;
	private String password;
	private String email;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	List<Post> listPosts;
	
	// Contructors


	public User() {
		
	}
	
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	

	// Getters & setters
	
	public long getIduser() {
		return iduser;
	}
	
	public void setIduser(long iduser) {
		this.iduser = iduser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Post> getListPosts() {
		return listPosts;
	}
	public void setListPosts(List<Post> listPosts) {
		this.listPosts = listPosts;
	}
	

}
