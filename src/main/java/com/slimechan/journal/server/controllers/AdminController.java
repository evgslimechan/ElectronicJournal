package com.slimechan.journal.server.controllers;

import java.util.Map;

import com.slimechan.journal.server.dao.mongo.RoleRepo;
import com.slimechan.journal.server.models.users.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.slimechan.journal.server.models.managers.ScheduleManager;
import com.slimechan.journal.server.models.schedule.ScheduleSchema;
import com.slimechan.journal.server.models.schedule.Week;
import com.slimechan.journal.server.models.schedule.WeekDay;

@RequestMapping(value="/admin")
@Controller
public class AdminController {
	
	@Autowired private ScheduleManager schManager;
	@Autowired private RoleRepo roles;

	@RequestMapping(value="/schedule/{groupId}/{weekType}", method=RequestMethod.GET)
	private String configGroupSchedule(Model model, @PathVariable("groupId") int groupId,  @PathVariable("weekType") Week weekType) {
		
		ScheduleSchema sch = schManager.getSchema(groupId, weekType);
		
		if(sch!=null) {
			model.addAttribute("schema", sch.getWeek());
		}
		
		model.addAttribute("weekType", weekType);
		model.addAttribute("group", groupId);
		model.addAttribute("week", WeekDay.values());
		
		return "schChanger";
	}
	@RequestMapping(value="/schedule/{groupId}/{weekType}", method=RequestMethod.POST)
	private String configGroupSchedule(Model model, @PathVariable("groupId") int groupId,  @PathVariable("weekType") Week weekType, @RequestParam Map<String, String> params) {
		
		ScheduleSchema d = new ScheduleSchema(params);
		d.setWeekType(weekType);
		d.setGroup(groupId);
		schManager.addSchema(d);
		
		return "redirect:/";
	}
	@RequestMapping(value = "/roles")
	private String getRoles(Model model){
		model.addAttribute("roles", roles.findAll());
		return "listRole";
	}
	@RequestMapping(value="/roles/add", method = RequestMethod.GET)
	private String getAddRole(){
		return "addRole";
	}
	@RequestMapping(value="/roles/add", method = RequestMethod.POST)
	private String getAddRolePost(@RequestParam("name") String name){

		if(roles.findByName(name)==null) {
			Role r = new Role(name);
			r.setId(roles.count());
			roles.save(r);
		}
		return "redirect:/admin/roles/add";
	}
	@RequestMapping(value="/roles/{role}", method = RequestMethod.GET)
	private String getChangePerms(Model model,@PathVariable String role){
		model.addAttribute("role", roles.findByName(role));
		return "editRole";
	}
	@RequestMapping(value="/roles/{role}", method = RequestMethod.POST)
	private String postChangePerms(Model model,@PathVariable String role, @RequestParam("authority") String param){
		Role r = roles.findByName(role);
		r.getAuthorities().add(new SimpleGrantedAuthority(param));
		r.getAuthorities().forEach(a->System.out.println(a.getAuthority()));
		roles.save(r);
		return "redirect:/admin/roles/"+role;
	}
}
