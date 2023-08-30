package com.pos.hr.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aes.protection.CipherUtils;
import com.pos.admin.model.UserInfoForm;
import com.pos.admin.service.AdminService;
import com.pos.hr.model.EmployeeInformation;
import com.pos.hr.service.HRService;
import com.pos.login.model.MenuInfo;
import com.pos.login.service.LoginService;
import com.pos.lookup.model.LookupModel;
import com.pos.lookup.service.LookupService;

@Controller
@RequestMapping(value = "hr")

@SuppressWarnings(value = { "rawtypes" })

public class HRController {

	CipherUtils oCipherUtils = new CipherUtils();
	
	@Autowired
    private LoginService loginService;
	
	@Autowired
	private LookupService lookupService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private HRService hrService;

	////////////////// EmployeeInfo///////////////////////

	@RequestMapping(value = "employeeInfoView", method = RequestMethod.GET)
	public ModelAndView employeeInfoView(@ModelAttribute EmployeeInformation employeeInformation,
			LookupModel lookupModel, HttpSession session, final RedirectAttributes redirectAttributes) {
		
		if (session.getAttribute("logonSuccessYN") == "Y") {

			String menuId = "M0401";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			ModelAndView model = new ModelAndView("employeeInfoView");
		    model.addObject("designationList", lookupService.designationList());
			return model;
			
			   } else {
					redirectAttributes.addFlashAttribute("message", "You are not authorized for this Page !");
					redirectAttributes.addFlashAttribute("mCode", "0000");
					return new ModelAndView("redirect:/");
				}
		} else {
			return new ModelAndView("redirect:/");
		}
	}
	
	
	@RequestMapping(value = "employeeInfoView/getEmployeeInfo", method = RequestMethod.GET)
	public ModelAndView getEmployeeInfo(@ModelAttribute EmployeeInformation employeeInformation, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {

				EmployeeInformation oEmployeeInformation = new EmployeeInformation();

				oEmployeeInformation = hrService.getEmployeeInfo(employeeInformation);

				// System.out.println("111 +employeeInformation.getEncEmployeeId());
				redirectAttributes.addFlashAttribute("employeeInformation", oEmployeeInformation);

				return new ModelAndView("redirect:/hr/employeeInfo");
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured");
				return new ModelAndView("redirect:/hr/employeeInfoView");
			}
		} else {
			return new ModelAndView("redirect:/");
		}

	}
	
	
	@RequestMapping(value = "employeeInfo", method = RequestMethod.GET)
	public ModelAndView employeeInfo(@ModelAttribute EmployeeInformation employeeInformation,
			LookupModel lookupModel, HttpSession session, final RedirectAttributes redirectAttributes) {
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M0401";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			ModelAndView model = new ModelAndView("employeeInfo2");
			model.addObject("genderList", lookupService.genderList());
			model.addObject("maritalStatusList", lookupService.maritalStatusList());
			model.addObject("religionList", lookupService.religionList());
			model.addObject("designationList", lookupService.designationList());
			return model;
			
			 } else {
					redirectAttributes.addFlashAttribute("message", "You are not authorized for this Page !");
					redirectAttributes.addFlashAttribute("mCode", "0000");
					return new ModelAndView("redirect:/");
				}
		} else {
			return new ModelAndView("redirect:/");
		}
	}
	
	
	@RequestMapping(value = "getEmployeeInfoList", method = RequestMethod.POST)
	public ModelAndView getEmployeeInfoList(@ModelAttribute EmployeeInformation employeeInformation, HttpSession session) {

		if (session.getAttribute("logonSuccessYN") == "Y") {
				ModelAndView mav = new ModelAndView("adminList");
			try {

				List<EmployeeInformation> oEmployeeInfoList = hrService.getEmployeeInfoList(employeeInformation);
				
				//System.out.println("size  "+ oEmployeeInfoList.size());
						
				if (oEmployeeInfoList.size() > 0) {
					mav.addObject("employeeInfoList", oEmployeeInfoList);
				} else {
					mav.addObject("employeeInfoNotFound", "No Employee Found!");
				}

				return mav;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return new ModelAndView("redirect:/");
		}
	}
	
	

	@RequestMapping(value = "getUserEmpInfo", method = RequestMethod.GET)
	public ModelAndView getUserEmpInfo(@ModelAttribute UserInfoForm userInfoForm, HttpSession session,
			HttpServletRequest request, final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {
				String referrer = request.getHeader("referer");
				UserInfoForm oUserInfo = new UserInfoForm();
				oUserInfo = adminService.getUserEmpInfo(userInfoForm);

				if (oCipherUtils.decrypt(oUserInfo.getEncUserId()).equals("")) {
					oUserInfo.setEncUserId("");
				}

				redirectAttributes.addFlashAttribute("userInfoForm", oUserInfo);

				return new ModelAndView("redirect:" + referrer);
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured");
				return new ModelAndView("redirect:/admin/userInfoview");
			}
		} else {
			return new ModelAndView("redirect:/");
		}

	}
	

	@RequestMapping(value = "employeeInfo/empInfoSave", method = RequestMethod.POST)
	public ModelAndView empInfoSave(@ModelAttribute EmployeeInformation employeeInformation,
			LookupModel lookupModel, HttpSession session, final RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {

				//employeeInformation.setUpdateBy((String) session.getAttribute("employeeId"));
				
				 //System.out.println(" getKnownAs "+employeeInformation.getKnownAs());

				EmployeeInformation oEmployeeInformation = new EmployeeInformation();
				
					oEmployeeInformation = hrService.empInfoSave(employeeInformation);

					if (oEmployeeInformation.getEncEmployeeId() != null && oEmployeeInformation.getEncEmployeeId().length() > 0) {
						employeeInformation.setEncEmployeeId(oEmployeeInformation.getEncEmployeeId());
					}

					redirectAttributes.addFlashAttribute("message", oEmployeeInformation.getMessage());
					redirectAttributes.addFlashAttribute("mCode", oEmployeeInformation.getMessageCode());
					
					redirectAttributes.addFlashAttribute("employeeInformation", employeeInformation);

				}  catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured");
				redirectAttributes.addFlashAttribute("employeeInformation", employeeInformation);
			}
			return new ModelAndView("redirect:/hr/employeeInfo");

		} else {
			return new ModelAndView("redirect:/");
		}
		
	}
	


}
