package com.slimechan.journal.server.controllers;

import java.util.Date;

import com.slimechan.journal.server.models.managers.NewsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.slimechan.journal.server.models.schedule.Schedule;

@Controller
public class IndexController {

	@Autowired private NewsManager newsManager;

	@RequestMapping(value= {"/index","/"}, method=RequestMethod.GET)
	public String getIndex(Model model) {
		model.addAttribute("date", new Date().toString());
		if(!newsManager.getPages().isEmpty())if(newsManager.getPage(0)!=null)model.addAttribute("page", newsManager.getPage(0));
		return "index";
	}
	
}
