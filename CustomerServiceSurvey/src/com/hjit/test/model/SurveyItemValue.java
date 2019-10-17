package com.hjit.test.model;

public class SurveyItemValue {
	private String valueId;
	private String valueDesc;
	private String itemId;
	private String userInput;

	public SurveyItemValue(String valueId) {
		this.valueId = valueId;
	}
	
	public String getValueId() {
		return valueId;
	}

	public String getValueDesc() {
		return valueDesc;
	}

	public void setValueDesc(String valueDesc) {
		this.valueDesc = valueDesc;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getUserInput() {
		return userInput;
	}

	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}

}
