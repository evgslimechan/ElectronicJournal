package com.slimechan.journal.server.models.users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.slimechan.journal.server.dao.mongo.RoleRepo;
import com.slimechan.journal.server.models.managers.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Document(collection = "users")
public class User{

	// !! id equals student ticket !!
	// but in groups id equals student number

	@Id
	private long id;
	
	@Field(value="name")
	private String name;
	@Field(value="fio")
	private String fio;
	@Field(value="password")
	private String password;
	
	@Field(value="group")
	private Group group;
	
	@Field(value="roles")
	private List<Role> roles = new ArrayList<>();
	
	protected User() {	}
	
	public User(long id, String fio, String name, String pass, Role defaultRole) {
		this.id = id;
		this.fio = fio;
		this.name = name;
		this.password = pass;
		roles.add(defaultRole);
	}
	
	public long getId() {return id;}
	public void setId(long id) {this.id = id;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public List<String> getStringRoles()
	{
		List<String> rs = new ArrayList<>();
		roles.forEach(r->rs.add(r.getName()));
		return rs;
	}
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> permissions = new ArrayList<GrantedAuthority>();
		for (Role role: roles) {
			permissions.addAll(role.getAuthorities());
		}
		return permissions;
	}

	public void setRoles(List<Role> p) {
		this.roles = p;
	}

	public boolean hasRole(String role){
		return getStringRoles().contains(role);
	}
	
	public String toString() {
		return id+":"+name+":"+password;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

}
