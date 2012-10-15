package org.saiweb.mandirs.controller;

import java.util.ArrayList;
import java.util.List;

import org.saibaba.common.service.MiscService;
import org.saibaba.domain.common.InvocationResult;
import org.saibaba.domain.common.UploadItem;
import org.saibaba.domain.misc.FileInfo;
import org.saibaba.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {
	
	@Autowired
	private MiscService miscService;

	public void setMiscService(MiscService miscService) {
		this.miscService = miscService;
	}

	@RequestMapping(value = "/showFiles", method = RequestMethod.GET)
	public ModelAndView viewFiles(@RequestParam("id") Long mandirId, @RequestParam("code") String mandirCode) {
		ModelAndView mav = new ModelAndView("showFiles");
		List<FileInfo> files = new ArrayList<FileInfo>();
		try{
			files = miscService.getFileInfoByMandirId(mandirId);
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
		mav.addObject("MANDIR_CODE", mandirCode);
		mav.addObject("MANDIR_ID", mandirId);
		mav.addObject("FILE_LIST", files);
		return mav;
	}
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
	public ModelAndView uploadForm(@RequestParam("id") Long mandirId, @RequestParam("code") String mandirCode) {
		ModelAndView mav = new ModelAndView("uploadFile");
		UploadItem item = new UploadItem();
		item.setMandirId(new Long(mandirId));
		item.setMandirCode(mandirCode);
		mav.getModelMap().put("uploadItem", item);
		return mav;
	}

	/**@RequestMapping(value = "/modifyFile", method = RequestMethod.GET)
	public ModelAndView modifyFile(@RequestParam("id") Long id, @RequestParam("code") String code) {
		ModelAndView mav = new ModelAndView("uploadFile");
		UploadItem item = new UploadItem();
		try{
			if(id != null)
			{
				FileInfo info = miscService.getFileInfoById(id);	
				item = getUploadItem(info, code);
			}
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
		mav.getModelMap().put("uploadItem", item);
		return mav;
	}*/
	
	@RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
	public ModelAndView deleteFile(@RequestParam("id") Long id, @RequestParam("code") String code,
			@RequestParam("mandirId") Long mandirId) {
		ModelAndView mav = new ModelAndView("redirect:showFiles.html");
		mav.addObject("id", mandirId);
		mav.addObject("code", code);
		UploadItem item = new UploadItem();
		InvocationResult result = null;
		try{
			if(id != null)
			{
				result = miscService.deleteFileItem(id, code);
			}
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return mav;
	}
	
	
	private UploadItem getUploadItem(FileInfo info, String code)
	{
		UploadItem item = new UploadItem();
		if(info != null)
		{
			item.setMandirId(info.getMandirId());
			item.setMandirCode(code);
			item.setName(info.getFileName());
			item.setDescription(info.getDescription());
		}
		return item;
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
		mav.addObject("id", uploadItem.getMandirId());
		mav.addObject("code", uploadItem.getMandirCode());
		mav.setViewName("redirect:showFiles.html");
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
