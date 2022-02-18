package com.core.warehouse.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.core.warehouse.exceptions.WarehouseException;
import com.core.warehouse.pojo.WarehouseItem;

/**
 * @author Shahrukh
 * @since 18-Feb-2022
 */
public interface CollOperations {

	boolean addItem(WarehouseItem item, HashMap<Integer, WarehouseItem> itemMap) throws WarehouseException;
	boolean removeItem(Integer itemCode, HashMap<Integer, WarehouseItem> itemMap) throws WarehouseException;
	
	ArrayList<WarehouseItem> displayItems(Map<Integer, WarehouseItem> itemMap) throws WarehouseException;
	
	void sortItemsByPrice(HashMap<Integer, WarehouseItem> itemMap);
	ArrayList<WarehouseItem> sortByPrice(HashMap<Integer, WarehouseItem> itemMap);
	Map<Integer, WarehouseItem> sortItemsByItemCode(Map<Integer, WarehouseItem> itemMap);
	
	void sortItemsByShipment(Map<Integer, WarehouseItem> itemMap);
	void sortItemsByCategory(Map<Integer, WarehouseItem> itemMap);
	
	WarehouseItem searchItemByTitle(String title, Map<Integer, WarehouseItem> itemMap);
	WarehouseItem searchItemByItemCode(long itemCode, Map<Integer, WarehouseItem> itemMap);
	
	boolean increaseQuantity() throws WarehouseException;;
	boolean decreaseQuantity() throws WarehouseException;;
	
	void prepareBill() throws WarehouseException;
}
