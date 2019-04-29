package com.slimechan.journal.server.models.session;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.slimechan.journal.server.models.managers.UserManager;

@Component
public class LoggedUser implements HttpSessionBindingListener{
	
	private String username;
	private String token;
	
	private UserManager manager;
	
	public LoggedUser() {}
	
	public LoggedUser(String name, String token, UserManager mng) {
		this.username = name;
		this.token = token;
		this.manager = mng;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getUserPage() {
		return "/id"+manager.getByName(username).getId();
	}
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		LoggedUser usr = (LoggedUser) event.getValue();
		if(usr==null) System.out.println("Null user");
		if(manager==null) System.out.println("Null manamer");
		manager.loginUser(usr);
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		LoggedUser usr = (LoggedUser) event.getValue();
		manager.logoutUser(usr);
	}
}
