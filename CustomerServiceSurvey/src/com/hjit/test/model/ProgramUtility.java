package com.hjit.test.model;

public class ProgramUtility {
	public static String getPK(){
		String pkId = null;
		
		String random = Long.toString((long)(Math.random() * 10000000));
		
		pkId = random + Long.toString(System.currentTimeMillis()).substring(0, 13);
		
		return pkId;
	}
}
