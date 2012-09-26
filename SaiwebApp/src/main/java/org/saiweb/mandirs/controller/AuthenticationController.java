package org.saiweb.mandirs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class AuthenticationController {

	@RequestMapping("/login")
	public String login(Model model, @RequestParam(required=false) String message) {
		model.addAttribute("message", message);
		return "login";
	}
	
	@RequestMapping(value = "/denied")
 	public String denied() {
		return "denied";
	}
	
	@RequestMapping(value = "/login/failure")
 	public String loginFailure() {		
		String message = "Login Failure! Invalid User Name or Password.";
		return "redirect:/login.html?message="+message;
	}
	
	@RequestMapping(value = "/logout/success")
 	public String logoutSuccess() {
		String message = "Logout Success!";
		return "redirect:/login.html?message="+message;
	}
}