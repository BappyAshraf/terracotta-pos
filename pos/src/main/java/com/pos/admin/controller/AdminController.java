package com.pos.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aes.protection.CipherUtils;
import com.pos.admin.model.EmployeeInfo;
import com.pos.admin.model.RoleInfoForm;
import com.pos.admin.model.RoleMenuMapping;
import com.pos.admin.model.UserInfoForm;
import com.pos.admin.service.AdminService;
import com.pos.login.model.MenuInfo;
import com.pos.login.service.LoginService;
import com.pos.lookup.model.LookupModel;
import com.pos.lookup.service.LookupService;



@Controller
@RequestMapping(value = "admin")
public class AdminController {
	CipherUtils oCipherUtils = new CipherUtils();
	
	@Autowired
    private LoginService loginService;
	
	@Autowired
	private LookupService lookupService;

	@Autowired
	private AdminService adminService;

	////////////////// Role Info///////////////////////

	@RequestMapping(value = "roleInfoView", method = RequestMethod.GET)
	public ModelAndView roleInfoView(@ModelAttribute RoleInfoForm roleInfoForm, LookupModel lookupModel,
			HttpSession session, final RedirectAttributes redirectAttributes) {
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M0301";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			ModelAndView model = new ModelAndView("roleInfoView");
			List<RoleInfoForm> roleList = new ArrayList<RoleInfoForm>();
			roleList = adminService.getRoleInfoList(roleInfoForm);
			if(roleList.size()>0){
				model.addObject("roleList",roleList);
			}else{
				model.addObject("noRoleFound","No Role Found !!!");
			}
			
			return model;
			
		    } else {
						redirectAttributes.addFlashAttribute("message", "You are not authorized for this Page !");
						redirectAttributes.addFlashAttribute("mCode", "0000");
						return new ModelAndView("redirect:/");
					}

		} else {
			return new ModelAndView("login");
		}
	}
	
	
	@RequestMapping(value = "roleInfoView/getRoleInfo", method = RequestMethod.GET)
	public ModelAndView getRoleInfo(@ModelAttribute RoleInfoForm roleInfoForm, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {
				RoleInfoForm oRoleInfoForm = new RoleInfoForm();
				oRoleInfoForm = adminService.getRoleInfo(roleInfoForm);
				redirectAttributes.addFlashAttribute("roleInfoForm", oRoleInfoForm);

				return new ModelAndView("redirect:/admin/roleInfo");
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured");
				return new ModelAndView("redirect:/admin/roleInfoView");
			}
		} else {
			return new ModelAndView("login");

		}

	}
	
	
	@RequestMapping(value = "roleInfoView/deleteRole", method = RequestMethod.POST)
	public @ResponseBody RoleInfoForm deleteRole(@ModelAttribute RoleInfoForm roleInfoForm, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		RoleInfoForm oRoleInfoForm = new RoleInfoForm();
		
		//System.out.println("DDD RO 1");
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {
				
				oRoleInfoForm = adminService.deleteRole(roleInfoForm);
				return oRoleInfoForm;
			} catch (Exception e) {
				e.printStackTrace();
				oRoleInfoForm.setMessageCode("0000");
				oRoleInfoForm.setMessage("Error occured");
				return oRoleInfoForm;
			}
		} 
		return oRoleInfoForm;
	}
	
	
	@RequestMapping(value = "roleInfoView/getRoleListAjax", method = RequestMethod.POST)
	public ModelAndView getRoleListAjax(@ModelAttribute RoleInfoForm roleInfoForm, HttpSession session) {

		ModelAndView mav = new ModelAndView("adminList");

		//System.out.println("RO LI AJA ");
		
		List<RoleInfoForm> roleList = new ArrayList<RoleInfoForm>();
		roleList = adminService.getRoleInfoList(roleInfoForm);
		if(roleList.size()>0){
			mav.addObject("roleList",roleList);
		}else{
			mav.addObject("noRoleFound","No Role Found !!!");
		}
		return mav;
	}
	
	
	@RequestMapping(value = "roleInfo", method = RequestMethod.GET)
	public ModelAndView roleInfo(@ModelAttribute RoleInfoForm roleInfoForm, LookupModel lookupModel,
			HttpSession session, final RedirectAttributes redirectAttributes) {
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M0301";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			ModelAndView model = new ModelAndView("roleInfo");
			return model;
			
			 } else {
					redirectAttributes.addFlashAttribute("message", "You are not authorized for this Page !");
					redirectAttributes.addFlashAttribute("mCode", "0000");
					return new ModelAndView("redirect:/");
				}

		} else {
			return new ModelAndView("login");

		}
	}
	
	
	@RequestMapping(value = "roleInfo/saveRoleInfo", method = RequestMethod.POST)
	public ModelAndView saveRoleInfo(@ModelAttribute("roleInfoForm") RoleInfoForm roleInfoForm, BindingResult result,
			HttpSession session, final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {

			if (roleInfoForm.getRoleName() == "" || roleInfoForm.getRoleName() == null) {
				redirectAttributes.addFlashAttribute("message", "Enter Role Name !");
				redirectAttributes.addFlashAttribute("mCode", "0000");
			}

			else if (roleInfoForm.getEnabledYN() == "" || roleInfoForm.getEnabledYN() == null) {
				redirectAttributes.addFlashAttribute("message", "Select Role Status !");
				redirectAttributes.addFlashAttribute("mCode", "0000");
			}

			else {
				
				RoleInfoForm oRoleInfoForm = new RoleInfoForm();
				// projectInfo.setUpdateBy((String)session.getAttribute("employeeId"));
				oRoleInfoForm = adminService.saveRoleInfo(roleInfoForm);

				redirectAttributes.addFlashAttribute("message", oRoleInfoForm.getMessage());
				redirectAttributes.addFlashAttribute("mCode", oRoleInfoForm.getMessageCode());
			}
			redirectAttributes.addFlashAttribute("roleInfoForm", roleInfoForm);
		}
		return new ModelAndView("redirect:/admin/roleInfo");
	}
	
	
	@RequestMapping(value = "roleMenuMappingView", method = RequestMethod.GET)
	public ModelAndView roleMenuMappingView(@ModelAttribute RoleInfoForm roleInfoForm, LookupModel lookupModel,
			HttpSession session, final RedirectAttributes redirectAttributes) {
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M0302";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			ModelAndView model = new ModelAndView("roleMenuMappingView");
			model.addObject("roleList", lookupService.roleList(lookupModel));
			return model;

            } else {
            			redirectAttributes.addFlashAttribute("message", "You are not authorized for this Page !");
            			redirectAttributes.addFlashAttribute("mCode", "0000");
            			return new ModelAndView("redirect:/");
            		}
		} else {
			return new ModelAndView("login");

		}
	}
	

	@RequestMapping(value = "roleMenuMappingView/getRoleAllMenuList", method = RequestMethod.POST)
	public ModelAndView getRoleAllMenuList(@ModelAttribute RoleMenuMapping roleMenuMapping, HttpSession session) {

		ModelAndView mav = new ModelAndView("adminList");

		List<RoleMenuMapping> oRoleMenuList = adminService.getRoleALLMenuList(roleMenuMapping);
		
		//System.out.println("oRoleMenuList "+oRoleMenuList.size() );

		if (oRoleMenuList.size() > 0) {
			mav.addObject("roleAllMenuList", oRoleMenuList);
		} else {
			mav.addObject("roleAllMenuNotFound", "No Data Found!");
		}
		return mav;
	}
	
	
	@RequestMapping(value = "roleMenuMapping", method = RequestMethod.GET)
	public ModelAndView roleMenuMapping(@ModelAttribute RoleInfoForm roleInfoForm, LookupModel lookupModel,
			HttpSession session, final RedirectAttributes redirectAttributes) {
		//System.out.println("2222222222222 " );
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M0302";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			ModelAndView model = new ModelAndView("roleMenuMapping");
			model.addObject("roleList", lookupService.roleList(lookupModel));
			model.addObject("mainMenuList", lookupService.mainMenuList(lookupModel));
			return model;
			
			 } else {
					redirectAttributes.addFlashAttribute("message", "You are not authorized for this Page !");
					redirectAttributes.addFlashAttribute("mCode", "0000");
							
						return new ModelAndView("redirect:/");
					}
		} else {
			return new ModelAndView("login");
		}
	}
	
	
	@RequestMapping(value = "roleMenuMapping/getRoleMenuMappingList", method = RequestMethod.POST)
	public ModelAndView getRoleMenuMappingList(@ModelAttribute RoleMenuMapping roleMenuMapping, HttpSession session) {

		ModelAndView mav = new ModelAndView("adminList");

		List<RoleMenuMapping> oRoleMenuList = adminService.getRoleMenuMappingList(roleMenuMapping);

		if (oRoleMenuList.size() > 0) {
			mav.addObject("roleMenuMappingList", oRoleMenuList);
		} else {
			mav.addObject("roleMenuNotFound", "No Data Found!");
		}
		return mav;
	}
	
	
	
	@RequestMapping(value = "roleMenuMapping/saveRoleMenuMapping", method = RequestMethod.POST)
	public ModelAndView saveCrntRoleMenuMapping(@ModelAttribute("roleMenuMapping") RoleMenuMapping roleMenuMapping,
			LookupModel lookupModel, BindingResult result, HttpSession session,
			final RedirectAttributes redirectAttributes) {

			roleMenuMapping.setUpdateBy((String) session.getAttribute("employeeid")); /*(String) session.getAttribute("employeeId")*/

			if (roleMenuMapping.getRoleId() == "" || roleMenuMapping.getRoleId() == null) {
				redirectAttributes.addFlashAttribute("message", "Please Select Role !!!");
				redirectAttributes.addFlashAttribute("mCode", "0000");
			}else if (roleMenuMapping.getMainMenuId()== "" || roleMenuMapping.getMainMenuId() == null) {
				redirectAttributes.addFlashAttribute("message", "Please Select Parent Menu !!!");
				redirectAttributes.addFlashAttribute("mCode", "0000");
			} else {

				RoleMenuMapping oRoleMenuMapping = adminService.saveRoleMenuMapping(roleMenuMapping);

				redirectAttributes.addFlashAttribute("message", oRoleMenuMapping.getMessage());
				redirectAttributes.addFlashAttribute("mCode", oRoleMenuMapping.getMessageCode());

				List<RoleMenuMapping> oRoleMenuList = adminService.getRoleMenuMappingList(roleMenuMapping);
				redirectAttributes.addFlashAttribute("roleMenuMappingList", oRoleMenuList);
				redirectAttributes.addFlashAttribute("roleMenuMapping", roleMenuMapping);
			}
			// System.out.println("getUserTypeId "
			// +roleMenuMapping.getUserTypeId());

			return new ModelAndView("redirect:/admin/roleMenuMapping");
	}
	

	////////////////// EmployeeInfo///////////////////////

	@RequestMapping(value = "employeeInfoView", method = RequestMethod.GET)
	public ModelAndView employeeInfoView(@ModelAttribute EmployeeInfo employeeInfo, LookupModel lookupModel,
			HttpSession session, final RedirectAttributes redirectAttributes) {
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M0401";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			ModelAndView model = new ModelAndView("employeeInfoView");
		//	model.addObject("departmentList", lookupService.departmentList());
		//	model.addObject("designationList", lookupService.designationList());
			return model;
			
			   } else {
					redirectAttributes.addFlashAttribute("message", "You are not authorized for this Page !");
					redirectAttributes.addFlashAttribute("mCode", "0000");
							
					return new ModelAndView("redirect:/");
				}
		} else {
			return new ModelAndView("login");
		}
	}
	

	@RequestMapping(value = "employeeInfo", method = RequestMethod.GET)
	public ModelAndView employeeInfo(@ModelAttribute EmployeeInfo employeeInfo, LookupModel lookupModel,
			HttpSession session, final RedirectAttributes redirectAttributes) {
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M0401";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			ModelAndView model = new ModelAndView("employeeInfo");
		//	model.addObject("departmentList", lookupService.departmentList());
		//	model.addObject("designationList", lookupService.designationList());
			return model;
			
			 } else {
					redirectAttributes.addFlashAttribute("message", "You are not authorized for this Page !");
					redirectAttributes.addFlashAttribute("mCode", "0000");
					return new ModelAndView("redirect:/");
				}

		} else {
			return new ModelAndView("login");
		}
	}
	

	@RequestMapping(value = "getEmployeeInfoList", method = RequestMethod.POST)
	public ModelAndView getEmployeeInfoList(@ModelAttribute EmployeeInfo employeeInfo, HttpSession session) {

		if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView("adminList");
			try {

				List<EmployeeInfo> oEmployeeInfoList = adminService.getEmployeeInfoList(employeeInfo);
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

	@RequestMapping(value = "saveEmployeeInfo", method = RequestMethod.POST)
	public ModelAndView saveEmployeeInfo(@ModelAttribute("employeeInfo") EmployeeInfo employeeInfo,
			BindingResult result, HttpSession session, final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {

			if (employeeInfo.getEmployeeName() == "" || employeeInfo.getEmployeeName() == null) {
				redirectAttributes.addFlashAttribute("message", "Enter Employee Name !");
				redirectAttributes.addFlashAttribute("mCode", "0000");
			}

			else if (employeeInfo.getDesignationId() == "" || employeeInfo.getDesignationId() == null) {
				redirectAttributes.addFlashAttribute("message", "Select Designation !");
				redirectAttributes.addFlashAttribute("mCode", "0000");
			}

			else if (employeeInfo.getEnabledYN() == "" || employeeInfo.getEnabledYN() == null) {
				redirectAttributes.addFlashAttribute("message", "Select Status !");
				redirectAttributes.addFlashAttribute("mCode", "0000");
			}

			else {

				EmployeeInfo oEmployeeInfo = new EmployeeInfo();
				employeeInfo.setUpdateBy((String) session.getAttribute("employeeId"));
			//	oEmployeeInfo = adminService.saveEmployeeInfo(employeeInfo);

				redirectAttributes.addFlashAttribute("message", oEmployeeInfo.getMessage());
				redirectAttributes.addFlashAttribute("mCode", oEmployeeInfo.getMessageCode());
			}
			redirectAttributes.addFlashAttribute("employeeInfo", employeeInfo);
		}
		return new ModelAndView("redirect:/admin/employeeInfo");
	}

	@RequestMapping(value = "getEmployeeInfo", method = RequestMethod.GET)
	public ModelAndView getEmployeeInfo(@ModelAttribute EmployeeInfo employeeInfo, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {
				EmployeeInfo oEmployeeInfo = new EmployeeInfo();
		//		oEmployeeInfo = adminService.getEmployeeInfo(employeeInfo);
				redirectAttributes.addFlashAttribute("employeeInfo", oEmployeeInfo);

				return new ModelAndView("redirect:/admin/employeeInfo");
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured");
				return new ModelAndView("redirect:/admin/employeeInfoView");
			}
		} else {
			return new ModelAndView("login");
		}

	}

	/*
	 * @RequestMapping(value = "deleteEmployeeInfo", method =
	 * RequestMethod.POST) public @ResponseBody EmployeeInfo
	 * deleteProject(@ModelAttribute EmployeeInfo employeeInfo, BindingResult
	 * result, HttpSession session) {
	 * 
	 * EmployeeInfo oEmployeeInfo = new EmployeeInfo(); if
	 * (session.getAttribute("employeeId") != null) {
	 * 
	 * try { if (employeeInfo.getEncEmployeeId() != "" ||
	 * employeeInfo.getEncEmployeeId() != null) { oEmployeeInfo =
	 * adminService.deleteEmployeeInfo(employeeInfo);
	 * 
	 * } } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * return oEmployeeInfo; }
	 */

	@RequestMapping(value = "userInfoView", method = RequestMethod.GET)
	public ModelAndView userInfoview(LookupModel lookupModel, HttpSession session, final RedirectAttributes redirectAttributes) {

		ModelAndView model = new ModelAndView();

		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M0303";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {

			model.setViewName("userInfoView");
			return model;
			
			 } else {
					redirectAttributes.addFlashAttribute("message", "You are not authorized for this Page !");
					redirectAttributes.addFlashAttribute("mCode", "0000");
					return new ModelAndView("redirect:/");
				}
			
		} else {
			return new ModelAndView("login");
		}

	}
	
	

	@RequestMapping(value = "userInfo", method = RequestMethod.GET)
	public ModelAndView userInfo(@ModelAttribute UserInfoForm userInfoForm, LookupModel lookupModel,
			HttpSession session, final RedirectAttributes redirectAttributes) {

		ModelAndView model = new ModelAndView();

		if (session.getAttribute("logonSuccessYN") == "Y") {

			try {
				
				String menuId = "M0303";
				MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
						menuId);
				
				if (menuInfo.getMenuId().equals(menuId)) {

		//		model.addObject("departmentList", lookupService.departmentList());
				model.addObject("designationList", lookupService.designationList());

				if (userInfoForm.getEncUserId() != null && userInfoForm.getEncUserId() != "") {
					userInfoForm.setUserId(oCipherUtils.decrypt(userInfoForm.getEncUserId()));
				} else {
					userInfoForm.setUserId("");
				}
				
				//System.out.println("userInfoForm.GetUserId() " +userInfoForm.getUserId());

				if (userInfoForm.getUserId() != null || userInfoForm.getUserId() != "") {
					List<UserInfoForm> oUserRoleList = adminService.getUserRoleList(userInfoForm.getUserId());

					if (oUserRoleList.size() > 0) {
						model.addObject("roleList", oUserRoleList);
					} else {
						model.addObject("roleNotFound", "No Role Found!");
					}
				}
				model.setViewName("userInfo");
				return model;
				
				   } else {
						redirectAttributes.addFlashAttribute("message", "You are not authorized for this Page !");
						redirectAttributes.addFlashAttribute("mCode", "0000");
								
						return new ModelAndView("redirect:/");
					}
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured");
				return new ModelAndView("redirect:/admin/userInfoview");
			}
		} else {
			return new ModelAndView("login");
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
					// System.out.println("aaaaaaaaaaaa");
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
			return new ModelAndView("login");
		}

	}

	@RequestMapping(value = "getUserEmpInfoForAjax", method = RequestMethod.POST)
	public @ResponseBody UserInfoForm getUserEmpInfoForAjax(@ModelAttribute UserInfoForm userInfoForm,
			HttpSession session, HttpServletRequest request, final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {
				UserInfoForm oUserInfo = new UserInfoForm();
				oUserInfo = adminService.getUserEmpInfo(userInfoForm);

				if (oCipherUtils.decrypt(oUserInfo.getEncUserId()).equals("")) {
					// System.out.println("aaaaaaaaaaaa");
					oUserInfo.setEncUserId("");
				}

				return oUserInfo;
			} catch (Exception e) {
				e.printStackTrace();

				return null;
			}
		} else {
			return null;
		}

	}

	@RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
	public ModelAndView getUserInfo(@ModelAttribute UserInfoForm userInfoForm, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {
				UserInfoForm oUserInfo = new UserInfoForm();
				oUserInfo = adminService.getUserInfo(userInfoForm);
				redirectAttributes.addFlashAttribute("userInfoForm", oUserInfo);

				return new ModelAndView("redirect:/admin/userInfo");
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured");
				return new ModelAndView("redirect:/admin/userInfoView");
			}
		} else {
			return new ModelAndView("login");
		}

	}

	@RequestMapping(value = "getUserInfoList", method = RequestMethod.POST)
	public ModelAndView getUserInfoList(@ModelAttribute UserInfoForm userInfoForm, HttpSession session) {

		if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView("adminList");
			try {

				List<UserInfoForm> oUserList = adminService.getUserInfoList(userInfoForm);
				if (oUserList.size() > 0) {
					mav.addObject("userList", oUserList);
				} else {
					mav.addObject("userNotFound", "No user Found!");
				}

				return mav;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return new ModelAndView("login");
		}
	}

	@RequestMapping(value = "checkUserName", method = RequestMethod.POST)
	public @ResponseBody String checkUserName(@ModelAttribute UserInfoForm userInfoForm, HttpSession session) {

		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {

				String userCount = adminService.checkUserName(userInfoForm);

				return userCount;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return ("redirect:/");
		}
	}
	

	@RequestMapping(value = "saveUserInfo", method = RequestMethod.POST)
	public ModelAndView saveCrntUserInfo(@ModelAttribute("userInfoForm") UserInfoForm userInfoForm,
			BindingResult result, HttpSession session, final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {

			if (userInfoForm.getEncEmployeeId() == "" || userInfoForm.getEncEmployeeId() == null) {
				redirectAttributes.addFlashAttribute("message", "Select an Employee !");
				redirectAttributes.addFlashAttribute("mCode", "0000");
			}

			else if (userInfoForm.getEnabledYN() == "" || userInfoForm.getEnabledYN() == null) {
				redirectAttributes.addFlashAttribute("message", "Select Employee Status !");
				redirectAttributes.addFlashAttribute("mCode", "0000");
			}

			else {

				UserInfoForm oUserInfo = new UserInfoForm();
				userInfoForm.setUpdateBy((String) session.getAttribute("employeeid"));
				
				// System.out.println("userInfoForm.getUpdateBy() " + userInfoForm.getUpdateBy());
				 
				oUserInfo = adminService.saveUserInfo(userInfoForm);

				redirectAttributes.addFlashAttribute("message", oUserInfo.getMessage());
				redirectAttributes.addFlashAttribute("mCode", oUserInfo.getMessageCode());

			}

			redirectAttributes.addFlashAttribute("userInfoForm", userInfoForm);

		}

		return new ModelAndView("redirect:/admin/userInfo");
	}
	
	

	@RequestMapping(value = "deleteUserInfo", method = RequestMethod.POST)
	public @ResponseBody UserInfoForm deleteCrntAgentInfo(@ModelAttribute UserInfoForm userInfoForm,
			BindingResult result, HttpSession session) {

		UserInfoForm oUserInfo = new UserInfoForm();
		if (session.getAttribute("logonSuccessYN") == "Y") {

			try {
				if (userInfoForm.getEncUserId() != "" || userInfoForm.getEncUserId() != null) {
					// oUserInfo =
					// userInfoService.deleteUserInfo(userInfo.getEncEmployeeId());

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return oUserInfo;
	}



}
