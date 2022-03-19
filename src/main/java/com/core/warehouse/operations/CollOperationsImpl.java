package com.core.warehouse.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.core.warehouse.exceptions.WarehouseException;
import com.core.warehouse.pojo.WarehouseItem;

/**
 * @author Shahrukh
 * @since 18-Feb-2022
 */
public class CollOperationsImpl implements CollOperations {

	@Override
	public boolean addItem(WarehouseItem item, HashMap<Long, WarehouseItem> itemMap) throws WarehouseException {
		
		if(itemMap.containsKey(item.getItemCode())) {
			throw new WarehouseException("Item already exist !!");
		}else {
			itemMap.put(item.getItemCode(), item);
			return true;
		}
	}

	@Override
	public boolean removeItem(Long itemCode, HashMap<Long, WarehouseItem> itemMap) throws WarehouseException {
		
		boolean exists = itemMap.containsKey(itemCode);
//		if(exists) {
//			itemMap.remove(itemCode);
//			return true;
//		} else {
//			return false;
//		}
		if(itemMap.remove(itemCode) == null) {
			throw new WarehouseException("Item does not exists!!");
		}
		return true;
	}

	@Override
	public ArrayList<WarehouseItem> displayItems(Map<Long, WarehouseItem> itemMap) throws WarehouseException {
		
		ArrayList<WarehouseItem> itemList = new ArrayList<WarehouseItem>(10);
		Set<Entry<Long, WarehouseItem>> entries = itemMap.entrySet();
		for(Entry entry : entries) {
			itemList.add((WarehouseItem) entry.getValue());
		}
		return itemList;
	}

	@Override
	public ArrayList<WarehouseItem> sortItemsByPrice(HashMap<Long, WarehouseItem> itemMap) {

		Collection<WarehouseItem> itemColl = itemMap.values();
		ArrayList<WarehouseItem> itemList = new ArrayList<WarehouseItem>(itemColl);
		Collections.sort(itemList, new Comparator<WarehouseItem>() {

			@Override
			public int compare(WarehouseItem w1, WarehouseItem w2) {
				return ((Double)w1.getPrice()).compareTo(w2.getPrice());
			}
		});
		return itemList;	 
	}

	@Override
	public Map<Long, WarehouseItem> sortItemsByItemCode(Map<Long, WarehouseItem> itemMap) {
		
		TreeMap<Long, WarehouseItem> itemTree = new TreeMap<Long, WarehouseItem>(itemMap);
		return itemTree;
	}

	@Override
	public void sortItemsByShipment(Map<Long, WarehouseItem> itemMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sortItemsByCategory(Map<Long, WarehouseItem> itemMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WarehouseItem searchItemByTitle(String title, Map<Long, WarehouseItem> itemMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WarehouseItem searchItemByItemCode(Long itemCode, Map<Integer, WarehouseItem> itemMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean increaseQuantity() throws WarehouseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean decreaseQuantity() throws WarehouseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void prepareBill() throws WarehouseException {
		// TODO Auto-generated method stub
		
	}

}
