package com.pos.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.inventory.dao.InventoryDao;
import com.pos.inventory.model.Inventory;

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService{


	@Autowired
	private InventoryDao inventoryDao;

	public Inventory saveIngredients(Inventory inventory) {
		return inventoryDao.saveIngredients(inventory);
	}

	public List<Inventory> getIngredientsList(Inventory inventory) throws Exception {
		return inventoryDao.getIngredientsList(inventory);
	}

	public Inventory getProductInfo(Inventory inventory) throws Exception {
		return inventoryDao.getProductInfo(inventory);
	}
	
	public Inventory saveStoreProduct(Inventory inventory) {
		return inventoryDao.saveStoreProduct(inventory);
	}

	public List<Inventory> getInventoryList(Inventory inventory) {
		return inventoryDao.getInventoryList(inventory);
	}
	
	public List<Inventory> getInventoryListSupplier(Inventory inventory) {
		return inventoryDao.getInventoryListSupplier(inventory);
	}

	public Inventory getPriceSum(Inventory inventory) {
		return inventoryDao.getPriceSum(inventory);
	}

	public List<Inventory> getProductView(Inventory inventory)throws Exception {
		return inventoryDao.getProductView(inventory);
	}

	public Inventory saveWastage(Inventory inventory) {
		return inventoryDao.saveWastage(inventory);
	}

	public List<Inventory> getWastageView(Inventory inventory) throws Exception {
		return inventoryDao.getWastageView(inventory);
	}

	public Inventory getWastageInfo(Inventory inventory) {
		return inventoryDao.getWastageInfo(inventory);
	}

	public Inventory saveStoreIngredients(Inventory inventory) {
		return inventoryDao.saveStoreIngredients(inventory);
	}

	public List<Inventory> getStoreInventoryList(Inventory inventory) {
		return inventoryDao.getStoreInventoryList(inventory);
	}

	public Inventory getStoredIngredientsInfo(Inventory inventory) throws Exception {
		return inventoryDao.getStoredIngredientsInfo(inventory);
	}

	public List<Inventory> getStoreHistoryList(Inventory inventory) throws Exception {
		return inventoryDao.getStoreHistoryList(inventory);
	}


}