package com.slimechan.journal.server.models.users;

public class UserFactory {

	private User user;
	
	private UserFactory() {
		user = new User();
	}
	
	public UserFactory setName(String name) {
		user.setName(name);
		return this;
	}
	public UserFactory setPassword(String password) {
		user.setPassword(password);
		return this;
	}
	
	
	public static UserFactory start() {
		return new UserFactory();
	}
	public User build() {return user;}
	
}
