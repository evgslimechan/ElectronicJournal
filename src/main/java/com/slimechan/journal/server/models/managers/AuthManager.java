package com.slimechan.journal.server.models.managers;

import com.slimechan.journal.server.models.managers.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthManager implements AuthenticationSuccessHandler {

    @Autowired
    private UserManager usrManager;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                            Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        usrManager.loginUser(usrManager.getByName(authentication.getName()), session);
        String url = getRedirectUrl(request);
        if(url.equals("")) response.sendRedirect("/home"); else response.sendRedirect(url);
    }

    protected String getRedirectUrl(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
            if(savedRequest != null) {
                return savedRequest.getRedirectUrl();
            }
        }

        return "";
    }
}
