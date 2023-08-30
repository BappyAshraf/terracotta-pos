package com.pos.membership.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aes.protection.CipherUtils;
import com.pos.accounts.model.EmployeeMonthlyConsumption;
import com.pos.admin.model.RoleInfoForm;
import com.pos.hr.model.EmployeeInformation;
import com.pos.login.model.MenuInfo;
import com.pos.login.service.LoginService;
import com.pos.lookup.model.LookupModel;
import com.pos.lookup.service.LookupService;
import com.pos.membership.model.Membership;
import com.pos.membership.service.MembershipService;

@Controller
@RequestMapping("membership")
public class MembershipController {

	CipherUtils oCipherUtils = new CipherUtils();
	
	@Autowired
    private LoginService loginService;
	
	@Autowired
	private LookupService lookupService;
	@Autowired
	private MembershipService membershipService;

	@RequestMapping(value = "addMember", method = RequestMethod.GET)
	public ModelAndView addMember(@ModelAttribute("membership") Membership membership, HttpSession session, LookupModel lookupModel
			, final RedirectAttributes redirectAttributes) {
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M0701";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName("addMember");

			mav.addObject("genderList", lookupService.genderList());
			mav.addObject("maritalStatusList", lookupService.maritalStatusList());
			mav.addObject("religionList", lookupService.religionList());
			mav.addObject("designationList", lookupService.designationList());

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
	

	@RequestMapping(value = "addMemberSave", method = RequestMethod.POST)
	public ModelAndView addMemberSave(@ModelAttribute("membership") Membership membership, LookupModel lookupModel,
			HttpSession session, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {

				Membership oMembership = new Membership();
				membership.setUpdateBy((String) session.getAttribute("employeeId"));
				oMembership = membershipService.addMemberSave(membership);
				
				if (oMembership.getEncMemberId() != null && oMembership.getEncMemberId().length() > 0) {
					membership.setEncMemberId(oMembership.getEncMemberId());
				}

				redirectAttributes.addFlashAttribute("message", oMembership.getMessage());
				redirectAttributes.addFlashAttribute("mCode", oMembership.getMessageCode());

				redirectAttributes.addFlashAttribute("membership", membership);

			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured");
				redirectAttributes.addFlashAttribute("membership", membership);
			}
			return new ModelAndView("redirect:/membership/addMember");

		} else {
			return new ModelAndView("redirect:/");
		}

	}
	
	

	@RequestMapping(value = "generateCard", method = RequestMethod.GET)
	public ModelAndView generateCard(@ModelAttribute Membership membership, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {
		// System.out.println("hello");
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M0702";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName("generateCard");
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
	
	

	@RequestMapping(value = "accountStatus", method = RequestMethod.GET)
	public ModelAndView accountStatus(@ModelAttribute Membership membership, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {
		// System.out.println("hello");
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M0703";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName("accountStatus");
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
	
	
	
	@RequestMapping(value = "getMemberList", method = RequestMethod.POST)
	public ModelAndView getMemberList(@ModelAttribute Membership membership, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {
		// System.out.println("hello");
		ModelAndView mav = new ModelAndView();
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {
				List<Membership> oMembershipList = new ArrayList<Membership>();

				oMembershipList = membershipService.getMemberList(membership);
				
				 //System.out.println("oMembershipList.size()  " + oMembershipList.size());
				
				if(oMembershipList.size() >0) {
					mav.addObject("membershipList", oMembershipList);
				} else {
					mav.addObject("membershipListNotFound", "No Member Found !!");
				}
				
				mav.setViewName("adminList");
				return mav;
				
			} catch (Exception e) {
				e.printStackTrace();
				mav.addObject("membershipListNotFound", "Error Occured !!");
			}
		} else {
			return new ModelAndView("login");
		}
		return mav;
	}
	
	
	
	@RequestMapping(value = "getMemberInfo", method = RequestMethod.GET)
	public ModelAndView getMemberInfo(@ModelAttribute Membership membership, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {
				Membership oMembership = new Membership();
				oMembership = membershipService.getMemberInfo(membership);
				
				if(oMembership.getMemberId() !=null && oMembership.getMemberId().length() >0) {
					redirectAttributes.addFlashAttribute("membership", oMembership);
				} else {
					redirectAttributes.addFlashAttribute("mCode", "0000");
					redirectAttributes.addFlashAttribute("message", "No Member Found !!! ");
				}
				return new ModelAndView("redirect:/membership/addMember");
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured");
				return new ModelAndView("redirect:/membership/addMember");
			}
		} else {
			return new ModelAndView("login");

		}
	}
	
	
	
	
	
}