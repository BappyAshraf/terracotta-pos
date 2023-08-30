package com.pos.inventory.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aes.protection.CipherUtils;
import com.pos.common.RemoveNull;
import com.pos.inventory.model.Inventory;
import com.pos.inventory.service.InventoryService;
import com.pos.login.model.MenuInfo;
import com.pos.login.service.LoginService;
import com.pos.lookup.model.LookupModel;
import com.pos.lookup.service.LookupService;
import com.pos.membership.model.Membership;
import com.pos.pointOfSale.model.PointOfSale;

@Controller
@RequestMapping("inventory")
public class InventoryController {

	CipherUtils oCipherUtils = new CipherUtils();
	
	@Autowired
    private LoginService loginService;
	
	@Autowired
	private LookupService lookupService;
	@Autowired
	private InventoryService inventoryService;
	

	@RequestMapping(value = "createIngredients", method = RequestMethod.GET)
	public ModelAndView createProduct(@ModelAttribute Inventory inventory, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) throws Exception {
		
		String menuId = "M0101";
		MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
				menuId);
		
		if (menuInfo.getMenuId().equals(menuId)) {
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView();
			
			List<Inventory> oIngredientsList = inventoryService.getIngredientsList(inventory);

			// System.out.println("em id "+hrInfo.getEmployeeId());
			// System.out.println("depart id "+hrInfo.getDepartmentId());

			if (oIngredientsList.size() > 0) {
				// System.out.println("size "+oProductList.size());
				mav.addObject("ingredientsList", oIngredientsList);
			} else {
				mav.addObject("ingredientsListNotFound", "No Ingredients Found!");
			}

			mav.setViewName("createIngredients");
			mav.addObject("unitList", lookupService.unitList(lookupModel));
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
	

	@RequestMapping(value = "getIngredientsList", method = RequestMethod.POST)
	public ModelAndView getProductList(@ModelAttribute Inventory inventory, HttpSession session) throws Exception {

		if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView("ingredientsList");
			List<Inventory> oIngredientsList = inventoryService.getIngredientsList(inventory);

			// System.out.println("em id "+hrInfo.getEmployeeId());
			// System.out.println("depart id "+hrInfo.getDepartmentId());

			if (oIngredientsList.size() > 0) {
				// System.out.println("size "+oProductList.size());
				mav.addObject("ingredientsList", oIngredientsList);
			} else {
				mav.addObject("ingredientsListNotFound", "No Ingredients Found!");
			}

			return mav;
		} else {
			return new ModelAndView("redirect:/login");
		}

	}
	

	@RequestMapping(value = "saveIngredients", method = RequestMethod.POST)
	public ModelAndView saveProduct(@ModelAttribute Inventory inventory, HttpSession session,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {
				inventory.setUpdateBy((String) session.getAttribute("employeeid"));
				Inventory oInventory = new Inventory();

				oInventory = inventoryService.saveIngredients(inventory);

				redirectAttributes.addFlashAttribute("message", oInventory.getMessage());
				redirectAttributes.addFlashAttribute("mCode", oInventory.getmCode());

				redirectAttributes.addFlashAttribute("inventory", inventory);
				return new ModelAndView("redirect:/inventory/createIngredients");
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured!!");
				return new ModelAndView("redirect:/inventory/createIngredients");
			}
		} else {
			return new ModelAndView("redirect:/login");
		}
	}
	

	@RequestMapping(value = "storeIngredients", method = RequestMethod.GET)
	public ModelAndView storeProduct(@ModelAttribute("inventory") Inventory inventory, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {

			String menuId = "M0103";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);

			if (menuInfo.getMenuId().equals(menuId)) {

				ModelAndView mav = new ModelAndView();

				mav.setViewName("storeProduct");
				mav.addObject("unitList", lookupService.unitList(lookupModel));
				mav.addObject("productList", lookupService.productList(lookupModel));
				mav.addObject("employeeList", lookupService.employeeList(lookupModel));
				mav.addObject("inventoryTypeList", lookupService.inventoryTypeList(lookupModel));

				List<Inventory> oInventoryList = inventoryService.getInventoryList(inventory);

				if (oInventoryList.size() > 0) {
					mav.addObject("inventoryList", oInventoryList);
				} else {
					mav.addObject("inventoryListNotFound", "No Data Found!");
				}

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
	
	
	@RequestMapping(value = "getProductInfo", method = RequestMethod.GET)
	public ModelAndView getProductInfo(@ModelAttribute("inventory") Inventory inventory, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {
				Inventory oInventory = new Inventory();
				oInventory = inventoryService.getProductInfo(inventory);
				
				if(oInventory.getEncInventoryId() !=null && oInventory.getEncInventoryId().length() >0) {
					redirectAttributes.addFlashAttribute("inventory", oInventory);
				} else {
					redirectAttributes.addFlashAttribute("mCode", "0000");
					redirectAttributes.addFlashAttribute("message", "No Product Info Found !!! ");
				}
				return new ModelAndView("redirect:/inventory/storeIngredients");
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured");
				return new ModelAndView("redirect:/inventory/storeIngredients");
			}
		} else {
			return new ModelAndView("login");

		}
	};
	
	
	@RequestMapping(value = "getStoredIngredientsInfo", method = RequestMethod.GET)
	public ModelAndView getStoredIngredientsInfo(@ModelAttribute("inventory") Inventory inventory, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {
				Inventory oInventory = new Inventory();
				oInventory = inventoryService.getStoredIngredientsInfo(inventory);
				
				redirectAttributes.addFlashAttribute("inventory", oInventory);
				
				return new ModelAndView("redirect:/inventory/storeManagement");
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured");
				return new ModelAndView("redirect:/inventory/storeManagement");
			}
		} else {
			return new ModelAndView("login");

		}
	}
	

	@RequestMapping(value = "saveStoreProduct", method = RequestMethod.POST)
	public @ResponseBody Inventory saveStoreProduct(@ModelAttribute Inventory inventory, LookupModel lookupModel,
			HttpSession session, final RedirectAttributes redirectAttributes) {

		Inventory oInventory = new Inventory();
		if (session.getAttribute("logonSuccessYN") == "Y") {

			if (inventory.getInventoryTypeId() == null || inventory.getInventoryTypeId() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Select Inventory type !!");
				return oInventory;
			} else if (inventory.getProductId() == null || inventory.getProductId() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Select Ingredient Name !!");
				return oInventory;
			} else if (inventory.getUnitId() == null || inventory.getUnitId() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Select Unit !!");
				return oInventory;
				
			} else if (inventory.getUnitPrice() == null || inventory.getUnitPrice() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Enter Unit Price !!");
				return oInventory;
				
			} else if (inventory.getQuantity() == null || inventory.getQuantity() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Enter Quantity !!");
				return oInventory;
			
			} else if (inventory.getPrice() == null || inventory.getPrice() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Enter Price !!");
				return oInventory;
			} else {

				if (inventory.getInventoryTypeId().equals("206")) {
					if (inventory.getSupplierId() == null || inventory.getSupplierId() == "") {
						oInventory.setmCode("0000");
						oInventory.setMessage("Please Select Supplier !!");
						return oInventory;
					}
				} else {
					if (inventory.getEmployeeId() == null || inventory.getEmployeeId() == "") {
						oInventory.setmCode("0000");
						oInventory.setMessage("Please Select Shop By !!");
						return oInventory;
					}
				}

				inventory.setUpdateBy((String) session.getAttribute("employeeid"));
				oInventory = inventoryService.saveStoreProduct(inventory);

			}
		}
		return oInventory;
	}
	
	
	//manage_inventory
	@RequestMapping(value = "updateStoreProduct", method = RequestMethod.POST)
	public ModelAndView  updateStoreProduct(@ModelAttribute Inventory inventory, LookupModel lookupModel,
			HttpSession session, final RedirectAttributes redirectAttributes) {

		Inventory oInventory = new Inventory();
		if (session.getAttribute("logonSuccessYN") == "Y") {

			if (inventory.getInventoryTypeId() == null || inventory.getInventoryTypeId() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Select Inventory type !!");
				redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
				redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
				
			} else if (inventory.getProductId() == null || inventory.getProductId() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Select Ingredient Name !!");
				redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
				redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
				
			} else if (inventory.getUnitId() == null || inventory.getUnitId() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Select Unit !!");
				redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
				redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
				
			} else if (inventory.getUnitPrice() == null || inventory.getUnitPrice() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Enter Unit Price !!");
				redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
				redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
				
			} else if (inventory.getQuantity() == null || inventory.getQuantity() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Enter Quantity !!");
				redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
				redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
			
			} else if (inventory.getPrice() == null || inventory.getPrice() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Enter Price !!");
				redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
				redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
			} else {

				if (inventory.getInventoryTypeId().equals("206")) {
					if (inventory.getSupplierId() == null || inventory.getSupplierId() == "") {
						oInventory.setmCode("0000");
						oInventory.setMessage("Please Select Supplier !!");
						redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
						redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
					}
				} else {
					if (inventory.getEmployeeId() == null || inventory.getEmployeeId() == "") {
						oInventory.setmCode("0000");
						oInventory.setMessage("Please Select Shop By !!");
						redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
						redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
					}
				}

				inventory.setUpdateBy((String) session.getAttribute("employeeid"));
				oInventory = inventoryService.saveStoreProduct(inventory);
				
				if(oInventory.getmCode() !=null && oInventory.getmCode().equals("1111")) {
					inventory = new Inventory();
				}

				redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
				redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
				
				redirectAttributes.addFlashAttribute("inventory",inventory);
			}
		}
		return new ModelAndView("redirect:/inventory/storeIngredients");
	}
	
	//store_management
	@RequestMapping(value = "updateStoreIngredients", method = RequestMethod.POST)
	public ModelAndView  updateStoreIngredients(@ModelAttribute Inventory inventory, LookupModel lookupModel,
			HttpSession session, final RedirectAttributes redirectAttributes) {

		Inventory oInventory = new Inventory();
		if (session.getAttribute("logonSuccessYN") == "Y") {

			if (inventory.getInventoryTypeId() == null || inventory.getInventoryTypeId() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Select Inventory type !!");
				redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
				redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
				
			} else if (inventory.getProductId() == null || inventory.getProductId() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Select Ingredient Name !!");
				redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
				redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
				
			} else if (inventory.getUnitId() == null || inventory.getUnitId() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Select Unit !!");
				redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
				redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
				
			} else if (inventory.getQuantity() == null || inventory.getQuantity() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Enter Quantity !!");
				redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
				redirectAttributes.addFlashAttribute("message",oInventory.getMessage());

			} else {

				inventory.setUpdateBy((String) session.getAttribute("employeeid"));
				oInventory = inventoryService.saveStoreIngredients(inventory);
				
				

				redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
				redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
				
				//redirectAttributes.addFlashAttribute("inventory", inventory);
			}
		}
		return new ModelAndView("redirect:/inventory/storeManagement");
	}
	

	@RequestMapping(value = "getInventoryList", method = RequestMethod.POST)
	public ModelAndView getInventoryList(@ModelAttribute Inventory inventory, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView("inventoryList");
			try {

				// taskTrackingInfo.setEmployeeId((String)
				// session.getAttribute("employeeId"));

				List<Inventory> oInventoryList = inventoryService.getInventoryList(inventory);

				if (oInventoryList.size() > 0) {
					mav.addObject("inventoryList", oInventoryList);
				} else {
					mav.addObject("inventoryListNotFound", "No Data Found!");
				}
				// mav.addObject("projectsList",
				// lookupService.projectsList(lookupModel));
				// mav.addObject("taskTypeList", lookupService.taskTypeList());

				return mav;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return new ModelAndView("redirect:/");
		}
	}
	
	

	@RequestMapping(value = "getInventoryListSupplier", method = RequestMethod.POST)
	public ModelAndView getInventoryListSupplier(@ModelAttribute Inventory inventory, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView("inventoryList");
			try {

				// taskTrackingInfo.setEmployeeId((String)
				// session.getAttribute("employeeId"));

				List<Inventory> oInventoryList = inventoryService.getInventoryListSupplier(inventory);

				if (oInventoryList.size() > 0) {
					mav.addObject("supplierInventoryList", oInventoryList);
				} else {
					mav.addObject("supplierInventoryListNotFound", "No Data Found!");
				}
				// mav.addObject("projectsList",
				// lookupService.projectsList(lookupModel));
				// mav.addObject("taskTypeList", lookupService.taskTypeList());

				return mav;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return new ModelAndView("redirect:/");
		}
	}
	
	

	@RequestMapping(value = "getPriceSum", method = RequestMethod.POST)
	public @ResponseBody Inventory getPriceSum(@ModelAttribute Inventory inventory, LookupModel lookupModel,
			HttpSession session, final RedirectAttributes redirectAttributes) {
		//System.out.println("entrance");
		//System.out.println();
		Inventory oInventory = new Inventory();
		if (session.getAttribute("logonSuccessYN") == "Y") {

			oInventory = inventoryService.getPriceSum(inventory);

		}
		return oInventory;
	}
	

	@RequestMapping(value = "ingredientsView", method = RequestMethod.GET)
	public ModelAndView productView(@ModelAttribute Inventory inventory, HttpSession session, LookupModel lookupModel, 
			final RedirectAttributes redirectAttributes) {
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M0102";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName("productView");
			mav.addObject("unitList", lookupService.unitList(lookupModel));
			mav.addObject("productList", lookupService.productList(lookupModel));
			mav.addObject("employeeList", lookupService.employeeList(lookupModel));
			mav.addObject("inventoryTypeList", lookupService.inventoryTypeList(lookupModel));
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

	/*
	 * @RequestMapping(value = "getTeacherInfoList", method =
	 * RequestMethod.POST) public ModelAndView
	 * getTeacherInfoList(@ModelAttribute Teacher teacher, HttpSession session,
	 * final RedirectAttributes redirectAttributes, HttpServletRequest request)
	 * throws Exception { System.out.println("entrance"); if
	 * (session.getAttribute("logonSuccessYN") == "Y") { // String referrer =
	 * request.getHeader("referer"); // System.out.println("classId " +
	 * student.getClassId()); List<Teacher> oTeacherInfoList =
	 * teacherService.getTeacherInfoList(teacher); System.out.println("list " +
	 * oTeacherInfoList.size()); if (oTeacherInfoList.size() > 0) {
	 * redirectAttributes.addFlashAttribute("teacherInfoList",
	 * oTeacherInfoList);
	 * redirectAttributes.addFlashAttribute("teacherInfoListSize",
	 * oTeacherInfoList.size()); } else {
	 * redirectAttributes.addFlashAttribute("teacherInfoListNotFound",
	 * "No Data Foound !!"); } redirectAttributes.addFlashAttribute("teacher",
	 * teacher); return new ModelAndView("redirect:/teacher/teacherInfoView"); }
	 * else { return new ModelAndView("redirect:/login"); } }
	 */
	@RequestMapping(value = "getProductView", method = RequestMethod.POST)
	public ModelAndView getProductView(@ModelAttribute Inventory inventory, LookupModel lookupModel,
			HttpSession session, final RedirectAttributes redirectAttributes) throws Exception {
		//System.out.println("entrance");
		Inventory oInventory = new Inventory();
		if (session.getAttribute("logonSuccessYN") == "Y") {
			// ModelAndView mav = new ModelAndView("inventoryList");
			//System.out.println("sdsd " + inventory.getInventoryTypeId());
			// oInventory = inventoryService.getProductView(inventory);
			List<Inventory> oProductViewList = inventoryService.getProductView(inventory);
			//System.out.println("size " + oProductViewList.size());
			if (oProductViewList.size() > 0) {

				// mav.addObject("productViewList", oProductViewList);
				redirectAttributes.addFlashAttribute("productViewList", oProductViewList);
				redirectAttributes.addFlashAttribute("productViewListSize", oProductViewList.size());
			} else {
				redirectAttributes.addFlashAttribute("productViewListNotFound", "No Data Found!");
			}
			redirectAttributes.addFlashAttribute("inventory", inventory);
			return new ModelAndView("redirect:/inventory/ingredientsView");

		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	// other Inventory

	@RequestMapping(value = "otherInventory", method = RequestMethod.GET)
	public ModelAndView otherInventory(@ModelAttribute Inventory inventory, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {

		// System.out.println("hello");

		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M0104";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName("otherInventory");
			mav.addObject("unitList", lookupService.unitList(lookupModel));
			mav.addObject("productList", lookupService.productList(lookupModel));
			mav.addObject("supplierList", lookupService.supplierList(lookupModel));
			mav.addObject("inventoryTypeList", lookupService.inventoryTypeListOther(lookupModel));
			
			inventory.setInventoryTypeId("206");
			List<Inventory> oInventoryList = inventoryService.getInventoryListSupplier(inventory);

			if (oInventoryList.size() > 0) {
				mav.addObject("inventoryList", oInventoryList);
			} else {
				mav.addObject("inventoryListNotFound", "No Data Found!");
			}
			
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
	
	
	
	@RequestMapping(value = "getProductInfoSupplier", method = RequestMethod.GET)
	public ModelAndView getProductInfoSupplier(@ModelAttribute("inventory") Inventory inventory, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {
				Inventory oInventory = new Inventory();
				oInventory = inventoryService.getProductInfo(inventory);
				
				if(oInventory.getEncInventoryId() !=null && oInventory.getEncInventoryId().length() >0) {
					redirectAttributes.addFlashAttribute("inventory", oInventory);
				} else {
					redirectAttributes.addFlashAttribute("mCode", "0000");
					redirectAttributes.addFlashAttribute("message", "No Supplier Product Info Found !!! ");
				}
				return new ModelAndView("redirect:/inventory/otherInventory");
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured");
				return new ModelAndView("redirect:/inventory/otherInventory");
			}
		} else {
			return new ModelAndView("login");

		}
	}
	
	
	
	@RequestMapping(value = "updateStoreProductSupplier", method = RequestMethod.POST)
	public ModelAndView  updateStoreProductSupplier(@ModelAttribute Inventory inventory, LookupModel lookupModel,
			HttpSession session, final RedirectAttributes redirectAttributes) {

		Inventory oInventory = new Inventory();
		if (session.getAttribute("logonSuccessYN") == "Y") {

			if (inventory.getInventoryTypeId() == null || inventory.getInventoryTypeId() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Select Inventory type !!");
				redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
				redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
				
			} else if (inventory.getProductId() == null || inventory.getProductId() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Select Ingredient Name !!");
				redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
				redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
				
			} else if (inventory.getUnitId() == null || inventory.getUnitId() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Select Unit !!");
				redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
				redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
				
			} else if (inventory.getUnitPrice() == null || inventory.getUnitPrice() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Enter Unit Price !!");
				redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
				redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
				
			} else if (inventory.getQuantity() == null || inventory.getQuantity() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Enter Quantity !!");
				redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
				redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
			
			} else if (inventory.getPrice() == null || inventory.getPrice() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Enter Price !!");
				redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
				redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
			} else {

				if (inventory.getInventoryTypeId().equals("206")) {
					if (inventory.getSupplierId() == null || inventory.getSupplierId() == "") {
						oInventory.setmCode("0000");
						oInventory.setMessage("Please Select Supplier !!");
						redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
						redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
					}
				} else {
					if (inventory.getEmployeeId() == null || inventory.getEmployeeId() == "") {
						oInventory.setmCode("0000");
						oInventory.setMessage("Please Select Shop By !!");
						redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
						redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
					}
				}

				inventory.setUpdateBy((String) session.getAttribute("employeeid"));
				oInventory = inventoryService.saveStoreProduct(inventory);
				
				if(oInventory.getmCode() !=null && oInventory.getmCode().equals("1111")) {
					inventory = new Inventory();
				}

				redirectAttributes.addFlashAttribute("mCode",oInventory.getmCode());
				redirectAttributes.addFlashAttribute("message",oInventory.getMessage());
				
				redirectAttributes.addFlashAttribute("inventory",inventory);
			}
		}
		return new ModelAndView("redirect:/inventory/otherInventory");
	}
	
	
	

	@RequestMapping(value = "wastageView", method = RequestMethod.GET)
	public ModelAndView wastageView(@ModelAttribute Inventory inventory, HttpSession session, LookupModel lookupModel
			, final RedirectAttributes redirectAttributes) {

		// System.out.println("hello");

		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M0105";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName("wastageView");
			mav.addObject("productList", lookupService.productList(lookupModel));
			mav.addObject("itemList", lookupService.itemList(lookupModel));
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

	@RequestMapping(value = "wastage", method = RequestMethod.GET)
	public ModelAndView wastage(@ModelAttribute Inventory inventory, HttpSession session, LookupModel lookupModel
			, final RedirectAttributes redirectAttributes) {

		// System.out.println("hello");

		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			String menuId = "M0105";
			MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
					menuId);
			
			if (menuInfo.getMenuId().equals(menuId)) {
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName("wastage");
			mav.addObject("productList", lookupService.productList(lookupModel));
			mav.addObject("itemList", lookupService.itemList(lookupModel));
			mav.addObject("unitList", lookupService.unitList(lookupModel));
			mav.addObject("inventoryTypeList", lookupService.inventoryTypeList(lookupModel));
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

	@RequestMapping(value = "saveWastage", method = RequestMethod.POST)
	public ModelAndView saveWastage(@ModelAttribute Inventory inventory, HttpSession session,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (session.getAttribute("logonSuccessYN") == "Y") {
			try {
				inventory.setUpdateBy((String) session.getAttribute("employeeid"));
				Inventory oInventory = new Inventory();

				if ((inventory.getProductId() == "" || inventory.getProductId() == null)
						&& (inventory.getItemId() == "" || inventory.getItemId() == null)) {
					redirectAttributes.addFlashAttribute("message", "Ingredient or Item Must Be Selected !");
					redirectAttributes.addFlashAttribute("mCode", "0000");
				} else if (inventory.getQuantity() == "" || inventory.getQuantity() == null) {
					redirectAttributes.addFlashAttribute("message", "Please Enter Quantity !");
					redirectAttributes.addFlashAttribute("mCode", "0000");
				} else if (inventory.getPrice() == "" || inventory.getPrice() == null) {
					redirectAttributes.addFlashAttribute("message", "Please Enter Price !");
					redirectAttributes.addFlashAttribute("mCode", "0000");
				} else if (inventory.getInventoryTypeId() == "" || inventory.getInventoryTypeId() == null) {
					redirectAttributes.addFlashAttribute("message", "Please Select Inventory Type First !");
					redirectAttributes.addFlashAttribute("mCode", "0000");
				} else {

					oInventory = inventoryService.saveWastage(inventory);

					redirectAttributes.addFlashAttribute("message", oInventory.getMessage());
					redirectAttributes.addFlashAttribute("mCode", oInventory.getmCode());

					//redirectAttributes.addFlashAttribute("inventory", inventory);
					return new ModelAndView("redirect:/inventory/wastage");
				}
				redirectAttributes.addFlashAttribute("inventory", inventory);
				return new ModelAndView("redirect:/inventory/wastage");
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured!!");
				return new ModelAndView("redirect:/inventory/wastage");
			}
		} else {
			return new ModelAndView("redirect:/login");
		}
	}
	
	@RequestMapping(value = "getWastageView", method = RequestMethod.POST)
	public ModelAndView getWastageView(@ModelAttribute Inventory inventory, LookupModel lookupModel,
			HttpSession session, final RedirectAttributes redirectAttributes) throws Exception {
		//System.out.println("entrance");
		if (session.getAttribute("logonSuccessYN") == "Y") {
			List<Inventory> oWastageViewList = inventoryService.getWastageView(inventory);
			if (oWastageViewList.size() > 0) {
				redirectAttributes.addFlashAttribute("wastageViewList", oWastageViewList);
				redirectAttributes.addFlashAttribute("oWastageViewListSize", oWastageViewList.size());
				redirectAttributes.addFlashAttribute("inventory", inventory);
			} else {
				redirectAttributes.addFlashAttribute("wastageViewListNotFound", "No Data Found!");
			}
			redirectAttributes.addFlashAttribute("inventory", inventory);
			return new ModelAndView("redirect:/inventory/wastageView");

		} else {
			return new ModelAndView("redirect:/login");
		}
	}
	
	@RequestMapping(value = "getWastageInfo", method = RequestMethod.GET)
	public ModelAndView getWastageInfo(@ModelAttribute Inventory inventory, LookupModel lookupModel,
			HttpSession session, HttpServletRequest request, final RedirectAttributes redirectAttributes)
			throws Exception {
		if (session.getAttribute("logonSuccessYN") == "Y") {
			
			Inventory oInventory = new Inventory();
			oInventory = inventoryService.getWastageInfo(inventory);
			redirectAttributes.addFlashAttribute("inventory", oInventory);
		}else {
			return new ModelAndView("redirect:/login");
		}

		return new ModelAndView("redirect:/inventory/wastage");
	}
	
	@RequestMapping(value = "storeManagementView", method = RequestMethod.GET)
	public ModelAndView storeManagementView(@ModelAttribute Inventory inventory, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {
		
		String menuId = "M0106";
		MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
				menuId);
		
		if (menuInfo.getMenuId().equals(menuId)) {
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView();

			mav.setViewName("storeManagementView");
			mav.addObject("unitList", lookupService.unitList(lookupModel));
			mav.addObject("productList", lookupService.productList(lookupModel));
			mav.addObject("employeeList", lookupService.employeeList(lookupModel));
			mav.addObject("inventoryTypeList", lookupService.inventoryTypeList(lookupModel));
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
	
	@RequestMapping(value = "storeManagement", method = RequestMethod.GET)
	public ModelAndView storeManagement(@ModelAttribute Inventory inventory, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {
		
		String menuId = "M0106";
		MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
				menuId);
		
		if (menuInfo.getMenuId().equals(menuId)) {
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView();

			mav.setViewName("storeManagement");
			mav.addObject("unitList", lookupService.unitList(lookupModel));
			mav.addObject("productList", lookupService.productList(lookupModel));
			mav.addObject("employeeList", lookupService.employeeList(lookupModel));
			mav.addObject("inventoryTypeList", lookupService.inventoryTypeList(lookupModel));
			
				List<Inventory> oStoreInventoryList = inventoryService.getStoreInventoryList(inventory);
				//System.out.println("oStoreInventoryList " + oStoreInventoryList.size());
				if (oStoreInventoryList.size() > 0) {
					mav.addObject("storeInventoryList", oStoreInventoryList);
				} else {
					mav.addObject("storeInventoryListNotFound", "No Data Found!");
				}
			
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
	
	@RequestMapping(value = "saveStoreIngredients", method = RequestMethod.POST)
	public @ResponseBody Inventory saveStoreIngredients(@ModelAttribute Inventory inventory, LookupModel lookupModel,
			HttpSession session, final RedirectAttributes redirectAttributes) {

		Inventory oInventory = new Inventory();
		if (session.getAttribute("logonSuccessYN") == "Y") {

			if (inventory.getInventoryTypeId() == null || inventory.getInventoryTypeId() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Select Inventory type !!");
				return oInventory;
			} else if (inventory.getProductId() == null || inventory.getProductId() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Select Ingredient Name !!");
				return oInventory;
			} else if (inventory.getUnitId() == null || inventory.getUnitId() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Select Unit !!");
				return oInventory;
				
			} else if (inventory.getQuantity() == null || inventory.getQuantity() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Enter Quantity !!");
				return oInventory;

			} else {

				if (inventory.getInventoryTypeId().equals("206")) {
					if (inventory.getSupplierId() == null || inventory.getSupplierId() == "") {
						oInventory.setmCode("0000");
						oInventory.setMessage("Please Select Supplier !!");
						return oInventory;
					}
				}

				inventory.setUpdateBy((String) session.getAttribute("employeeid"));
				oInventory = inventoryService.saveStoreIngredients(inventory);

			}
		}
		return oInventory;
	}
	
	@RequestMapping(value = "getStoreInventoryList", method = RequestMethod.POST)
	public ModelAndView getStoreInventoryList(@ModelAttribute Inventory inventory, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView("inventoryList");
			try {

				// taskTrackingInfo.setEmployeeId((String)
				// session.getAttribute("employeeId"));

				List<Inventory> oStoreInventoryList = inventoryService.getStoreInventoryList(inventory);
				System.out.println("oStoreInventoryList " + oStoreInventoryList.size());
				if (oStoreInventoryList.size() > 0) {
					mav.addObject("storeInventoryList", oStoreInventoryList);
				} else {
					mav.addObject("storeInventoryListNotFound", "No Data Found!");
				}
				// mav.addObject("projectsList",
				// lookupService.projectsList(lookupModel));
				// mav.addObject("taskTypeList", lookupService.taskTypeList());

				return mav;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return new ModelAndView("redirect:/");
		}
	}
	
	@RequestMapping(value = "getStoreHistoryList", method = RequestMethod.POST)
	public ModelAndView getStoreHistoryList(@ModelAttribute Inventory inventory, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView("inventoryList");
			try {

				// taskTrackingInfo.setEmployeeId((String)
				// session.getAttribute("employeeId"));

				List<Inventory> oStoreHistoryList = inventoryService.getStoreHistoryList(inventory);
				System.out.println("oStoreHistoryList " + oStoreHistoryList.size());
				if (oStoreHistoryList.size() > 0) {
					mav.addObject("storeHistoryList", oStoreHistoryList);
				} else {
					mav.addObject("storeHistoryListNotFound", "No Data Found!");
				}
				// mav.addObject("projectsList",
				// lookupService.projectsList(lookupModel));
				// mav.addObject("taskTypeList", lookupService.taskTypeList());

				return mav;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return new ModelAndView("redirect:/");
		}
	}
	
	
/*	@RequestMapping(value = "kitchenManagementView", method = RequestMethod.GET)
	public ModelAndView kitchenManagementView(@ModelAttribute Inventory inventory, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {
		
		String menuId = "M0107";
		MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
				menuId);
		
		if (menuInfo.getMenuId().equals(menuId)) {
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView();

			mav.setViewName("kitchenManagementView");
			mav.addObject("unitList", lookupService.unitList(lookupModel));
			mav.addObject("productList", lookupService.productList(lookupModel));
			mav.addObject("employeeList", lookupService.employeeList(lookupModel));
			mav.addObject("inventoryTypeList", lookupService.inventoryTypeList(lookupModel));
			return mav;
			
		  } else {
				redirectAttributes.addFlashAttribute("message", "You are not authorized for this Page !");
				redirectAttributes.addFlashAttribute("mCode", "0000");
				return new ModelAndView("redirect:/");
			}
		} else {
			return new ModelAndView("login");

		}
	}*/
/*	
	@RequestMapping(value = "kitchenManagement", method = RequestMethod.GET)
	public ModelAndView kitchenManagement(@ModelAttribute Inventory inventory, HttpSession session,
			LookupModel lookupModel, final RedirectAttributes redirectAttributes) {
		
		String menuId = "M0107";
		MenuInfo menuInfo = loginService.checkUserAuthorization((String) session.getAttribute("employeeid"),
				menuId);
		
		if (menuInfo.getMenuId().equals(menuId)) {
		
		if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView();

			mav.setViewName("kitchenManagement");
			mav.addObject("unitList", lookupService.unitList(lookupModel));
			mav.addObject("productList", lookupService.productList(lookupModel));
			mav.addObject("employeeList", lookupService.employeeList(lookupModel));
			mav.addObject("inventoryTypeList", lookupService.inventoryTypeList(lookupModel));
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
*/	
/*	
	@RequestMapping(value = "saveKitchenIngredients", method = RequestMethod.POST)
	public @ResponseBody Inventory saveKitchenIngredients(@ModelAttribute Inventory inventory, LookupModel lookupModel,
			HttpSession session, final RedirectAttributes redirectAttributes) {

		Inventory oInventory = new Inventory();
		if (session.getAttribute("logonSuccessYN") == "Y") {

			if (inventory.getInventoryTypeId() == null || inventory.getInventoryTypeId() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Select Inventory type !!");
				return oInventory;
			} else if (inventory.getProductId() == null || inventory.getProductId() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Select Ingredient Name !!");
				return oInventory;
			} else if (inventory.getUnitId() == null || inventory.getUnitId() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Select Unit !!");
				return oInventory;
				
			} else if (inventory.getUnitPrice() == null || inventory.getUnitPrice() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Enter Unit Price !!");
				return oInventory;
				
			} else if (inventory.getQuantity() == null || inventory.getQuantity() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Enter Quantity !!");
				return oInventory;
			
			} else if (inventory.getPrice() == null || inventory.getPrice() == "") {
				oInventory.setmCode("0000");
				oInventory.setMessage("Please Enter Price !!");
				return oInventory;
			} else {

				if (inventory.getInventoryTypeId().equals("206")) {
					if (inventory.getSupplierId() == null || inventory.getSupplierId() == "") {
						oInventory.setmCode("0000");
						oInventory.setMessage("Please Select Supplier !!");
						return oInventory;
					}
				} else {
					if (inventory.getEmployeeId() == null || inventory.getEmployeeId() == "") {
						oInventory.setmCode("0000");
						oInventory.setMessage("Please Select Shop By !!");
						return oInventory;
					}
				}

				inventory.setUpdateBy((String) session.getAttribute("employeeid"));
				oInventory = inventoryService.saveKitchenIngredients(inventory);

			}
		}
		return oInventory;
	}
*/
}