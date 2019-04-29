package com.slimechan.journal.server.models.users;

import com.slimechan.journal.server.dao.mongo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

@Document(collection = "roles")
public class Role implements Comparable<Role>, Comparator<Role> {
    @Id
    private long id;

    private String name;
    private Collection<GrantedAuthority> authorities  = new ArrayList<>();

    private Role(){}
    public Role(String name){
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public int compareTo(Role o) {
         return (getId()==o.getId())?1:0;
    }
    @Override
    public int compare(Role u1, Role u2)
    {
        return (u1.getId() == u2.getId())?1:0;
    }

}
