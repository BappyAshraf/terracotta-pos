package com.pos.reports.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pos.reports.model.ReportModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

public interface ReportService {

	public ReportModel searchReportDetails(ReportModel reportModel);
	
	public JRDataSource orderFinalizeData(ReportModel reportModel);
	
	public JRDataSource inventoryReportData(ReportModel reportModel);
	
	public JRDataSource posReportData(ReportModel reportModel);
	
	public JRDataSource costAnalysisReportData(ReportModel reportModel);
	
	public String viewPdfReport(String fileName, HttpServletRequest request, HttpServletResponse response,
			Map parameters) throws JRException, IOException, SQLException, ClassNotFoundException, Exception;
	
	public JRDataSource kitchenQTReportData(ReportModel reportModel);
	
	public JRDataSource customerMoneyReceiptData(ReportModel reportModel);
	
	public ReportModel updateItemWiseKitchenQT(ReportModel reportModel);
	
	public ReportModel posReportSumData(ReportModel reportModel);
	
	public ReportModel getShopInfo(ReportModel reportModel);
	
	public JRDataSource stockSummaryReportData(ReportModel reportModel);
	
}
