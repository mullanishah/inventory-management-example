package com.core.warehouse.utils;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Shahrukh
 * @since 16-Feb-2022
 */
public class CommonUtils {
	
	private static Scanner scanner;
	private static SimpleDateFormat sdf;
	private static Random random = null;
	
	static {
		scanner = new Scanner(System.in);
		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}

	public static Scanner getScanner() {
		return scanner;
	}

	public static SimpleDateFormat getSdf() {
		return sdf;
	}
	
	public static Integer getNextRandomNumber() {
		random = new Random();
		return random.nextInt(10000);
	}

}
