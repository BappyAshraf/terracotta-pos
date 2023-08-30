package com.pos.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pos.user.service.UserService;
import com.pos.user.model.UserInfo;



@Controller
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "changePassword", method = RequestMethod.GET)
	public ModelAndView changePassword(@ModelAttribute UserInfo userInfo, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = new ModelAndView();

		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			//System.out.println("hello");
			//System.out.println("forceExpirePwd " + session.getAttribute("forceExpirePwd"));
			
			if ((session.getAttribute("forceExpirePwd").equals("Y"))) {
				
				//System.out.println("hello1");
				
				modelAndView.setViewName("changePasswordFirst");
			} else {
				
				//System.out.println("hello2");
				
				modelAndView.setViewName("changePasswordInfo");
			}
			return modelAndView;
		} else {
			return new ModelAndView("redirect:/");
		}
	}

	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	public ModelAndView saveChangePassword(@ModelAttribute UserInfo userInfo, BindingResult result, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {

			if (userInfo.getCurrentPassword() == "") {
				redirectAttributes.addFlashAttribute("message", "Enter Current Password !");
				redirectAttributes.addFlashAttribute("mCode", "0000");

			}
			else if (userInfo.getNewPassword() == "") {
				redirectAttributes.addFlashAttribute("message", "Enter New Password !");
				redirectAttributes.addFlashAttribute("mCode", "0000");

			}
			else if (userInfo.getCurrentPassword().equals(userInfo.getNewPassword())) {
				redirectAttributes.addFlashAttribute("message", "No changes are applicable !");
				redirectAttributes.addFlashAttribute("mCode", "0000");

			} else if (!userInfo.getNewPassword().equals(userInfo.getConfirmPassword())) {
				redirectAttributes.addFlashAttribute("message", "New password and confirm password does'nt match !");
				redirectAttributes.addFlashAttribute("mCode", "0000");

			} else {
				UserInfo oUserInfo = new UserInfo();
				userInfo.setEmployeeId((String) session.getAttribute("employeeid"));
				userInfo.setUpdateBy((String) session.getAttribute("employeeid"));
				oUserInfo = userService.userPasswordChng(userInfo);

				redirectAttributes.addFlashAttribute("message", oUserInfo.getMessage());
				redirectAttributes.addFlashAttribute("mCode", oUserInfo.getMessageCode());

			}
			// redirectAttributes.addFlashAttribute("userInfo", userInfo);
		}
		return new ModelAndView("redirect:/user/changePassword");
	}
	
	
}
