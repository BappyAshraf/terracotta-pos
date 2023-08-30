package com.pos.pointOfSale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.pointOfSale.dao.PointOfSaleDao;
import com.pos.pointOfSale.model.PointOfSale;


@Service("pointOfSaleService")
public class PointOfSaleServiceImpl implements PointOfSaleService {

	@Autowired
	private PointOfSaleDao pointOfSaleDao;

	public PointOfSale saveItem(PointOfSale pointOfSale) {
		return pointOfSaleDao.saveItem(pointOfSale);
	}

	public List<PointOfSale> getItemList(PointOfSale pointOfSale) throws Exception {
		return pointOfSaleDao.getItemList(pointOfSale);
	}

	public PointOfSale saveItemConfig(PointOfSale pointOfSale) {
		return pointOfSaleDao.saveItemConfig(pointOfSale);
	}

	public List<PointOfSale> getItemConfigList(PointOfSale pointOfSale) {
		return pointOfSaleDao.getItemConfigList(pointOfSale);
	}

	public PointOfSale getOrderInfo(PointOfSale pointOfSale) {
		return pointOfSaleDao.getOrderInfo(pointOfSale);
	}

	public PointOfSale saveOrder(PointOfSale pointOfSale) {
		return pointOfSaleDao.saveOrder(pointOfSale);
	}
	public PointOfSale getOrderPrice(PointOfSale pointOfSale) {
		return pointOfSaleDao.getOrderPrice(pointOfSale);
	}
	public PointOfSale saveDiscount(PointOfSale pointOfSale) {
		return pointOfSaleDao.saveDiscount(pointOfSale);
	}

	public List<PointOfSale> getDiscountList(PointOfSale pointOfSale) {
		return pointOfSaleDao.getDiscountList(pointOfSale);
	}

	public PointOfSale getDiscountInfo(PointOfSale pointOfSale) {
		return pointOfSaleDao.getDiscountInfo(pointOfSale);
	}

	public List<PointOfSale> getOrderInfoList(PointOfSale pointOfSale) throws Exception {
		return pointOfSaleDao.getOrderInfoList(pointOfSale);
	}

	public List<PointOfSale> getOrderList(PointOfSale pointOfSale) {
		return pointOfSaleDao.getOrderList(pointOfSale);
	}

	public List<PointOfSale> getPendingOrderList(PointOfSale pointOfSale) {
		return pointOfSaleDao.getPendingOrderList(pointOfSale);
	}

	public List<PointOfSale> getPendingOrderInfoList(PointOfSale pointOfSale) throws Exception {
		return pointOfSaleDao.getPendingOrderInfoList(pointOfSale);
	}

	public PointOfSale cancelOrder(PointOfSale pointOfSale) {
		return pointOfSaleDao.cancelOrder(pointOfSale);
	}

	public List<PointOfSale> getOrderEditList(PointOfSale pointOfSale) throws Exception {
		return pointOfSaleDao.getOrderEditList(pointOfSale);
	}

	public PointOfSale getOrderTotalAmount(String encOrderId) {
		return pointOfSaleDao.getOrderTotalAmount(encOrderId);
	}

	public PointOfSale saveOrderFinalize(PointOfSale pointOfSale) {
		return pointOfSaleDao.saveOrderFinalize(pointOfSale);
	}

	public List<PointOfSale> getOwnerConsumptionList(PointOfSale pointOfSale) {
		return pointOfSaleDao.getOwnerConsumptionList(pointOfSale);
	}

	public PointOfSale saveOwnerConsumption(PointOfSale pointOfSale) {
		return pointOfSaleDao.saveOwnerConsumption(pointOfSale);
	}
	public PointOfSale getOwnerConsumption(PointOfSale pointOfSale) {
		return pointOfSaleDao.getOwnerConsumption(pointOfSale);
	}

	public PointOfSale orderProcessComplete(PointOfSale pointOfSale) {
		return pointOfSaleDao.orderProcessComplete(pointOfSale);
	}

	public PointOfSale getDuplicteTable(PointOfSale pointOfSale) {
		return pointOfSaleDao.getDuplicteTable(pointOfSale);
	}

	public PointOfSale saveDueCustomer(PointOfSale pointOfSale) {
		return pointOfSaleDao.saveDueCustomer(pointOfSale);
	}

	public List<PointOfSale> getDueCustomerList(PointOfSale pointOfSale) throws Exception {
		return pointOfSaleDao.getDueCustomerList(pointOfSale);
	}
	public PointOfSale getDueCustomerInfo(PointOfSale pointOfSale) {
		return pointOfSaleDao.getDueCustomerInfo(pointOfSale);
	}

	public PointOfSale getDueDepositeAmount(String dueCustomerId) {
		return pointOfSaleDao.getDueDepositeAmount(dueCustomerId);
	}

	public PointOfSale saveDueCollection(PointOfSale pointOfSale) {
		return pointOfSaleDao.saveDueCollection(pointOfSale);
	}
	public List<PointOfSale> getDueCollectionHistoryList(PointOfSale pointOfSale) throws Exception {
		return pointOfSaleDao.getDueCollectionHistoryList(pointOfSale);
	}
}


















