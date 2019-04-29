package com.slimechan.journal.server.models.session;

import com.slimechan.journal.server.models.managers.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private UserManager usrManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String pass = authentication.getPrincipal().toString();

        if(usrManager.auth(name, pass)){
            return new UsernamePasswordAuthenticationToken(name, pass, usrManager.getByName(name).getAuthorities());
        }else{
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}
