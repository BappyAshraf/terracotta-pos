package com.pos.accounts.controller;

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
import com.pos.accounts.model.AdCategoryList;
import com.pos.accounts.model.EmployeeMonthlyConsumption;
import com.pos.accounts.model.OwnerConsumptionInfo;
import com.pos.accounts.model.SalaryProcessModel;
import com.pos.accounts.model.StockProcess;
import com.pos.accounts.model.SupplierInfo;
import com.pos.accounts.service.AccountsService;
import com.pos.admin.model.UserInfoForm;
import com.pos.admin.service.AdminService;
import com.pos.hr.model.EmployeeInformation;
import com.pos.hr.service.HRService;
import com.pos.login.model.MenuInfo;
import com.pos.login.service.LoginService;
import com.pos.lookup.model.LookupModel;
import com.pos.lookup.service.LookupService;
import com.pos.membership.model.Membership;
import com.pos.membership.service.MembershipService;
import com.pos.pointOfSale.model.PointOfSale;

@Controller
@RequestMapping(value = "accounts")

@SuppressWarnings(value = { "rawtypes" })

public class AccountsController {

	CipherUtils oCipherUtils = new CipherUtils();
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private LookupService lookupService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private HRService hrService;
	
	@Autowired
	private AccountsService accountsService;
	
	@Autowired
	private MembershipService membershipService;

	////////////////// Employee Monthly Consumption Info///////////////////////

	@RequestMapping(value = "empMonthlyConsumption", method = RequestMethod.GET)
	public ModelAndView empMonthlyConsumption(@ModelAttribute("employeeMonthlyConsumption") EmployeeMonthlyConsumption employeeMonthlyConsumption,
			LookupModel lookupModel, HttpSession session, final RedirectAttributes redirectAttributes) {
		
		//System.out.println("sadddddddddd");
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M1001";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			
			ModelAndView model = new ModelAndView("employeeMonthlyConsumption");
			model.addObject("allowanceDeducCategoryList", lookupService.allowanceDeducCategoryListForEmpConsume());
			
		    model.addObject("designationList", lookupService.designationList());
			
			model.addObject("message", employeeMonthlyConsumption.getMessage());
			model.addObject("mCode", employeeMonthlyConsumption.getMessageCode());
			
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
	
	
	@RequestMapping(value = "empMonthlyConsumption/getEmpMonthlyConsumptionList", method = RequestMethod.GET)
	public ModelAndView getEmpMonthlyConsumptionList(@ModelAttribute("employeeMonthlyConsumption") EmployeeMonthlyConsumption employeeMonthlyConsumption, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {

				List<EmployeeMonthlyConsumption> oEmployeeMonthlyConsumptionList = new ArrayList<EmployeeMonthlyConsumption>();

				oEmployeeMonthlyConsumptionList = accountsService.getEmpMonthlyConsumption(employeeMonthlyConsumption);

				 //System.out.println("oEmployeeMonthlyConsumptionList.size()  " + oEmployeeMonthlyConsumptionList.size());
				
				if(oEmployeeMonthlyConsumptionList.size() >0) {
					redirectAttributes.addFlashAttribute("employeeMonthlyConsumptionList", oEmployeeMonthlyConsumptionList);
				} else {
					redirectAttributes.addFlashAttribute("employeeMonthlyConsumptionNotFound", "No Data Found !!");
				}
				
				redirectAttributes.addFlashAttribute("employeeMonthlyConsumption", employeeMonthlyConsumption);
				
				return new ModelAndView("redirect:/accounts/empMonthlyConsumption");
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured !!");
				return new ModelAndView("redirect:/accounts/empMonthlyConsumption");
			}
		} else {
			return new ModelAndView("redirect:/");
		}

	}
	
	
	@RequestMapping(value = "empMonthlyConsumption/getConsumeInfo", method = RequestMethod.GET)
	public ModelAndView getConsumeInfo(@ModelAttribute("employeeMonthlyConsumption") EmployeeMonthlyConsumption employeeMonthlyConsumption, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {

				EmployeeMonthlyConsumption oEmployeeMonthlyConsumption = new EmployeeMonthlyConsumption();

				oEmployeeMonthlyConsumption = accountsService.getConsumeInfo(employeeMonthlyConsumption);
				
				redirectAttributes.addFlashAttribute("employeeMonthlyConsumption", oEmployeeMonthlyConsumption);
				
				return new ModelAndView("redirect:/accounts/empMonthlyConsumption");
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured !!");
				return new ModelAndView("redirect:/accounts/empMonthlyConsumption");
			}
		} else {
			return new ModelAndView("redirect:/");
		}

	}
	
	
	
	@RequestMapping(value = "empMonthlyConsumptionSave", method = RequestMethod.POST)
	public ModelAndView empMonthlyConsumptionSave(@ModelAttribute EmployeeMonthlyConsumption employeeMonthlyConsumption,
			LookupModel lookupModel, HttpSession session, final RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			try {

				employeeMonthlyConsumption.setUpdateBy((String) session.getAttribute("employeeid"));
				//System.out.println(" sess  emp id  "+  (String) session.getAttribute("employeeid"));

				EmployeeMonthlyConsumption oEmployeeMonthlyConsumption = new EmployeeMonthlyConsumption();
				
				oEmployeeMonthlyConsumption = accountsService.empMonthlyConsumptionSave(employeeMonthlyConsumption);

				employeeMonthlyConsumption.setMessage(oEmployeeMonthlyConsumption.getMessage());
				employeeMonthlyConsumption.setMessageCode(oEmployeeMonthlyConsumption.getMessageCode());
					
					redirectAttributes.addFlashAttribute("employeeMonthlyConsumption", employeeMonthlyConsumption);

				}  catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured in saving data !!");
				redirectAttributes.addFlashAttribute("employeeMonthlyConsumption", employeeMonthlyConsumption);
			}
			return new ModelAndView("redirect:/accounts/empMonthlyConsumption/getEmpMonthlyConsumptionList");

		} else {
			return new ModelAndView("redirect:/");
		}
		
	}
	

	@RequestMapping(value = "salaryProcess", method = RequestMethod.GET)
	public ModelAndView salaryProcess(@ModelAttribute("salaryProcessModel") SalaryProcessModel salaryProcessModel,
			LookupModel lookupModel, HttpSession session , final RedirectAttributes redirectAttributes) {
		
		//System.out.println("sadddddddddd");
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M1002";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			ModelAndView model = new ModelAndView("salaryProcess");
			
			  model.addObject("designationList", lookupService.designationList());
			  
			  if((salaryProcessModel.getEncEmployeeId() !=null)
					  && (salaryProcessModel.getMonth() !=null && salaryProcessModel.getMonth().length() >0)
					  && (salaryProcessModel.getYear() !=null && salaryProcessModel.getYear().length() >0)) {
				  
			  List<SalaryProcessModel> oSalaryProcessSearchList = new ArrayList<SalaryProcessModel>();
				
				oSalaryProcessSearchList = accountsService.salaryProcessedSearch(salaryProcessModel);
				
				if(oSalaryProcessSearchList.size() >0) {
					model.addObject("salaryProcessSearchList", oSalaryProcessSearchList);
					
					SalaryProcessModel oSalaryProcessModel = new SalaryProcessModel();
					oSalaryProcessModel = accountsService.grossSalary(salaryProcessModel);
					
					model.addObject("grossSalary", oSalaryProcessModel.getAmount());
					model.addObject("finalizedYN", oSalaryProcessModel.getFinalizedYN());
					model.addObject("encPayrollId", oSalaryProcessModel.getEncPayrollId());
					
				} else {
					model.addObject("salaryProcessSearchListNotFound", "Salary is not processed yet !!");
				}
				
			  }
			
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
	
	
	@RequestMapping(value = "salaryProcess/salaryProcessSetup", method = RequestMethod.POST, params="process")
	public ModelAndView salaryProcessSetup(@ModelAttribute("salaryProcessModel") SalaryProcessModel salaryProcessModel, LookupModel

	lookupModel, HttpSession session, final RedirectAttributes redirectAttributes) {
		if (session.getAttribute("logonSuccessYN") == "Y") {

			try {
				
				SalaryProcessModel oSalaryProcessModel = new SalaryProcessModel();
				oSalaryProcessModel = accountsService.grossSalary(salaryProcessModel);
				
				if((oSalaryProcessModel.getEncPayrollId() !=null && oSalaryProcessModel.getEncPayrollId().length() >0)
						&& (oSalaryProcessModel.getFinalizedYN() !=null && oSalaryProcessModel.getFinalizedYN().equals("YES"))) {
					
					redirectAttributes.addFlashAttribute("mCode", "0000");
					redirectAttributes.addFlashAttribute("message", "Salary Already Finalized Of This Employee !!");
					
					redirectAttributes.addFlashAttribute("salaryProcessModel", salaryProcessModel);
					return new ModelAndView("redirect:/accounts/salaryProcess");
				} else {
				
			List<SalaryProcessModel> oSalaryProcessSetupList = new ArrayList<SalaryProcessModel>();
			
			oSalaryProcessSetupList = accountsService.salaryProcessSetup(salaryProcessModel);
			
			if(oSalaryProcessSetupList.size() >0) {
				redirectAttributes.addFlashAttribute("salaryProcessSetupList", oSalaryProcessSetupList);
			} else {
				redirectAttributes.addFlashAttribute("salaryProcessSetupListNotFound", "No Data Found !!");
			}
			
		}
		
			redirectAttributes.addFlashAttribute("salaryProcessModel", salaryProcessModel);
			
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured !!");
				return new ModelAndView("redirect:/accounts/salaryProcess");
			}
			
		} else {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("redirect:/accounts/salaryProcess");
	}
	
	
	@RequestMapping(value = "salaryProcess/salaryProcessSetup", method = RequestMethod.POST, params="search")
	public ModelAndView salaryProcessedSearch(@ModelAttribute("salaryProcessModel") SalaryProcessModel salaryProcessModel, LookupModel

	lookupModel, HttpSession session, final RedirectAttributes redirectAttributes) {
		if (session.getAttribute("logonSuccessYN") == "Y") {

			try {
		
			redirectAttributes.addFlashAttribute("salaryProcessModel", salaryProcessModel);
			
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured !!");
				return new ModelAndView("redirect:/accounts/salaryProcess");
			}
			
		} else {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("redirect:/accounts/salaryProcess");
	}
	
	
	
	@RequestMapping(value = "salaryProcess/salaryProcessSetup", method = RequestMethod.POST, params="save")
	public ModelAndView salaryProcessSave(@ModelAttribute("salaryProcessModel") SalaryProcessModel salaryProcessModel, LookupModel

	lookupModel, HttpSession session, final RedirectAttributes redirectAttributes) {
		if (session.getAttribute("logonSuccessYN") == "Y") {

			try {
			SalaryProcessModel oSalaryProcessModel = new SalaryProcessModel();
			
			salaryProcessModel.setUpdateBy((String) session.getAttribute("employeeid"));
			
			oSalaryProcessModel = accountsService.salaryProcessSave(salaryProcessModel);
			
			redirectAttributes.addFlashAttribute("mCode", salaryProcessModel.getMessageCode());
			redirectAttributes.addFlashAttribute("message", salaryProcessModel.getMessage());
			
			redirectAttributes.addFlashAttribute("salaryProcessSetupList", salaryProcessModel.getAdCategoryIdList());
			redirectAttributes.addFlashAttribute("salaryProcessModel", salaryProcessModel);
			
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured !!");
				return new ModelAndView("redirect:/accounts/salaryProcess");
			}
			
		} else {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("redirect:/accounts/salaryProcess");
	}
	
	@RequestMapping(value = "salaryProcess/salaryProcessSetup", method = RequestMethod.POST, params="finalize")
	public ModelAndView salaryProcessFinalize(@ModelAttribute("salaryProcessModel") SalaryProcessModel salaryProcessModel, LookupModel

	lookupModel, HttpSession session, final RedirectAttributes redirectAttributes) {
		if (session.getAttribute("logonSuccessYN") == "Y") {

			try {
			SalaryProcessModel oSalaryProcessModel = new SalaryProcessModel();
			
			salaryProcessModel.setUpdateBy((String) session.getAttribute("employeeid"));
			
			oSalaryProcessModel = accountsService.salaryProcessFinalize(salaryProcessModel);
			
			redirectAttributes.addFlashAttribute("mCode", "1111");
			redirectAttributes.addFlashAttribute("message", "Salary Finalized Successfully");
			
			redirectAttributes.addFlashAttribute("salaryProcessModel", salaryProcessModel);
			
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured !!");
				return new ModelAndView("redirect:/accounts/salaryProcess");
			}
			
		} else {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("redirect:/accounts/salaryProcess");
	}
	
	@RequestMapping(value = "supplierInfoView", method = RequestMethod.GET)
	public ModelAndView supplierInfoView(@ModelAttribute SupplierInfo supplierInfo, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {

			String menuId = "M1003";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);

			if (menuInfo.getMenuId().equals(menuId)) {

				ModelAndView mav = new ModelAndView();

				mav.setViewName("supplierInfoView");

				// mav.addObject("genderList", lookupService.genderList());
				// mav.addObject("maritalStatusList",
				// lookupService.maritalStatusList());
				// mav.addObject("religionList", lookupService.religionList());
				// mav.addObject("designationList",
				// lookupService.designationList());

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
	
	
	
	@RequestMapping(value = "getSupplierInfo", method = RequestMethod.POST)
	public ModelAndView getSupplierInfo(@ModelAttribute SupplierInfo supplierInfo, LookupModel lookupModel,
			HttpSession session, final RedirectAttributes redirectAttributes) throws Exception {
		if (session.getAttribute("logonSuccessYN") == "Y") {
			List<SupplierInfo> oSupplierList = accountsService.supplierList(supplierInfo);
			if (oSupplierList.size() > 0) {
				redirectAttributes.addFlashAttribute("supplierList", oSupplierList);
				redirectAttributes.addFlashAttribute("supplierListSize", oSupplierList.size());
				redirectAttributes.addFlashAttribute("supplierInfo", supplierInfo);
			} else {
				redirectAttributes.addFlashAttribute("supplierListNotFound", "No Data Found!");
				redirectAttributes.addFlashAttribute("supplierInfo", supplierInfo);
			}
			redirectAttributes.addFlashAttribute("supplierInfo", supplierInfo);
			return new ModelAndView("redirect:/accounts/supplierInfoView");

		} else {
			return new ModelAndView("redirect:/login");
		}
	}
	
	
	
	@RequestMapping(value = "supplierInfo", method = RequestMethod.GET)
	public ModelAndView supplierInfo(@ModelAttribute SupplierInfo supplierInfo, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {

			String menuId = "M1003";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);

			if (menuInfo.getMenuId().equals(menuId)) {

				ModelAndView mav = new ModelAndView();

				mav.setViewName("supplierInfo");

				// mav.addObject("genderList", lookupService.genderList());
				// mav.addObject("maritalStatusList",
				// lookupService.maritalStatusList());
				// mav.addObject("religionList", lookupService.religionList());
				// mav.addObject("designationList",
				// lookupService.designationList());

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
	

	@RequestMapping(value = "saveSupplier", method = RequestMethod.POST)
	public ModelAndView saveSupplier(@ModelAttribute SupplierInfo supplierInfo, HttpSession session,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {
				supplierInfo.setUpdateBy((String) session.getAttribute("employeeid"));
				SupplierInfo oSupplierInfo = new SupplierInfo();

				oSupplierInfo = accountsService.saveSupplier(supplierInfo);

				redirectAttributes.addFlashAttribute("message", oSupplierInfo.getMessage());
				redirectAttributes.addFlashAttribute("mCode", oSupplierInfo.getmCode());

				redirectAttributes.addFlashAttribute("supplierInfo", supplierInfo);
				return new ModelAndView("redirect:/accounts/supplierInfo");
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured!!");
				return new ModelAndView("redirect:/accounts/supplierInfo");
			}
		} else {
			return new ModelAndView("redirect:/login");
		}
	}
	
	@RequestMapping(value = "getSupplierInfo", method = RequestMethod.GET)
	public ModelAndView getSupplierInfo(@ModelAttribute SupplierInfo supplierInfo, LookupModel lookupModel,
			HttpSession session, HttpServletRequest request, final RedirectAttributes redirectAttributes)
			throws Exception {
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			SupplierInfo oSupplierInfo = new SupplierInfo();
			oSupplierInfo = accountsService.getSupplierInfo(supplierInfo);
			redirectAttributes.addFlashAttribute("supplierInfo", oSupplierInfo);
		}else {
			return new ModelAndView("redirect:/login");
		}

		return new ModelAndView("redirect:/accounts/supplierInfo");
	}
	
	
	
	////////////////// Owner Consumption Info///////////////////////

	@RequestMapping(value = "ownerConsumptionInfo", method = RequestMethod.GET)
	public ModelAndView ownerConsumptionInfo(@ModelAttribute("ownerConsumptionInfo") OwnerConsumptionInfo ownerConsumptionInfo,
			LookupModel lookupModel, Membership membership, HttpSession session, final RedirectAttributes redirectAttributes) {
		
		//System.out.println("sadddddddddd");
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M1003";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			ModelAndView model = new ModelAndView("ownerConsumptionInfo");
			
			lookupModel.setId("2");
			
			model.addObject("ownerList", lookupService.employeeList(lookupModel));
			
			model.addObject("membershipList", membershipService.getMemberList(membership));
			
			model.addObject("itemList", lookupService.itemList(lookupModel));
			
			//model.addObject("message", employeeMonthlyConsumption.getMessage());
			//model.addObject("mCode", employeeMonthlyConsumption.getMessageCode());
			
			
			List<OwnerConsumptionInfo> oOwnerConsumptionInfoList = new ArrayList<OwnerConsumptionInfo>();

			oOwnerConsumptionInfoList = accountsService.getOwnerConsumptionList(ownerConsumptionInfo);

			// System.out.println("oOwnerConsumptionInfoList.size()  " + oOwnerConsumptionInfoList.size());
			
			if(oOwnerConsumptionInfoList.size() >0) {
				model.addObject("ownerConsumptionInfoList", oOwnerConsumptionInfoList);
			} else {
				model.addObject("ownerConsumptionInfoListNotFound", "No Data Found !!");
			}
			
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
	
	
	@RequestMapping(value = "ownerConsumptionInfo/getOwnerConsumptionList", method = RequestMethod.POST)
	public ModelAndView getOwnerConsumptionList(@ModelAttribute("ownerConsumptionInfo") OwnerConsumptionInfo ownerConsumptionInfo, 
			HttpSession session) {
		
		ModelAndView mav = new ModelAndView("adminList");
		
		 //System.out.println("dsadsadsa");

		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {

				List<OwnerConsumptionInfo> oOwnerConsumptionInfoList = new ArrayList<OwnerConsumptionInfo>();

				oOwnerConsumptionInfoList = accountsService.getOwnerConsumptionList(ownerConsumptionInfo);

				// System.out.println("oOwnerConsumptionInfoList.size()  " + oOwnerConsumptionInfoList.size());
				
				if(oOwnerConsumptionInfoList.size() >0) {
					mav.addObject("ownerConsumptionInfoList", oOwnerConsumptionInfoList);
				} else {
					mav.addObject("ownerConsumptionInfoListNotFound", "No Data Found !!");
				}
				
				return mav;
				
			} catch (Exception e) {
				mav.addObject("mCode", "0000");
				mav.addObject("message", "Error occured !!");
				return mav;
			}
		} else {
			return new ModelAndView("redirect:/");
		}

	}
	
	
	@RequestMapping(value = "ownerConsumptionInfo/getOwnerConsumption", method = RequestMethod.GET)
	public ModelAndView getOwnerConsumption(@ModelAttribute("ownerConsumptionInfo") OwnerConsumptionInfo ownerConsumptionInfo, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {

				OwnerConsumptionInfo oOwnerConsumptionInfo = new OwnerConsumptionInfo();

				oOwnerConsumptionInfo = accountsService.getOwnerConsumption(ownerConsumptionInfo);
				
				redirectAttributes.addFlashAttribute("ownerConsumptionInfo", oOwnerConsumptionInfo);
				
				return new ModelAndView("redirect:/accounts/ownerConsumptionInfo");
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured !!");
				return new ModelAndView("redirect:/accounts/ownerConsumptionInfo");
			}
		} else {
			return new ModelAndView("redirect:/");
		}

	}
	
	
	
	@RequestMapping(value = "ownerConsumptionSave", method = RequestMethod.POST)
	public ModelAndView ownerConsumptionSave(@ModelAttribute OwnerConsumptionInfo ownerConsumptionInfo,
			LookupModel lookupModel, HttpSession session, final RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			try {

				ownerConsumptionInfo.setUpdateBy((String) session.getAttribute("employeeid"));
				//System.out.println(" sess  emp id  "+  (String) session.getAttribute("employeeid"));

				OwnerConsumptionInfo oOwnerConsumptionInfo = new OwnerConsumptionInfo();
				
				oOwnerConsumptionInfo = accountsService.ownerConsumptionSave(ownerConsumptionInfo);
				
				if(oOwnerConsumptionInfo.getMessageCode() !=null && oOwnerConsumptionInfo.getMessageCode().equals("1111")) {
					ownerConsumptionInfo.setConsumeDate("");
					ownerConsumptionInfo.setItemId("");
					ownerConsumptionInfo.setQuantity("");
					ownerConsumptionInfo.setRemarks("");
				}

				redirectAttributes.addFlashAttribute("mCode", oOwnerConsumptionInfo.getMessageCode());
				redirectAttributes.addFlashAttribute("message", oOwnerConsumptionInfo.getMessage());
					
				redirectAttributes.addFlashAttribute("ownerConsumptionInfo", ownerConsumptionInfo);
				
				
				List<OwnerConsumptionInfo> oOwnerConsumptionInfoList = accountsService.getOwnerConsumptionList(ownerConsumptionInfo);

				
				if(oOwnerConsumptionInfoList.size() >0) {
					redirectAttributes.addFlashAttribute("ownerConsumptionInfoList", oOwnerConsumptionInfoList);
				} else {
					redirectAttributes.addFlashAttribute("ownerConsumptionInfoListNotFound", "No Data Found !!");
				}
				
				}  catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured in saving data !!");
				redirectAttributes.addFlashAttribute("ownerConsumptionInfo", ownerConsumptionInfo);
			}
			return new ModelAndView("redirect:/accounts/ownerConsumptionInfo");

		} else {
			return new ModelAndView("redirect:/");
		}
		
	}
	
	
	@RequestMapping(value = "ownerConsumptionInfo/finalizeOwnerConsumeInfo", method = RequestMethod.GET)
	public ModelAndView finalizeOwnerConsumeInfo(@ModelAttribute OwnerConsumptionInfo ownerConsumptionInfo,
			LookupModel lookupModel, HttpSession session, final RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			try {

				ownerConsumptionInfo.setUpdateBy((String) session.getAttribute("employeeid"));

				OwnerConsumptionInfo oOwnerConsumptionInfo = new OwnerConsumptionInfo();
				
				oOwnerConsumptionInfo = accountsService.finalizeOwnerConsumeInfo(ownerConsumptionInfo);

				redirectAttributes.addFlashAttribute("mCode", oOwnerConsumptionInfo.getMessageCode());
				redirectAttributes.addFlashAttribute("message", oOwnerConsumptionInfo.getMessage());
				
				}  catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured in finalize action !!");
			}
			return new ModelAndView("redirect:/accounts/ownerConsumptionInfo");

		} else {
			return new ModelAndView("redirect:/");
		}
		
	}
	
	
	
	@RequestMapping(value = "ownerConsumptionHistory", method = RequestMethod.GET)
	public ModelAndView ownerConsumptionHistory(@ModelAttribute("ownerConsumptionInfo") OwnerConsumptionInfo ownerConsumptionInfo,
			LookupModel lookupModel, Membership membership, HttpSession session, final RedirectAttributes redirectAttributes) {
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M1003";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			ModelAndView model = new ModelAndView("ownerConsumptionHistory");
			
			String designationId = ((String) session.getAttribute("designationId"));
			
			System.out.println("designationId " + designationId);
			
			//model.addObject("ownerList", lookupService.employeeList(lookupModel));
			//model.addObject("membershipList", membershipService.getMemberList(membership));
			//model.addObject("itemList", lookupService.itemList(lookupModel));
			
			if(designationId != null && designationId.equals("2")) {
			
				List<OwnerConsumptionInfo> oOwnerConsumptionInfoList = new ArrayList<OwnerConsumptionInfo>();

				oOwnerConsumptionInfoList = accountsService.getOwnerConsumptionHistoryList(ownerConsumptionInfo);
	
				// System.out.println("oOwnerConsumptionInfoList.size()  " + oOwnerConsumptionInfoList.size());
				
				if(oOwnerConsumptionInfoList.size() >0) {
					model.addObject("consumptionHistoryList", oOwnerConsumptionInfoList);
				} else {
					model.addObject("consumptionHistoryListNotFound", "No Data Found !!");
				}
			
			} else {
				model.addObject("consumptionHistoryListNotFound", "No Data Found !!");
			}
			
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
	
	
	
	@RequestMapping(value = "stockProcessView", method = RequestMethod.GET)
	public ModelAndView stockProcessView(@ModelAttribute SupplierInfo supplierInfo, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {

			String menuId = "M1004";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);

			if (menuInfo.getMenuId().equals(menuId)) {

				ModelAndView mav = new ModelAndView();

				mav.setViewName("stockProcessView");
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
	
	
	@RequestMapping(value = "processStock", method = RequestMethod.POST)
	public ModelAndView processStock(@ModelAttribute StockProcess stockProcess,
			 HttpSession session, final RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			try {
				StockProcess oStockProcess = new StockProcess();
				stockProcess.setUpdateBy((String) session.getAttribute("employeeid"));
							
				System.out.println("stockProcess.getProcessDate() = " + stockProcess.getProcessDate());
				System.out.println("stockProcess.getUpdateBy() = " + stockProcess.getUpdateBy());
				
				oStockProcess = accountsService.processStock(stockProcess);

				redirectAttributes.addFlashAttribute("mCode", oStockProcess.getMessageCode());
				redirectAttributes.addFlashAttribute("message", oStockProcess.getMessage());
				
				}  catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured in Stock Proces action !!");
			}
			return new ModelAndView("redirect:/accounts/stockProcessView");

		} else {
			return new ModelAndView("redirect:/");
		}
	}
	

}