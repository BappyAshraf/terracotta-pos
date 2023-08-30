package com.pos.reports.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsXlsView;

import com.aes.protection.CipherUtils;
import com.pos.accounts.model.SalaryProcessModel;
import com.pos.lookup.model.LookupModel;
import com.pos.lookup.service.LookupService;
import com.pos.pointOfSale.model.PointOfSale;
import com.pos.reports.model.ReportModel;
import com.pos.reports.service.ReportService;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

@Controller
@RequestMapping("reports")
public class ReportController {

	CipherUtils oCipherUtils = new CipherUtils();
	@Autowired
	private LookupService lookupService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private ApplicationContext appContext;
	
		
	@RequestMapping(value = "stockSummaryReport", method = RequestMethod.GET)
	public ModelAndView stockReport(HttpSession session) {
		// System.out.println("hello");
		if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("stockSummaryReport");
			return mav;
		} else {
			return new ModelAndView("login");
		}
	}

	@RequestMapping(value = "inventoryReport", method = RequestMethod.GET)
	public ModelAndView inventoryReport(@ModelAttribute PointOfSale pointOfSale, HttpSession session,
			LookupModel lookupModel) {
		// System.out.println("hello");
		if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView();

			mav.setViewName("inventoryReport");
			mav.addObject("unitList", lookupService.unitList(lookupModel));
			mav.addObject("productList", lookupService.productList(lookupModel));
			mav.addObject("employeeList", lookupService.employeeList(lookupModel));
			mav.addObject("inventoryTypeList", lookupService.inventoryTypeList(lookupModel));
			mav.addObject("itemList", lookupService.itemList(lookupModel));
			return mav;

		} else {
			return new ModelAndView("login");

		}
	}

	@RequestMapping(value = "posReport", method = RequestMethod.GET)
	public ModelAndView posReport(@ModelAttribute PointOfSale pointOfSale, HttpSession session,
			LookupModel lookupModel) {
		// System.out.println("hello");
		if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView();

			mav.setViewName("posReport");
			mav.addObject("itemList", lookupService.itemList(lookupModel));
			return mav;

		} else {
			return new ModelAndView("login");

		}
	}

	@RequestMapping(value = "employeeReport", method = RequestMethod.GET)
	public ModelAndView employeeReport(@ModelAttribute PointOfSale pointOfSale, HttpSession session,
			LookupModel lookupModel) {
		// System.out.println("hello");
		if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView();

			mav.setViewName("employeeReport");
			return mav;

		} else {
			return new ModelAndView("login");

		}
	}
	
	@RequestMapping(value = "costAnalysisReport", method = RequestMethod.GET)
	public ModelAndView costAnalysisReport(@ModelAttribute PointOfSale pointOfSale, HttpSession session,
			LookupModel lookupModel) {
		// System.out.println("hello");
		if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView();

			mav.setViewName("costAnalysisReport");
			return mav;

		} else {
			return new ModelAndView("login");

		}
	}
	
	
	@RequestMapping(value = "payrollReport", method = RequestMethod.GET)
	public ModelAndView payrollReport(@ModelAttribute SalaryProcessModel salaryProcessModel, HttpSession session,
			LookupModel lookupModel) {
		// System.out.println("hello");
		if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView();

			mav.setViewName("payrollReport");
			return mav;

		} else {
			return new ModelAndView("login");

		}
	}
	
	@RequestMapping(value = "printKitchenQT", method = RequestMethod.GET)
	public ModelAndView printKitchenQT(ModelAndView modelAndView, @ModelAttribute("reportModel") ReportModel reportModel,
			BindingResult result, HttpSession session, HttpServletRequest request, HttpServletResponse response,
			final RedirectAttributes redirectAttributes)
			throws ClassNotFoundException, JRException, IOException, SQLException, Exception {

		JasperReportsPdfView pdf = new JasperReportsPdfView();
		JasperReportsXlsView xls = new JasperReportsXlsView();
		Map<String, Object> params = new HashMap<String, Object>();
		ReportModel oReportDetails = new ReportModel();

		//System.out.println("order id " + reportModel.getEncOrderId());

		// reportModel.setReportCode("101");
		
		if (reportModel.getReportCode().equals("501")) {
			reportModel.setViewType("1");
			oReportDetails = reportService.searchReportDetails(reportModel);
			JRDataSource dataSource = reportService.kitchenQTReportData(reportModel);
			// params.put("advanceGivenByName",
			// reportModel.getAdvanceGivenByName());
			/*params.put("fromDate", reportModel.getFromDate());
			params.put("toDate", reportModel.getToDate());*/
			params.put("dataSource", dataSource);
		}
		else {
			return null;
		}
		ReportModel oReportModel = new ReportModel();
		oReportModel = reportService.updateItemWiseKitchenQT(reportModel);

		if (reportModel.getViewType().equals("1")) {
			pdf.setReportDataKey("dataSource");
			pdf.setUrl(oReportDetails.getJesperName());
			pdf.setApplicationContext(appContext);
			return new ModelAndView(pdf, params);

		} else if (reportModel.getViewType().equals("2")) {
			xls.setReportDataKey("dataSource");
			xls.setUrl(oReportDetails.getJesperName());
			xls.setApplicationContext(appContext);
			return new ModelAndView(xls, params);
		} else if (reportModel.getViewType().equals("3")) {

			reportService.viewPdfReport(oReportDetails.getJesperName(), request, response, params);
			// reportService.viewPdfReport(oReportDetails.getJesperName(),
			// request, response, params);

			// modelAndView.setViewName("reports//complain//dailyFault.jasper");
			return modelAndView;
		} else if (reportModel.getViewType().equals("4")) {

			// reportService.viewExcelReport(oReportDetails.getJesperName(),
			// request, response, params);

			return new ModelAndView(xls, params);
		} else {
			return null;
		}
	}
	

	@RequestMapping(value = "cashReceipt", method = RequestMethod.GET)
	public ModelAndView cashReceipt(ModelAndView modelAndView, @ModelAttribute("reportModel") ReportModel reportModel,
			BindingResult result, HttpSession session, HttpServletRequest request, HttpServletResponse response,
			final RedirectAttributes redirectAttributes)
			throws ClassNotFoundException, JRException, IOException, SQLException, Exception {

		JasperReportsPdfView pdf = new JasperReportsPdfView();
		JasperReportsXlsView xls = new JasperReportsXlsView();
		Map<String, Object> params = new HashMap<String, Object>();
		ReportModel oReportDetails = new ReportModel();

		//System.out.println("order id " + reportModel.getEncOrderId());

		// reportModel.setReportCode("101");
		
		reportModel.setViewType("1");
		oReportDetails = reportService.searchReportDetails(reportModel);
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/reports/posReport/");
		String reportPath = path.replace("\\", "/");

		//System.out.println("SUBREPORT_DIR " + reportPath + "/");
		
		params.put("SUBREPORT_DIR", reportPath + "/");
		
		//System.out.println("order Id " + oCipherUtils.decrypt(reportModel.getEncOrderId()));
		
		params.put("encOrderId", oCipherUtils.decrypt(reportModel.getEncOrderId()));
		
		// params.put("dataSource", dataSource);
		reportModel.setViewType("3");
		
		
		/*if (reportModel.getReportCode().equals("101")) {
			reportModel.setViewType("1");
			oReportDetails = reportService.searchReportDetails(reportModel);
			JRDataSource dataSource = reportService.orderFinalizeData(reportModel);
			// params.put("advanceGivenByName",
			// reportModel.getAdvanceGivenByName());
			params.put("dataSource", dataSource);

		} else {
			return null;
		}*/

		if (reportModel.getViewType().equals("1")) {
			pdf.setReportDataKey("dataSource");
			pdf.setUrl(oReportDetails.getJesperName());
			pdf.setApplicationContext(appContext);
			return new ModelAndView(pdf, params);

		} else if (reportModel.getViewType().equals("2")) {
			xls.setReportDataKey("dataSource");
			xls.setUrl(oReportDetails.getJesperName());
			xls.setApplicationContext(appContext);
			return new ModelAndView(xls, params);
		} else if (reportModel.getViewType().equals("3")) {

			reportService.viewPdfReport(oReportDetails.getJesperName(), request, response, params);
			// reportService.viewPdfReport(oReportDetails.getJesperName(),
			// request, response, params);

			// modelAndView.setViewName("reports//complain//dailyFault.jasper");
			return modelAndView;
		} else if (reportModel.getViewType().equals("4")) {

			// reportService.viewExcelReport(oReportDetails.getJesperName(),
			// request, response, params);

			return new ModelAndView(xls, params);
		} else {
			return null;
		}
	};
	
	@RequestMapping(value = "customerMoneyReceipt", method = RequestMethod.GET)
	public ModelAndView customerMoneyReceipt(ModelAndView modelAndView, @ModelAttribute("reportModel") ReportModel reportModel,
			BindingResult result, HttpSession session, HttpServletRequest request, HttpServletResponse response,
			final RedirectAttributes redirectAttributes)
			throws ClassNotFoundException, JRException, IOException, SQLException, Exception {

		JasperReportsPdfView pdf = new JasperReportsPdfView();
		JasperReportsXlsView xls = new JasperReportsXlsView();
		Map<String, Object> params = new HashMap<String, Object>();
		ReportModel oReportDetails = new ReportModel();
		ReportModel oReport = new ReportModel();
		reportModel.setEmployeeId((String)session.getAttribute("employeeid"));
		System.out.println("EmployeeId " + oReport.getEmployeeId());

		// reportModel.setReportCode("101");
		
	if (reportModel.getReportCode().equals("103")) {
		reportModel.setViewType("1");
		oReportDetails = reportService.searchReportDetails(reportModel);
		oReport = reportService.getShopInfo(reportModel);
		JRDataSource dataSource = reportService.customerMoneyReceiptData(reportModel);

		params.put("shopName", oReport.getShopName());
		params.put("shopAddress", oReport.getShopAddress());
		params.put("phoneNo", oReport.getPhoneNo());
		params.put("email", oReport.getEmail());
		
		params.put("dataSource", dataSource);
		
	} 	else if (reportModel.getReportCode().equals("104")) {
		reportModel.setViewType("1");
		oReportDetails = reportService.searchReportDetails(reportModel);
		oReport = reportService.getShopInfo(reportModel);
		JRDataSource dataSource = reportService.customerMoneyReceiptData(reportModel);


		params.put("shopName", oReport.getShopName());
		params.put("shopAddress", oReport.getShopAddress());
		params.put("phoneNo", oReport.getPhoneNo());
		params.put("email", oReport.getEmail());


		params.put("dataSource", dataSource);
		
	}	else {
		return null;
	}
		if (reportModel.getViewType().equals("1")) {
			pdf.setReportDataKey("dataSource");
			pdf.setUrl(oReportDetails.getJesperName());
			pdf.setApplicationContext(appContext);
			return new ModelAndView(pdf, params);

		} else if (reportModel.getViewType().equals("2")) {
			xls.setReportDataKey("dataSource");
			xls.setUrl(oReportDetails.getJesperName());
			xls.setApplicationContext(appContext);
			return new ModelAndView(xls, params);
		} else if (reportModel.getViewType().equals("3")) {

			reportService.viewPdfReport(oReportDetails.getJesperName(), request, response, params);
			// reportService.viewPdfReport(oReportDetails.getJesperName(),
			// request, response, params);

			// modelAndView.setViewName("reports//complain//dailyFault.jasper");
			return modelAndView;
		} else if (reportModel.getViewType().equals("4")) {

			// reportService.viewExcelReport(oReportDetails.getJesperName(),
			// request, response, params);

			return new ModelAndView(xls, params);
		} else {
			return null;
		}
	}

	@RequestMapping(value = "viewreport", method = RequestMethod.POST)
	public ModelAndView viewreport(ModelAndView modelAndView, @ModelAttribute("reportModel") ReportModel reportModel,
			BindingResult result, HttpSession session, HttpServletRequest request, HttpServletResponse response,
			final RedirectAttributes redirectAttributes)
			throws ClassNotFoundException, JRException, IOException, SQLException, Exception {


		JasperReportsPdfView pdf = new JasperReportsPdfView();
		JasperReportsXlsView xls = new JasperReportsXlsView();
		Map<String, Object> params = new HashMap<String, Object>();
		ReportModel oReportDetails = new ReportModel();
		ReportModel oReport = new ReportModel();
		reportModel.setEmployeeId((String)session.getAttribute("employeeid"));
		System.out.println("EmployeeId " + oReport.getEmployeeId());

		//System.out.println("ReportCode " + reportModel.getReportCode());


		if (reportModel.getReportCode().equals("201")) {
			reportModel.setViewType("1");
			oReportDetails = reportService.searchReportDetails(reportModel);
			//oReport = reportService.getShopInfo(reportModel);
			JRDataSource dataSource = reportService.inventoryReportData(reportModel);
			// params.put("advanceGivenByName",
			// reportModel.getAdvanceGivenByName());
			params.put("fromDate", reportModel.getFromDate());
			params.put("toDate", reportModel.getToDate());
			params.put("dataSource", dataSource);

		} else if (reportModel.getReportCode().equals("101")) {
			//reportModel.setViewType("1");
			oReportDetails = reportService.searchReportDetails(reportModel);
			String path = request.getSession().getServletContext().getRealPath("/WEB-INF/reports/posReport/");
			String reportPath = path.replace("\\", "/");
			
			System.out.println("SUBREPORT_DIR " + reportPath + "/");
			params.put("SUBREPORT_DIR", reportPath + "/");
			
			//System.out.println("order Id " + oCipherUtils.decrypt(reportModel.getEncOrderId()));
			
			params.put("encOrderId", oCipherUtils.decrypt(reportModel.getEncOrderId()));
			// params.put("dataSource", dataSource);
			reportModel.setViewType("3");

		} else if (reportModel.getReportCode().equals("102")) {
			reportModel.setViewType("1");
			oReportDetails = reportService.searchReportDetails(reportModel);
			oReport = reportService.getShopInfo(reportModel);
			JRDataSource dataSource = reportService.posReportData(reportModel);
			// params.put("advanceGivenByName",
			// reportModel.getAdvanceGivenByName());
			

			//System.out.println("item id " + reportModel.getItemId());
			ReportModel oReportModel = new ReportModel();
			oReportModel = reportService.posReportSumData(reportModel);
			
			System.out.println("CashPayAmount " + oReportModel.getCashPaySum());
			System.out.println("CardPayAmount " + oReportModel.getCardPaySum());
			System.out.println("bkashPayAmount " + oReportModel.getBkashPaySum());
			
			
			params.put("shopName", oReport.getShopName());
			params.put("shopAddress", oReport.getShopAddress());
			params.put("phoneNo", oReport.getPhoneNo());
			params.put("email", oReport.getEmail());
			
			
			params.put("orderId", oReportModel.getOrderId());
			params.put("discountSum", oReportModel.getDiscountSum());
			params.put("bkashPaySum", oReportModel.getBkashPaySum());
			params.put("cashPaySum", oReportModel.getCashPaySum());
			params.put("cardPaySum", oReportModel.getCardPaySum());
			
			params.put("fromDate", reportModel.getFromDate());
			params.put("toDate", reportModel.getToDate());
			params.put("dataSource", dataSource);
			
		} else if (reportModel.getReportCode().equals("103")) {
			reportModel.setViewType("1");
			oReportDetails = reportService.searchReportDetails(reportModel);
			JRDataSource dataSource = reportService.customerMoneyReceiptData(reportModel);
			// params.put("advanceGivenByName",
			// reportModel.getAdvanceGivenByName());
			
			//System.out.println("from date " + reportModel.getFromDate());
			//System.out.println("to date " + reportModel.getToDate());
			
			//params.put("fromDate", reportModel.getFromDate());
			//params.put("toDate", reportModel.getToDate());
			params.put("dataSource", dataSource);
			
		} else if (reportModel.getReportCode().equals("301")) {
			reportModel.setViewType("1");
			oReportDetails = reportService.searchReportDetails(reportModel);
			JRDataSource dataSource = reportService.costAnalysisReportData(reportModel);
			//JRDataSource dataSource = reportService.posReportData(reportModel);
			
			//System.out.println("from date " + reportModel.getFromDate());
			//System.out.println("to date " + reportModel.getToDate());
			
			params.put("fromDate", reportModel.getFromDate());
			params.put("toDate", reportModel.getToDate());
			params.put("dataSource", dataSource);
		}  else if (reportModel.getReportCode().equals("401")) {
			
			oReportDetails = reportService.searchReportDetails(reportModel);
			String path = request.getSession().getServletContext().getRealPath("/WEB-INF/reports/payrollReport/");
			String reportPath = path.replace("\\", "/");
			
			System.out.println("SUBREPORT_DIR " + reportPath + "/");
			
			//params.put("SUBREPORT_DIR", reportPath + "/");
			
			System.out.println("year  " + reportModel.getYear());
			System.out.println("month  " + reportModel.getMonth());
			
			params.put("year", reportModel.getYear());
			params.put("month", reportModel.getMonth());
			
			reportModel.setViewType("3");
		} else if (reportModel.getReportCode().equals("601")) {
			reportModel.setViewType("1");
			oReport = reportService.getShopInfo(reportModel);
			oReportDetails = reportService.searchReportDetails(reportModel);
			
			System.out.println("oReportDetails.getJesperName() " + oReportDetails.getJesperName());
			
			JRDataSource dataSource = reportService.stockSummaryReportData(reportModel);
			//JRDataSource dataSource = reportService.posReportData(reportModel);
			
			//System.out.println("from date " + reportModel.getFromDate());
			//System.out.println("to date " + reportModel.getToDate());
			
			params.put("shopName", oReport.getShopName());
			params.put("shopAddress", oReport.getShopAddress());
			params.put("phoneNo", oReport.getPhoneNo());
			params.put("email", oReport.getEmail());
			
			params.put("fromDate", reportModel.getFromDate());
			//params.put("toDate", reportModel.getToDate());
			params.put("dataSource", dataSource);
		}
		else {
			return null;
		}

		if (reportModel.getViewType().equals("1")) {
			pdf.setReportDataKey("dataSource");
			pdf.setUrl(oReportDetails.getJesperName());
			pdf.setApplicationContext(appContext);
			return new ModelAndView(pdf, params);

		} else if (reportModel.getViewType().equals("2")) {
			xls.setReportDataKey("dataSource");
			xls.setUrl(oReportDetails.getJesperName());
			xls.setApplicationContext(appContext);
			return new ModelAndView(xls, params);
		} else if (reportModel.getViewType().equals("3")) {

			reportService.viewPdfReport(oReportDetails.getJesperName(), request, response, params);
			// request, response, params);
			// modelAndView.setViewName("reports//complain//dailyFault.jasper");
			return modelAndView;
		} else if (reportModel.getViewType().equals("4")) {

			// reportService.viewExcelReport(oReportDetails.getJesperName(),
			// request, response, params);

			return new ModelAndView(xls, params);
		} else {
			return null;
		}
	}
}
