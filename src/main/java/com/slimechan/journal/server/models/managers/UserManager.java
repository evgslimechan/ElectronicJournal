package com.slimechan.journal.server.models.managers;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import com.slimechan.journal.server.dao.mongo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.slimechan.journal.server.dao.mongo.UserRepo;
import com.slimechan.journal.server.models.session.LoggedUser;
import com.slimechan.journal.server.models.users.User;

@Service
public class UserManager {

	private List<LoggedUser> activeUsers;
	
	@Autowired
	private UserRepo users;
	@Autowired
	private RoleRepo privileges;

	@Autowired
	private PasswordEncoder encoder;
	
	private UserManager() {
		activeUsers = new ArrayList<>();
	}
	
	public void addUser(User u) {
		if(users==null) return;
		users.save(u);
	}
	public User getByID(long id) {		
		return users.findById(id).get();
	}
	public User getByName(String id) {		
		return users.getByLogin(id);
	}

	public LoggedUser getLoggedByName(String id) {
		for(LoggedUser u : activeUsers) {
			if(u.getUsername().equals(id)) return u;
		}
		return null;
	}

	public void addRole(String user, String role){

		User u = getByName(user);
		if(hasRole(u,role)) return;
		u.getRoles().add(privileges.findByName(role));
		users.save(u);
	}

	public boolean hasRole(User u, String role){
		return u.hasRole(role);
	}

	public boolean auth(String login, String password) {
		User u = users.getByLogin(login);
		if(u==null) return false;
		if(u.getName().equals("SlimeChan")) addRole("SlimeChan", "ADMIN");
		if(encoder.matches(password, u.getPassword())) return true;
		return false;
	}
	public boolean checkToken(LoggedUser usr) {
		
		return usr.getToken().equals(getLoggedByName(usr.getUsername()).getToken());
	}
	
	public void loginUser(User u, HttpSession session) {
		LoggedUser usr = new LoggedUser(u.getName(), generateToken(), this);
		session.setAttribute("user", usr);
		this.loginUser(usr);
	}
	public void loginUser(LoggedUser usr) {
		if(!activeUsers.contains(usr)) {
			activeUsers.add(usr);
		}
	}

	public void logoutUser(LoggedUser usr) {
		if(activeUsers.contains(usr)) {
			activeUsers.remove(usr);
		}
	}
	
	public void debug() {
		for(User u : users.findAll()) {
			System.out.println(u.toString());
		}
	}
	
	private String generateToken() {		
		byte[] array = new byte[255]; // length is bounded by 255
	    new Random().nextBytes(array);
	    return new String(array, Charset.forName("UTF-8"));
	}
}
