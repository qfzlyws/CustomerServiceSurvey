package com.hjit.test.model;

import java.util.List;

public class SurveyItem {
	private String itemId;
	private String itemDesc;
	private String surveyId;
	private String isMultiSelect;
	private String isIgnore;
	private List<SurveyItemValue> itemValues;

	public SurveyItem(String itemId)
	{
		this.itemId = itemId;
	}
	
	public String getItemId() {
		return itemId;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	public String getIsMultiSelect() {
		return isMultiSelect;
	}

	public void setIsMultiSelect(String isMultiSelect) {
		this.isMultiSelect = isMultiSelect;
	}

	public String getIsIgnore() {
		return isIgnore;
	}

	public void setIsIgnore(String isIgnore) {
		this.isIgnore = isIgnore;
	}

	public List<SurveyItemValue> getItemValues() {
		return itemValues;
	}

	public void setItemValues(List<SurveyItemValue> itemValues) {
		this.itemValues = itemValues;
	}

}
