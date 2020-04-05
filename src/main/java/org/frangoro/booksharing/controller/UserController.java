package org.frangoro.booksharing.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.frangoro.booksharing.configuration.AuthenticationFacade;
import org.frangoro.booksharing.domain.User;
import org.frangoro.booksharing.dto.UserDto;
import org.frangoro.booksharing.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@GetMapping(value= {"/", ""})
	public String edit(Model model) {
		String username = authenticationFacade.getAuthentication().getName();
	    User user = service.get(username);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user,userDto);
	    model.addAttribute("user", userDto);
	     
	    return "editUser";
	}
	
	@PostMapping("/save")
	public String save(Model model, UserDto userDto) {
		User user = service.registerNewUserAccount(userDto);
		model.addAttribute("user", user);
		return "editUser";
	}

	@PostMapping("/update")
	public String update(Model model, UserDto userDto) {
		User user = service.updateUser(userDto);
		model.addAttribute("user", user);
		return "editUser";
	}
	
	@PostMapping(value="/delete", params="action=cancel")
	public String delete(Model model, UserDto userDto) {
		service.deleteUser(userDto);
		return "login";
	}

}
