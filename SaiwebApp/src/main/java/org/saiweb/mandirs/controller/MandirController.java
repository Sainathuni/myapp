package org.saiweb.mandirs.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.saiweb.mandirs.MandirConstants;
import org.saiweb.mandirs.dao.api.ContactDAO;
import org.saiweb.mandirs.dao.api.CountryDAO;
import org.saiweb.mandirs.dao.api.RegionDAO;
import org.saiweb.mandirs.dao.api.StatusDAO;
import org.saiweb.mandirs.dao.api.MandirDAO;
import org.saiweb.mandirs.model.Contact;
import org.saiweb.mandirs.model.Country;
import org.saiweb.mandirs.model.Mandir;
import org.saiweb.mandirs.model.Region;
import org.saiweb.mandirs.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MandirController {
	@Autowired
	private MandirDAO mandirDAO;

	@Autowired
	private CountryDAO countriesDAO;

	@Autowired
	private RegionDAO regionsDAO;
	
	@Autowired
	private StatusDAO statusDAO;
	
	@Autowired
	private ContactDAO contactDAO;
	// @Autowired
	// private MandirDataFlowDAO mandirsDataFlowDAO;
	//

	@RequestMapping("/home")
	public String home() {
		return "home";
	}

	@ModelAttribute("countryList")
	public Map<String, String> populateCountryList() {

		Map<String, String> countriesList = new LinkedHashMap<String, String>();
		List<Country> list = countriesDAO.getCountries();

		for (Iterator<Country> i = list.iterator(); i.hasNext();) {
			Country country = (Country) i.next();
			countriesList.put(Integer.toString(country.getCountry_id()),
					country.getName());
		}

		return countriesList;
	}
	
	@ModelAttribute("regionList")
	public Map<String, String> populateRegionList() {

		Map<String, String> regionList = new LinkedHashMap<String, String>();
		List<Region> list = regionsDAO.getRegions();

		for (Iterator<Region> i = list.iterator(); i.hasNext();) {
			Region region = (Region) i.next();
			regionList.put(Integer.toString(region.getRegionId()),
					region.getName());
		}

		return regionList;
	}
	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping(value = "/searchMandirs", method = RequestMethod.POST)
	public ModelAndView searchMandirs(
			@RequestParam(required = false, defaultValue = "") String location) {
		ModelAndView mav = new ModelAndView("showMandirs");
		List<Mandir> mandirs = mandirDAO.getMandirsByLocation(location.trim());
		mav.addObject("MANDIR_LIST", mandirs);
		return mav;
	}
	
	@RequestMapping(value = "/findMandirs", method = RequestMethod.POST)
	public ModelAndView findMandirs(
			@RequestParam("regionId") int regionId, @RequestParam("statusId") int statusId) {
		ModelAndView mav = new ModelAndView("showMandirs");
		List<Mandir> mandirs = mandirDAO.getMandirsByLocation(regionId, statusId);
		mav.addObject("MANDIR_LIST", mandirs);
		return mav;
	}
	

	@RequestMapping(value = "/viewAllMandirs", method = RequestMethod.GET)
	public ModelAndView viewAllMandirs() {
		ModelAndView mav = new ModelAndView("showMandirs");
		List<Mandir> mandirList = mandirDAO.getAllMandirs();
		mav.addObject("MANDIR_LIST", mandirList);
		return mav;
	}

	@RequestMapping(value = "/addMandir", method = RequestMethod.GET)
	public ModelAndView addMandir() {
		ModelAndView mav = new ModelAndView("addMandir");
		Mandir mandir = new Mandir();
		mav.getModelMap().put("addMandir", mandir);
		return mav;
	}

	@RequestMapping(value = "/saveMandir", method = RequestMethod.POST)
	public String saveMandir(@ModelAttribute("addMandir") Mandir mandir,
			BindingResult result, SessionStatus status) {
		String errorPath = "addMandir";
		if (result.hasErrors()) {
			return errorPath;
		}

		Status madirStatus = getStatusByCode(MandirConstants.STATUS_DATA_COLLECTED);
		if (madirStatus == null) {
			return errorPath;
		}
		mandir.setStatus(madirStatus);
		mandir.setInfoGatheredOn(new Date());
		mandirDAO.save(mandir);
		return "redirect:viewAllMandirs.html";
	}

	@RequestMapping(value = "/viewMandirs", method = RequestMethod.GET)
	public ModelAndView viewMandirs(@RequestParam("status") Integer status) {
		ModelAndView mav = new ModelAndView("showMandirs");
		List<Mandir> mandirs = mandirDAO.getMandirsByStatusId(status);
		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", mandirs);
		return mav;
	}

	@RequestMapping(value = "/updateMandir", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") Integer id) {
		ModelAndView mav = new ModelAndView("editMandir");
		Mandir mandir = mandirDAO.getById(id);
		mav.addObject("editMandir", mandir);
		return mav;
	}

	@RequestMapping(value = "/updateMandir", method = RequestMethod.POST)
	public String update(@ModelAttribute("editMandir") Mandir mandir,
			BindingResult result, SessionStatus status) {
		String errorPath = "editMandir";
		if (result.hasErrors()) {
			return errorPath;
		}

		Status madirStatus = getStatusByCode(MandirConstants.STATUS_DATA_COLLECTED);
		if (madirStatus == null) {
			return errorPath;
		}
		mandirDAO.update(mandir);
		status.setComplete();
		return "redirect:viewAllMandirs.html";
	}

	@RequestMapping(value = "/mandirDataFlow", method = RequestMethod.GET)
	public ModelAndView mandirDataFlow(@RequestParam("id") Integer id) {
		ModelAndView mav = new ModelAndView("mandirflow");
		Mandir mandir = mandirDAO.getById(id);
		mav.addObject("editMandir", mandir);
		return mav;
	}

	@RequestMapping("deleteMandir")
	public ModelAndView delete(@RequestParam("id") Integer id) {
		ModelAndView mav = new ModelAndView("redirect:viewAllMandirs.html");
		// mandirDAO.delete(id);
		return mav;
	}
	
	@RequestMapping(value = "/addContact", method = RequestMethod.GET)
	public ModelAndView addContact() {
		ModelAndView mav = new ModelAndView("addContact");
		Contact contact = new Contact();
		mav.getModelMap().put("addContact", contact);
		return mav;
	}

	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public String saveContact(@ModelAttribute("addContact") Contact contact,
			BindingResult result, SessionStatus status) {
		String errorPath = "addContact";
		if (result.hasErrors()) {
			return errorPath;
		}

		contact.setMandirId(1);
		contactDAO.addContact(contact);
		return "redirect:viewAllContacts.html";
	}

	@RequestMapping(value = "/viewAllContacts", method = RequestMethod.GET)
	public ModelAndView viewAllContacts() {
		ModelAndView mav = new ModelAndView("showContacts");
		List<Contact> contactList = contactDAO.getAllContacts();
		System.out.println("+++++++++++++++++++++++ contactList+++++++++++++==" + contactList.size());
		
		mav.addObject("CONTACT_LIST", contactList);
		return mav;
	}


	@RequestMapping(value = "/updateContact", method = RequestMethod.GET)
	public ModelAndView editContact(@RequestParam("id") Integer id) {
		ModelAndView mav = new ModelAndView("editContact");
		Contact contact = contactDAO.getContactById(id);
		mav.addObject("editContact", contact);
		return mav;
	}

	@RequestMapping(value = "/updateContact", method = RequestMethod.POST)
	public String updateContact(@ModelAttribute("editContact") Contact contact,
			BindingResult result, SessionStatus status) {
		String errorPath = "editContact";
		if (result.hasErrors()) {
			return errorPath;
		}
		contactDAO.updateContact(contact);
		return "redirect:viewAllContacts.html";
	}


	/**
	 * @param mandirDAO
	 *            the mandirDAO to set
	 */
	public void setMandirDAO(MandirDAO mandirDAO) {
		this.mandirDAO = mandirDAO;
	}

	/**
	 * @param regionsDAO the regionsDAO to set
	 */
	public void setRegionsDAO(RegionDAO regionsDAO) {
		this.regionsDAO = regionsDAO;
	}

	/**
	 * @param statusDAO
	 *            the statusDAO to set
	 */
	public void setStatusDAO(StatusDAO statusDAO) {
		this.statusDAO = statusDAO;
	}

	public void setCountriesDAO(CountryDAO countriesDAO) {
		this.countriesDAO = countriesDAO;
	}

	private Status getStatusByCode(String code) {
		Status mandirStatus = statusDAO
				.getStatusByCode(MandirConstants.STATUS_DATA_COLLECTED);
		return mandirStatus;
	}

	/**
	 * @param contactDAO the contactDAO to set
	 */
	public void setContactDAO(ContactDAO contactDAO) {
		this.contactDAO = contactDAO;
	}

}
