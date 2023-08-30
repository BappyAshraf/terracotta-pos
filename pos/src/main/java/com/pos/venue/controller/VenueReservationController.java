package com.pos.venue.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aes.protection.CipherUtils;
import com.pos.login.model.MenuInfo;
import com.pos.login.service.LoginService;
import com.pos.lookup.model.LookupModel;
import com.pos.lookup.service.LookupService;
import com.pos.venue.model.VenueReservationModel;
import com.pos.venue.service.VenueReservationService;

@Controller
@RequestMapping("venueReservation")
public class VenueReservationController {
	
	CipherUtils oCipherUtils = new CipherUtils();
	
	@Autowired
	private LookupService lookupService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private VenueReservationService venueReservationService;
	
	@RequestMapping(value = "reserveVenueView", method = RequestMethod.GET)
	public ModelAndView reserveVenueView(@ModelAttribute VenueReservationModel venueReservationModel, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M0501";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName("reserveVenueView");
			mav.addObject("venueList", lookupService.venueList(lookupModel));
			
			return mav;
			
			} else {
				redirectAttributes.addFlashAttribute("message", "You are not authorized for this Page !");
				redirectAttributes.addFlashAttribute("mCode", "0000");
				return new ModelAndView("redirect:/");
			}

		} else {
			return new ModelAndView("login");

		}
	}
	
	
	@RequestMapping(value = "reserveVenue", method = RequestMethod.GET)
	public ModelAndView reserveVenue(@ModelAttribute VenueReservationModel venueReservationModel, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M0501";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName("reserveVenue");
			mav.addObject("venueList", lookupService.venueList(lookupModel));
			mav.addObject("programmeList", lookupService.programmeList(lookupModel));
			return mav;
			
			 } else {
					redirectAttributes.addFlashAttribute("message", "You are not authorized for this Page !");
					redirectAttributes.addFlashAttribute("mCode", "0000");
					return new ModelAndView("redirect:/");
				}

		} else {
			return new ModelAndView("login");

		}
	}
	
	
	@RequestMapping(value = "getVenueReservedList", method = RequestMethod.POST)
	public ModelAndView getVenueReservedList(@ModelAttribute VenueReservationModel venueReservationModel, HttpSession session,
			LookupModel lookupModel) {
		 //System.out.println("hello");
		if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView("venueReservedList");
			try {

				List<VenueReservationModel> oVenueReseredList = venueReservationService.getVenueReservedList(venueReservationModel);
				if (oVenueReseredList.size() > 0) {
					mav.addObject("venueReseredList", oVenueReseredList);
				} else {
					mav.addObject("venueReseredListNotFound", "No Venue Reserved Info Found!");
				}
				
				//System.out.println("ven res list "+ oVenueReseredList.size());

				return mav;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return new ModelAndView("login");
		}
	}
	
	
	@RequestMapping(value = "getReservedVenue", method = RequestMethod.GET)
	public ModelAndView getReservedVenue(@ModelAttribute VenueReservationModel venueReservationModel, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		// System.out.println("hello");
		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {

				VenueReservationModel oVenueResered = venueReservationService.getVenueReserved(venueReservationModel);
				
				//System.out.println("getReservationDate "+ oVenueResered.getReservationDate());
				
				if(oVenueResered.getReservationDate() == null || oVenueResered.getReservationDate().isEmpty()){
					redirectAttributes.addFlashAttribute("mCode", "0000");
					redirectAttributes.addFlashAttribute("message", "Invalid Data !!!");
					redirectAttributes.addFlashAttribute("venueReservationModel", venueReservationModel);
					 return new ModelAndView("redirect:/venueReservation/reserveVenueView");
				}else{
					redirectAttributes.addFlashAttribute("venueReservationModel", oVenueResered);
				}
				
				 return new ModelAndView("redirect:/venueReservation/reserveVenue");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return new ModelAndView("login");
		}
	}
	
	
	@RequestMapping(value = "saveVenueReservation", method = RequestMethod.POST)
	public ModelAndView saveVenueReservation(@ModelAttribute VenueReservationModel venueReservationModel, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		// System.out.println("hello");
		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {

				VenueReservationModel oVenueResere = venueReservationService.saveVenueReservation(venueReservationModel);
				
				//System.out.println("getReservationDate "+ oVenueResere.getReservationDate());
				
				if(oVenueResere.getmCode() !=null && oVenueResere.getmCode().equals("1111")) {
					redirectAttributes.addFlashAttribute("mCode", oVenueResere.getmCode());
					redirectAttributes.addFlashAttribute("message", oVenueResere.getMessage());
				     return new ModelAndView("redirect:/venueReservation/reserveVenueView");
				} else {
					redirectAttributes.addFlashAttribute("venueReservationModel", venueReservationModel);
				}
				
				redirectAttributes.addFlashAttribute("mCode", oVenueResere.getmCode());
				redirectAttributes.addFlashAttribute("message", oVenueResere.getMessage());
			
			return new ModelAndView("redirect:/venueReservation/reserveVenue");
				
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured !!");
			
				 return new ModelAndView("redirect:/venueReservation/reserveVenue");
			}
		} else {
			return new ModelAndView("login");
		}
	}
	
	

}
