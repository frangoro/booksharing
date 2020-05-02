package org.frangoro.booksharing.controller;

import org.frangoro.booksharing.configuration.AuthenticationFacade;
import org.frangoro.booksharing.domain.User;
import org.frangoro.booksharing.dto.UserDto;
import org.frangoro.booksharing.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @GetMapping(value = {"/", ""})
    public String edit(final UserDto userDto) {
        String username = authenticationFacade.getAuthentication().getName();
        User user = service.get(username);
        BeanUtils.copyProperties(user, userDto);
        return "editUser";
    }

    @PostMapping("/save")
    public String save(@Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "newUser";
        }
        User user = service.registerNewUserAccount(userDto);
        return "login";
    }

    @PostMapping("/update/{username}")
    public String update(@PathVariable("username") String username, @Valid UserDto userDto,
						 BindingResult bindingResult) {
        //TODO Admin can update other users, so he can send the username in the dto
		if (bindingResult.hasErrors()) {
			return "editUser";
		}
        userDto.setUsername(username);
        User user = service.updateUser(userDto);
        BeanUtils.copyProperties(user, userDto);
        return "search";
    }

    @GetMapping(value = "/delete/{username}")
    public String delete(@PathVariable("username") String username, Model model) {
        service.deleteUserByUsername(username);
        if (authenticationFacade.getAuthentication().getName().equals(username)) {
            return "login";
        }
        return "editUser";
    }

}
