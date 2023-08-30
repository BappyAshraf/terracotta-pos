package com.pos.inventory.dao;

import java.util.List;

import com.pos.inventory.model.Inventory;
import com.pos.lookup.model.LookupModel;

public interface InventoryDao {

	public List<Inventory> getIngredientsList(Inventory inventory) throws Exception;
	
	public Inventory getProductInfo(Inventory inventory) throws Exception;
	
	public Inventory saveIngredients(Inventory inventory);
	public Inventory saveStoreProduct(Inventory inventory);
	public List<Inventory> getInventoryList(Inventory inventory);
	public List<Inventory> getInventoryListSupplier(Inventory inventory);
	public Inventory getPriceSum(Inventory inventory);
	public List<Inventory> getProductView(Inventory inventory) throws Exception;
	public Inventory saveWastage(Inventory inventory);
	public List<Inventory> getWastageView(Inventory inventory) throws Exception;
	public Inventory getWastageInfo(Inventory inventory);
	public Inventory saveStoreIngredients(Inventory inventory);
	public List<Inventory> getStoreInventoryList(Inventory inventory);
	
	public Inventory getStoredIngredientsInfo(Inventory inventory) throws Exception;
	
	public List<Inventory> getStoreHistoryList(Inventory inventory) throws Exception;
}