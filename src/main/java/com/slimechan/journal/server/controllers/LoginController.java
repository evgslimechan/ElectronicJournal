package com.slimechan.journal.server.controllers;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.slimechan.journal.server.dao.mongo.RoleRepo;
import com.slimechan.journal.server.models.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.slimechan.journal.server.models.managers.UserManager;
import com.slimechan.journal.server.models.users.User;

@Controller
public class LoginController {

	@Autowired private UserManager userManager;
	@Autowired private RoleRepo roles;

	@Autowired
	private PasswordEncoder encoder;

	@RequestMapping(value="/register", method=RequestMethod.GET)
	private String onReg(Model m) {
		return "reg";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	private String tryReg(ModelMap model, HttpServletRequest request, @RequestParam(value="name") String login,
												@RequestParam(value="fio") String fio,
												@RequestParam(value="ticket") String studentTicket,
												@RequestParam(value="password") String password,
												@RequestParam(value="repeatPassword") String passRepeat) {
		
		if(!password.equals(passRepeat)) return "redirect:/register";
		
		userManager.addUser(new User(Long.parseLong(studentTicket), fio, login, encoder.encode(password), roles.findByName("USER")));
		return "redirect:/home";
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	private String login(Model model) {
		return "login";
	}
	
	
	@RequestMapping(value="/home")
	private String enterLk(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		LoggedUser usr = (LoggedUser)session.getAttribute("user");
		if(usr!=null) {
			if(userManager.checkToken(usr)) {
				User u = userManager.getByName(usr.getUsername());
				if (u == null) userManager.debug();
				model.addAttribute("user", u);
				return "home";
			}
		}
		return "redirect:/";
	}
}
