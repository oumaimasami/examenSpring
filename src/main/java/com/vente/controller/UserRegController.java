package com.vente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vente.dto.UserRegDto;
import com.vente.service.UserService;

@Controller
@RequestMapping("/register")
public class UserRegController {
	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public UserRegDto userRegDto() {
		return new UserRegDto();
	}
	
	@GetMapping
	public String ShowRegForm(Model model) {
		return "register";
	}
	@PostMapping
	public String registerUserAcc(@ModelAttribute("user") UserRegDto userRegDto) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(userRegDto.getPassword());
		userRegDto.setPassword(encodedPassword);
		userService.save(userRegDto);
		return "redirect:/register?success";
		
	}
	

}
