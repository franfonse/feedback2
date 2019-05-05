package com.example.demo.controller;

import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.example.demo.model.DaoPost;
import com.example.demo.model.DaoUser;
import com.example.demo.model.Post;
import com.example.demo.model.User;

@SessionAttributes
@Controller
public class MainController {
	
	//Daos
	
	@Autowired
	DaoUser daoUser;
	
	@Autowired
	DaoPost daoPost;
	
	
	java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
	
	
	// Mappings
	
	
	@RequestMapping (value="/", method=RequestMethod.GET)
	public String home() {
		
		return "homepage";
		
	}
	
	@RequestMapping (value="/login", method=RequestMethod.GET)
	public String loginUser(Model model, @RequestParam String username, @RequestParam String password) {
		
		User loginUser = new User(username, password, null);
		
		model.addAttribute("loginUser", loginUser);
		
			
		if (daoUser.findByUsername(loginUser.getUsername()) == null || 
				!loginUser.getUsername().equals(daoUser.findByUsername(loginUser.getUsername()).getUsername()) || 
				!loginUser.getPassword().equals(daoUser.findByUsername(loginUser.getUsername()).getPassword())) { 
			
				
			// Username or password invalid
			// Activate text in HTML that says "Incorrect username and/or password"
			
			return "homepage";
				
		}
		
		else {
			
			// Welcome!
			
			loginUser = daoUser.findByUsername(loginUser.getUsername());
			
			model.addAttribute("loggedUser", loginUser);
			model.addAttribute("posts", daoPost.findAll());
			
			return "posts";
			
		}
		
	}
	
	
	@RequestMapping (value="/goRegister", method = RequestMethod.GET)
	public String registerUser() {
		
		return "newuser";
	}
	
	@RequestMapping (value="/goHome", method = RequestMethod.GET)
	public String goBackHome() {
		
		return "homepage";
	}
	
	
	@RequestMapping (value="/createUser", method=RequestMethod.POST)
	public String newUser(Model model, @RequestParam String username, @RequestParam String password, 
			@RequestParam String email) {
		
		User user = new User(username, password, email);
		
		model.addAttribute("user", user);
		
		
		if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null) {
			
			// Paramenters are required.
			
			return "newuser";
			
		}
		
		else if (daoUser.findByUsername(user.getUsername()) != null) {
			
			// Username already taken.
			
			return "newuser";
		}
		
		else {
			
			// User saved in database.
			
			daoUser.save(user);
			
			return "homepage";
			
		}
		
	}
	
	@RequestMapping (value="/createPost", method=RequestMethod.GET)
	public String goCreatePost(Model model, @RequestParam String username) {
		
		User loggedUser = daoUser.findByUsername(username);
		
		model.addAttribute("loggedUser", loggedUser);
		
		return "newpost";
		
	}
	
	@RequestMapping (value="/post", method=RequestMethod.POST)
	public String savePost(Model model, @ModelAttribute User user, @RequestParam String username, 
			@RequestParam String comment) {
		
		User loggedUser = daoUser.findByUsername(username);
		
		
		model.addAttribute("loggedUser", loggedUser);
		
		if (comment == null) {
			
			//Must containt text to post.
			
			return "newpost";
		}
		else {
			
			// Save post
			
			Post post = new Post(loggedUser, sqlDate, comment);
			daoPost.save(post);
			
			model.addAttribute("posts", daoPost.findAll());
			
			return "posts";
			
		}
		
	}
	
	@RequestMapping (value="/logOff", method=RequestMethod.GET)
	public String loggingOff() {
		
		
		// Sign off
		
		return "homepage";
		
	}

}
