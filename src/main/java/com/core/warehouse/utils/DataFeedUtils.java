package com.core.warehouse.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.StringTokenizer;
import static com.core.warehouse.utils.CommonUtils.*;
import com.core.warehouse.pojo.WarehouseItem;

/**
 * @author Shahrukh
 * @since 18-Feb-2022
 */
public class DataFeedUtils {

	private static File productFile = null;
	private static BufferedReader reader = null;
	private static final String PRODUCT_FILE_NAME = "product-data.txt";
	private static WarehouseItem[] warehouseItems = null;

	public static WarehouseItem[] getWarehouseItems() {
		
		try {
			productFile = new File(PRODUCT_FILE_NAME);
			if(productFile.exists() && productFile.isFile() && productFile.canRead()) {
				
				int i = 0;
				warehouseItems = new WarehouseItem[15];
				reader = new BufferedReader(new FileReader(productFile));
				String fileContent = null;
				
				while((fileContent = reader.readLine()) != null) {

					//get random itemCode 
					int generatedItemCode = getNextRandomNumber();

					//create string tokens separed by commas 
					StringTokenizer currentLine = new StringTokenizer(fileContent, ",");

					//put it into Warehouse item object
					WarehouseItem item = new WarehouseItem();
					item.setItemCode(generatedItemCode);
					while(currentLine.hasMoreElements()) {
						item.setTitle(currentLine.nextToken().trim());
						item.setDescription(currentLine.nextToken().trim());
						item.setCategory(currentLine.nextToken().trim());
						item.setQuantity(Integer.parseInt(currentLine.nextToken().trim()));
						item.setPrice(Double.parseDouble(currentLine.nextToken().trim()));
						item.setShipmentDate(getSdf().parse(currentLine.nextToken()));
					}
					warehouseItems[i++] = item;
				}
			}
		} catch (Exception e) {
			if(e instanceof ParseException) {
				System.out.println("Error in parsing date: " + e.getMessage());
				e.printStackTrace();
			}
			else if(e instanceof IOException) {
				System.out.println("Error in handling file: " + e.getMessage());
				e.printStackTrace();
			}
			else
				e.printStackTrace();
		} finally {
			try {
				if(reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return warehouseItems;
	}
}
