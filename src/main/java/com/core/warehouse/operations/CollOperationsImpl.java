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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sortItemsByPrice(HashMap<Long, WarehouseItem> itemMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<WarehouseItem> sortByPrice(HashMap<Long, WarehouseItem> itemMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, WarehouseItem> sortItemsByItemCode(Map<Long, WarehouseItem> itemMap) {
		// TODO Auto-generated method stub
		return null;
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
