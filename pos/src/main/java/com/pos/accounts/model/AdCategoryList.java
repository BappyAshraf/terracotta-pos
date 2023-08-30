package com.pos.accounts.model;

public class AdCategoryList {
	
	
	private String encCategoryId;
	private String amount;
	
	private String allowanceDeductCategoryId;
	private String allowanceDeductCategoryName;
	
	public AdCategoryList() {
	}

	public AdCategoryList(String encCategoryId, String amount, String allowanceDeductCategoryId,
			String allowanceDeductCategoryName) {
		super();
		this.encCategoryId = encCategoryId;
		this.amount = amount;
		this.allowanceDeductCategoryId = allowanceDeductCategoryId;
		this.allowanceDeductCategoryName = allowanceDeductCategoryName;
	}

	public String getEncCategoryId() {
		return encCategoryId;
	}

	public void setEncCategoryId(String encCategoryId) {
		this.encCategoryId = encCategoryId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAllowanceDeductCategoryId() {
		return allowanceDeductCategoryId;
	}

	public void setAllowanceDeductCategoryId(String allowanceDeductCategoryId) {
		this.allowanceDeductCategoryId = allowanceDeductCategoryId;
	}

	public String getAllowanceDeductCategoryName() {
		return allowanceDeductCategoryName;
	}

	public void setAllowanceDeductCategoryName(String allowanceDeductCategoryName) {
		this.allowanceDeductCategoryName = allowanceDeductCategoryName;
	}

	
	
	

}
