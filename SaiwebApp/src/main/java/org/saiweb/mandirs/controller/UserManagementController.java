package org.saiweb.mandirs.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.saibaba.common.exception.UserException;
import org.saibaba.common.service.LookupService;
import org.saibaba.common.service.UserConstants;
import org.saibaba.common.service.UserManagementService;
import org.saibaba.domain.common.InvocationResult;
import org.saibaba.domain.common.KeyValue;
import org.saibaba.domain.lookup.Role;
import org.saibaba.domain.lookup.UserStatus;
import org.saibaba.domain.user.User;
import org.saibaba.fw.domain.Lookup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value="/modifyUser", method=RequestMethod.GET)
	public ModelAndView modifyUser(HttpServletRequest request, 
	        HttpServletResponse response)
	{
		User user= null;
		if(request != null)
		{
			user = (User) request.getSession().getAttribute("USER");
			System.out.println("User in session:"+user);
		}
		if(user == null)
		{
			ModelAndView mav = new ModelAndView("redirect:/login");
			return mav;
		}
		System.out.println("Modify User Form");
		ModelAndView mav = new ModelAndView("modifyUser");
		User dbUser= new User();
		try{
			dbUser = userManagementService.getUserByEmail(user.getEmail());
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
		mav.getModelMap().put("user", dbUser);
		return mav;
	}
	
	@RequestMapping(value="/newUser", method=RequestMethod.POST)
	public String create(@ModelAttribute("user")User user, BindingResult result, SessionStatus status)
	{
		System.out.println("User:"+user);
		if (result.hasErrors()) 
		{				
			return "newUser";
		}
		user.setStatus(new UserStatus(UserStatus.APPROVED));
		try{
			InvocationResult invocationResult = userManagementService.register(user);
			if(!org.springframework.util.CollectionUtils.isEmpty(invocationResult.getErrors()))
			{
				for(KeyValue key: invocationResult.getErrors())
				{
					result.rejectValue(key.getField(), key.getKey());
				}
				return "newUser";
			}
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
		status.setComplete();
		//return "redirect:/modifyUser?email="+user.getEmail();
		return "redirect:/home";
	}
		
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute("user")User user, BindingResult result, HttpServletRequest request, 
	        HttpServletResponse response)
	{
		User dbUser = null;
		try{
			dbUser = userManagementService.loginUser(user.getEmail(), user.getPassword());
			HttpSession session = request.getSession();
			session.setAttribute("USER",dbUser);
			return "redirect:/home";
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
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login()
	{
		System.out.println("Login Form");
		ModelAndView mav = new ModelAndView("login");
		User user = new User();
		mav.getModelMap().put("user", user);
		return mav;
	}
	
	@RequestMapping(value="/modifyUser", method=RequestMethod.POST)
	public String update(@ModelAttribute("user") User user, BindingResult result, SessionStatus status)
	{
		if (result.hasErrors()) {
			return "modifyUser";
		}
		try{
			InvocationResult invocationResult = userManagementService.updateUserProfile(user);
			if(!org.springframework.util.CollectionUtils.isEmpty(invocationResult.getErrors()))
			{
				for(KeyValue key: invocationResult.getErrors())
				{
					result.rejectValue(key.getField(), key.getKey());
				}
				return "modifyUser";
			}
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
		status.setComplete();
		return "redirect:/home";
	}
	
	
	
	public UserManagementService getUserManagementService() {
		return userManagementService;
	}



	public void setUserManagementService(UserManagementService userManagementService) {
		System.out.println("userManagementService Property set");
		this.userManagementService = userManagementService;
	}
}
