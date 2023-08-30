package com.pos.pointOfSale.model;

public class OrderModel {

	private String encItemOrderId;
	private String itemId;
	private String quantity;
	private String itemPrice;
	private String subTotal;
	private String orderNote;
	
	public OrderModel() {
	}


	
	
public OrderModel(String encItemOrderId, String itemId, String quantity, String itemPrice, String subTotal,
			String orderNote) {
		this.encItemOrderId = encItemOrderId;
		this.itemId = itemId;
		this.quantity = quantity;
		this.itemPrice = itemPrice;
		this.subTotal = subTotal;
		this.orderNote = orderNote;
	}




/*	public OrderModel(String encItemOrderId, String itemId, String quantity, String itemPrice, String subTotal) {
		this.encItemOrderId = encItemOrderId;
		this.itemId = itemId;
		this.quantity = quantity;
		this.itemPrice = itemPrice;
		this.subTotal = subTotal;
	}*/


	public String getOrderNote() {
		return orderNote;
	}


	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}


	public String getEncItemOrderId() {
		return encItemOrderId;
	}


	public void setEncItemOrderId(String encItemOrderId) {
		this.encItemOrderId = encItemOrderId;
	}


	public String getItemId() {
		return itemId;
	}


	public void setItemId(String itemId) {
		this.itemId = itemId;
	}


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	public String getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}


	public String getSubTotal() {
		return subTotal;
	}


	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	

	
	
	
	
}
