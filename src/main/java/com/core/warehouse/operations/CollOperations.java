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

	boolean addItem(WarehouseItem item, HashMap<Long, WarehouseItem> itemMap) throws WarehouseException;
	boolean removeItem(Long itemCode, HashMap<Long, WarehouseItem> itemMap) throws WarehouseException;
	
	ArrayList<WarehouseItem> displayItems(Map<Long, WarehouseItem> itemMap) throws WarehouseException;
	
	void sortItemsByPrice(HashMap<Long, WarehouseItem> itemMap);
	ArrayList<WarehouseItem> sortByPrice(HashMap<Long, WarehouseItem> itemMap);
	Map<Integer, WarehouseItem> sortItemsByItemCode(Map<Long, WarehouseItem> itemMap);
	
	void sortItemsByShipment(Map<Long, WarehouseItem> itemMap);
	void sortItemsByCategory(Map<Long, WarehouseItem> itemMap);
	
	WarehouseItem searchItemByTitle(String title, Map<Long, WarehouseItem> itemMap);
	WarehouseItem searchItemByItemCode(Long itemCode, Map<Integer, WarehouseItem> itemMap);
	
	boolean increaseQuantity() throws WarehouseException;;
	boolean decreaseQuantity() throws WarehouseException;;
	
	void prepareBill() throws WarehouseException;
}
