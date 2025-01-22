package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cts.entities.Customer;
import com.cts.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class MyController {
	
    @Autowired
	private UserService userService;
    
	@GetMapping("/regpage")
	public String openRegPage(Model model) {
		model.addAttribute("user",new Customer());
		return "register" ;
	}
	
	@PostMapping("/regform")
	public String submitRegForm(@ModelAttribute("user") Customer user,Model model){
		boolean status = userService.registerUser(user);
		if(status) {
			model.addAttribute("successMsg","User Registered Successfully!!");
		}
		else {
			model.addAttribute("errorMsg","User not registered due to some error");
		}
		return "register";
	}
	
	@GetMapping("/loginpage")
	public String openLoginPage(Model model) {
		model.addAttribute("user", new Customer());
		return "login";
	}
	
	@PostMapping("/loginform")
	public String submitLoginForm(@ModelAttribute("user") Customer user,Model model) {
		Customer validUser =userService.loginUser(user.getEmail(), user.getPassword());
		if(validUser!=null) {
			model.addAttribute("modelName",validUser.getName());
			model.addAttribute("modelEmail", validUser.getEmail());
			model.addAttribute("modelPhone", validUser.getPhone());
			model.addAttribute("modelAddress", validUser.getAddress());
			return "profile";
		}
		else {
			model.addAttribute("errorMsg","Error email and password didnot match");
			return "login";
		}
	
	}
	
	@GetMapping("/logout")
	public String logoutUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session!=null) {
			session.invalidate();
		}
		return "login";
	}
		
}
