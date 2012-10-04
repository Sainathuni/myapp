package org.saiweb.mandirs.controller;

import org.saibaba.common.service.MiscService;
import org.saibaba.domain.common.InvocationResult;
import org.saibaba.domain.common.UploadItem;
import org.saibaba.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {
	
	@Autowired
	private MiscService miscService;

	public void setMiscService(MiscService miscService) {
		this.miscService = miscService;
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
	public ModelAndView uploadForm() {
		ModelAndView mav = new ModelAndView("uploadFile");
		UploadItem item = new UploadItem();
		item.setMandirId(new Long(1));
		item.setMandirCode("HYD001");
		mav.getModelMap().put("uploadItem", item);
		return mav;
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ModelAndView create(UploadItem uploadItem, BindingResult result) {
		ModelAndView mav = new ModelAndView("uploadFile");
		InvocationResult invocationResult = new InvocationResult();
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.err.println("Error: " + error.getCode() + " - "
						+ error.getDefaultMessage());
			}
			return mav;
		}

		try {
			invocationResult = miscService.addFileItem(uploadItem, getUser());
		}catch(Throwable th)
		{
			th.printStackTrace();
			return mav;
		}
		System.out.println("Upload Item " + uploadItem);		
		invocationResult.setStatusMessage("File upload is successful");
		mav.getModelMap().put("result", invocationResult);
		mav.setViewName("confirmation");
		return mav;
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
