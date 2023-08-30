package com.pos.reports.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pos.reports.dao.ReportDao;
import com.pos.reports.model.ReportModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

@Service("reportService")
public class ReportServiceImpl implements ReportService{

	@Autowired
	private ReportDao reportDao;

	public ReportModel searchReportDetails(ReportModel reportModel) {
		return reportDao.searchReportDetails(reportModel);
	}

	public JRDataSource orderFinalizeData(ReportModel reportModel) {
		return reportDao.orderFinalizeData(reportModel);
	}
	
	public JRDataSource inventoryReportData(ReportModel reportModel) {
		return reportDao.inventoryReportData(reportModel);
	}
	public JRDataSource posReportData(ReportModel reportModel) {
		return reportDao.posReportData(reportModel);
	}

	public String viewPdfReport(String fileName, HttpServletRequest request, HttpServletResponse response,
			Map parameters) throws JRException, IOException, SQLException, ClassNotFoundException, Exception {
		return reportDao.viewPdfReport(fileName, request, response, parameters);
	}

	public JRDataSource costAnalysisReportData(ReportModel reportModel) {
		return reportDao.costAnalysisReportData(reportModel);
	}

	public JRDataSource kitchenQTReportData(ReportModel reportModel) {
		return reportDao.kitchenQTReportData(reportModel);
	}
	public JRDataSource customerMoneyReceiptData(ReportModel reportModel) {
		return reportDao.customerMoneyReceiptData(reportModel);
	}

	public ReportModel updateItemWiseKitchenQT(ReportModel reportModel) {
		return reportDao.updateItemWiseKitchenQT(reportModel);
	}

	public ReportModel posReportSumData(ReportModel reportModel) {
		return reportDao.posReportSumData(reportModel);
	}

	public ReportModel getShopInfo(ReportModel reportModel) {
		return reportDao.getShopInfo(reportModel);
	}
	
	public JRDataSource stockSummaryReportData(ReportModel reportModel) {
		return reportDao.stockSummaryReportData(reportModel);
	}
	
}
