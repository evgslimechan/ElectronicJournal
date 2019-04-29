package com.slimechan.journal.server.models.session;

import com.slimechan.journal.server.models.managers.UserManager;
import com.slimechan.journal.server.models.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserManager usrManager;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = usrManager.getByName(userName);
        if(user==null) throw new UsernameNotFoundException("Username "+userName+" not found");
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), user.getAuthorities());
    }
}
