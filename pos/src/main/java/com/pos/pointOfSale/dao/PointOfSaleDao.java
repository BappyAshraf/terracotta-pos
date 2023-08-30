package com.pos.pointOfSale.dao;

import java.util.List;

import com.pos.accounts.model.OwnerConsumptionInfo;
import com.pos.pointOfSale.model.PointOfSale;


public interface PointOfSaleDao {

	
	public List<PointOfSale> getItemList(PointOfSale pointOfSale) throws Exception;
	public PointOfSale saveItem(PointOfSale pointOfSale);
	public PointOfSale saveItemConfig(PointOfSale pointOfSale);
	public List<PointOfSale> getItemConfigList(PointOfSale pointOfSale);
	public PointOfSale getOrderInfo(PointOfSale pointOfSale);
	public PointOfSale saveOrder(PointOfSale pointOfSale);
	public PointOfSale getOrderPrice(PointOfSale pointOfSale);
	public PointOfSale saveDiscount(PointOfSale pointOfSale);
	public List<PointOfSale> getDiscountList(PointOfSale pointOfSale);
	public PointOfSale getDiscountInfo(PointOfSale pointOfSale);
	public List<PointOfSale> getOrderInfoList(PointOfSale pointOfSale) throws Exception;
	public List<PointOfSale> getOrderList(PointOfSale pointOfSale);
	public List<PointOfSale> getPendingOrderList(PointOfSale pointOfSale);
	public List<PointOfSale> getPendingOrderInfoList(PointOfSale pointOfSale) throws Exception;
	public PointOfSale cancelOrder(PointOfSale pointOfSale);
	public List<PointOfSale> getOrderEditList(PointOfSale pointOfSale) throws Exception;
	public PointOfSale getOrderTotalAmount(String encOrderId);
	public PointOfSale saveOrderFinalize(PointOfSale pointOfSale);
	
	public List<PointOfSale> getOwnerConsumptionList(PointOfSale pointOfSale);
	
	public PointOfSale saveOwnerConsumption(PointOfSale pointOfSale);

	public PointOfSale getOwnerConsumption(PointOfSale pointOfSale);
	
	public PointOfSale orderProcessComplete(PointOfSale pointOfSale);
	
	public PointOfSale getDuplicteTable(PointOfSale pointOfSale);
	
	public PointOfSale saveDueCustomer(PointOfSale pointOfSale);
	
	public List<PointOfSale> getDueCustomerList(PointOfSale pointOfSale) throws Exception;
	
	public PointOfSale getDueCustomerInfo(PointOfSale pointOfSale);
	
	public PointOfSale getDueDepositeAmount(String dueCustomerId);
	
	public PointOfSale saveDueCollection(PointOfSale pointOfSale);
	
	public List<PointOfSale> getDueCollectionHistoryList(PointOfSale pointOfSale) throws Exception;
}






















