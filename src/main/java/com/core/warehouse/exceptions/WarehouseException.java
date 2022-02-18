package com.core.warehouse.exceptions;

/**
 * @author Shahrukh
 * @since 16-Feb-2022
 */
public class WarehouseException extends Throwable {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public WarehouseException(String msg) {
		message = msg;
	}

}
