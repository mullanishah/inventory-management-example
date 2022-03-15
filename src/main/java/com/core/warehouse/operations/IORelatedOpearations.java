package com.core.warehouse.operations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import com.core.warehouse.pojo.WarehouseItem;

/**
 * @author Shahrukh
 * @since 16-Feb-2022
 */
public class IORelatedOpearations {
	
	private static final String CART_FILE = "cartFile.ser";
	
	public static void saveCartDataToFile(Map<Long, WarehouseItem> itemMap) throws Exception {
		
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(CART_FILE));){
			out.writeObject(itemMap);
			System.out.println("Item saved to file!! Serialization succeed.");
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Map<Long, WarehouseItem> restoreCartFromFile() throws Exception {
		
		Map<Long, WarehouseItem> itemMap = new HashMap<Long, WarehouseItem>();
		File cartFile = new File(CART_FILE);
		if(cartFile.exists() && cartFile.isFile() && cartFile.canRead()) {
			try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(cartFile));){
				itemMap = (Map<Long, WarehouseItem>) in.readObject();
			}
		}
		System.out.println("Cart file restored successfully: " + itemMap);
		return itemMap;
	}

}
