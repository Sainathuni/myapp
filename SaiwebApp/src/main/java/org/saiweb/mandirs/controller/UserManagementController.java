package org.saiweb.mandirs.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.saibaba.common.exception.UserException;
import org.saibaba.common.service.LookupService;
import org.saibaba.common.service.UserConstants;
import org.saibaba.common.service.UserManagementService;
import org.saibaba.domain.common.InvocationResult;
import org.saibaba.domain.common.KeyValue;
import org.saibaba.domain.lookup.Role;
import org.saibaba.domain.lookup.UserStatus;
import org.saibaba.domain.security.SecurityConstants;
import org.saibaba.domain.user.User;
import org.saibaba.fw.domain.Lookup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;




@Controller
public class UserManagementController 
{
	@Autowired
	private UserManagementService userManagementService;
	
	@Autowired
	private LookupService lookupService;

	public LookupService getLookupService() {
		return lookupService;
	}

	@Autowired
    private Validator validator;

	public Validator getValidator() {
		return validator;
	}


	public void setValidator(Validator validator) {
		this.validator = validator;
	}


	public void setLookupService(LookupService lookupService) {
		System.out.println("lookupService Property set");
		this.lookupService = lookupService;
	}

	
	@ModelAttribute("roleList")
	public Map<String,String> populateRoleList() {
 
		Map<String,String> roleList = new LinkedHashMap<String,String>();
		List<Lookup> list = null;
		try {
			list = lookupService.findAll(Role.class);
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		if(list != null)
		{
			for (Lookup lookup:list ) {
				
			roleList.put(lookup.getCode(), lookup.getName());
			}
		}
 
		return roleList;
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
		
		
	@RequestMapping(value="/newUser", method=RequestMethod.GET)
	public ModelAndView newUserForm()
	{
		System.out.println("New User Form");
		ModelAndView mav = new ModelAndView("newUser");
		User user = new User();
		mav.getModelMap().put("user", user);
		return mav;
	}
	
	@Secured({SecurityConstants.ROLE_USER, SecurityConstants.ROLE_SA, SecurityConstants.ROLE_TCO})
	@RequestMapping(value="/modifyUser", method=RequestMethod.GET)
	public ModelAndView modifyUser(HttpServletRequest request, 
	        HttpServletResponse response)
	{
		
		User user = getUser();
				ModelAndView mav = new ModelAndView("home");
		/**if(request != null)
		{
			user = (User) request.getSession().getAttribute("USER");
			System.out.println("User in session:"+user);
		}
		if(user == null)
		{
			ModelAndView mav = new ModelAndView("redirect:/login.html");
			return mav;
		}*/
		if(user != null)
		{			System.out.println("Modify User Form");
			
			User dbUser= new User();
			try{
				dbUser = userManagementService.getUserByEmail(user.getEmail());
				mav.setViewName("modifyUser");
			}catch (Exception ex)
			{
				ex.printStackTrace();
			}
			mav.getModelMap().put("user", dbUser);
		}
		return mav;
	}
	
	@RequestMapping(value="/newUser", method=RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("user")User user, BindingResult result, SessionStatus status)
	{
		System.out.println("User:"+user);
		validator.validate(user, result);
		ModelAndView mav  = new ModelAndView("newUser");
		InvocationResult invocationResult = null;
		if (result.hasErrors()) {
            return mav;
        }
		
		user.setStatus(new UserStatus(UserStatus.APPROVED));
		
		try{
			invocationResult = userManagementService.register(user);
			if(!org.springframework.util.CollectionUtils.isEmpty(invocationResult.getErrors()))
			{
				for(KeyValue key: invocationResult.getErrors())
				{
					result.rejectValue(key.getField(), key.getKey());
				}
				return mav;
			}
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
		status.setComplete();
		invocationResult.setStatusMessage("User Registration is successful");
		mav.getModelMap().put("result", invocationResult);
		mav.setViewName("confirmation");
		return mav;
	}
		
	/**@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute("user")User user, BindingResult result, HttpServletRequest request, 
	        HttpServletResponse response)
	{
		User dbUser = null;
		try{
			dbUser = userManagementService.loginUser(user.getEmail(), user.getPassword());
			HttpSession session = request.getSession();
			session.setAttribute("USER",dbUser);
			return "redirect:/home.html";
		}catch (UserException se)
		{
			se.printStackTrace();
			if(se.getMessageCode().equals(UserConstants.INVALID_PASSWORD)){
				result.rejectValue("password", se.getMessageCode());
			}else if(se.getMessageCode().equals(UserConstants.INVALID_USER_ID)){
				result.rejectValue("email", se.getMessageCode());
			}
			return "login";
		}
		catch (Throwable th)
		{
			th.printStackTrace();
		}
		return "redirect:/home";
	}*/
	
	@RequestMapping(value="/forgotPassword", method=RequestMethod.POST)
	public ModelAndView forgotPassword(@ModelAttribute("user")User user, BindingResult result, HttpServletRequest request, 
	        HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("confirmation");
		try{
			userManagementService.forgotPassword(user.getEmail());
			InvocationResult invocationResult = new InvocationResult();
			invocationResult.setStatusMessage("Password sent successfully to the email on profile.");
			mav.getModelMap().put("result", invocationResult);
			return mav;
		}catch (UserException se)
		{
			se.printStackTrace();
			if(se.getMessageCode().equals(UserConstants.INVALID_USER_ID)){
				result.rejectValue("email", se.getMessageCode());
			}
			mav.setViewName("forgotPassword");
			return mav;
		}
		catch (Throwable th)
		{
			th.printStackTrace();
		}
		
		return mav;
	}
	
/**	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login()
	{
		System.out.println("Login Form");
		ModelAndView mav = new ModelAndView("login");
		User user = new User();
		mav.getModelMap().put("user", user);
		return mav;
	}*/
	
	@RequestMapping(value="/forgotPassword", method=RequestMethod.GET)
	public ModelAndView forgotPassword()
	{
		System.out.println("Forgot Password Form");
		ModelAndView mav = new ModelAndView("forgotPassword");
		User user = new User();
		mav.getModelMap().put("user", user);
		return mav;
	}
	
	@Secured({SecurityConstants.ROLE_USER, SecurityConstants.ROLE_SA, SecurityConstants.ROLE_TCO})
	@RequestMapping(value="/modifyUser", method=RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("user") User user, BindingResult result, SessionStatus status)
	{
		validator.validate(user, result);
		ModelAndView mav = new ModelAndView("modifyUser");
		InvocationResult invocationResult = null;
		if (result.hasErrors()) {
 
            return mav;
        }
		try{
			invocationResult = userManagementService.updateUserProfile(user);
			if(!org.springframework.util.CollectionUtils.isEmpty(invocationResult.getErrors()))
			{
				for(KeyValue key: invocationResult.getErrors())
				{
					result.rejectValue(key.getField(), key.getKey());
				}
				return mav;
			}
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
		status.setComplete();
		invocationResult.setStatusMessage("Updated User Profile successfully");
		mav.getModelMap().put("result", invocationResult);
		mav.setViewName("confirmation");
		return mav;
	}
	
	
	
	public UserManagementService getUserManagementService() {
		return userManagementService;
	}



	public void setUserManagementService(UserManagementService userManagementService) {
		System.out.println("userManagementService Property set");
		this.userManagementService = userManagementService;
	}
	
	public User getUser(){
		
		User user = null;
		if (SecurityContextHolder.getContext().getAuthentication() != null && 
				SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null &&
				SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
			
			user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		
		return user;	
	}
	
}
