package com.core.warehouse.tester;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import com.core.warehouse.exceptions.WarehouseException;
import com.core.warehouse.operations.CollOperations;
import com.core.warehouse.operations.CollOperationsImpl;
import com.core.warehouse.operations.IORelatedOpearations;
import com.core.warehouse.pojo.WarehouseItem;
import static com.core.warehouse.utils.CommonUtils.*;

/**
 * This class is intended to be used by warehouse/store keeper who is in control of billing and updating of user cart.
 * @author Shahrukh
 * @since 16-Feb-2022 
 */
public class WarehouseConsoleApplication {
	private static HashMap<Long, WarehouseItem> itemMap;
	private static CollOperations collOperations;

	
	public WarehouseConsoleApplication() {
		restoreCart();
		collOperations = new CollOperationsImpl();
	}

	public static void main(String[] args) throws WarehouseException {
		WarehouseConsoleApplication consoleApp = new WarehouseConsoleApplication();
		int choice = 0;
		try(Scanner sc = new Scanner(System.in);) {
			do {
				System.out.println("================================\n"
						+ "1.Display cart \t\t\t"
						+ "2.Add item to cart \n"
						+ "3.Remove item from cart \t"
						+ "4.Sort cart(on Price) \n"
						+ "5.Sort cart(on Item code) \t"
						+ "6.Generate Bill/ Cart checkout \n"
						+ "7.Exit");
				System.out.println("Enter your choice: ");
				choice = sc.nextInt();
				switch(choice) {
				case 1:
					System.out.println(collOperations.displayItems(itemMap));
					break;
				case 2:
					System.out.println("Enter item details: (itemCode, title, description, category, shipment(today), quantity, price):");
					WarehouseItem item = new WarehouseItem(sc.nextLong(), sc.next(), sc.next(), sc.next(), getSdf().parse(sc.next()), sc.nextInt(), sc.nextDouble());	
					boolean status = collOperations.addItem(item, itemMap);
					System.out.println(status ? "Item added into the cart" : "Item was not added!");
					break;
				case 3:
					System.out.println("Enter item code from the cart to remove: ");
					status = collOperations.removeItem(sc.nextLong(), itemMap);
					System.out.println(status ? "Item removed successfully" : "Item was not removed!");
					break;
				case 4:
					System.out.println("Cart values sorted on price: " + collOperations.sortItemsByPrice(itemMap));
					break;
				case 5: 
					System.out.println("Cart sorted on item code: " + collOperations.sortItemsByItemCode(itemMap));
					break;
				case 6:
					Collection<WarehouseItem> itemColl = itemMap.values();
					LinkedList<WarehouseItem> itemList = new LinkedList<WarehouseItem>(itemColl);
					double totalAmount = 0;
					for(WarehouseItem wi : itemList) {
						totalAmount += (wi.getPrice() * wi.getQuantity());
					}
					System.out.println("Bill generated: Rs." + totalAmount);
					itemMap.clear();
					IORelatedOpearations.clearCartFile();
					break;
				case 7:
					System.exit(0);
				}
			} while(choice != 7);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void restoreCart() {
		try {
			itemMap = (HashMap<Long, WarehouseItem>) IORelatedOpearations.restoreCartFromFile();
			//System.out.println("Restored cart: " + itemMap);
		} catch (Exception e) {
			System.err.println("Error in restoring data from file");
			e.printStackTrace();
		}
	}
}
