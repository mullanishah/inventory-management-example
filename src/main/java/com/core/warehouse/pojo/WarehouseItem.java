package com.core.warehouse.pojo;

import java.io.Serializable;
import java.util.Date;
import com.core.warehouse.utils.CommonUtils;

/**
 * @author Shahrukh
 * @since 16-Feb-2022
 */
public class WarehouseItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long itemCode;
	private String title;
	private String description;
	private Integer quantity;
	private String category;
	private double price;
	private Date shipmentDate;
	
	public WarehouseItem(long itemCode, String title, String description, String category, Integer quantity, 
			double price, Date shipmentDate) {
		super();
		this.itemCode = itemCode;
		this.title = title;
		this.description = description;
		this.quantity = quantity;
		this.category = category;
		this.price = price;
		this.shipmentDate = shipmentDate;
	}
	
	public WarehouseItem() {
		// TODO Auto-generated constructor stub
	}

	public void setItemCode(long itemCode) {
		this.itemCode = itemCode;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}


	public long getItemCode() {
		return itemCode;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public String getCategory() {
		return category;
	}

	public double getPrice() {
		return price;
	}

	public Date getShipmentDate() {
		return shipmentDate;
	}

	@Override
	public String toString() {
		return "WarehouseItem [itemCode=" + itemCode + ", title=" + title + ", description=" + description
				+ ", quantity=" + quantity + ", category=" + category + ", price=" + price + ", shipmentDate="
				+ CommonUtils.getSdf().format(shipmentDate) + "]";
	}
		
}
