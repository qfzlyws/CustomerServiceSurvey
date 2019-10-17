package com.hjit.test.model;

public class Survey {
	private String surveyId;
	private String surveyDesc;
	private String surveyDate;
	
	public Survey(String surveyId) {
		this.surveyId = surveyId;
	}

	public String getSurveyId() {
		return surveyId;
	}
	
	public String getSurveyDesc() {
		return surveyDesc;
	}

	public void setSurveyDesc(String surveyDesc) {
		this.surveyDesc = surveyDesc;
	}

	public String getSurveyDate() {
		return surveyDate;
	}

	public void setSurveyDate(String surveyDate) {
		this.surveyDate = surveyDate;
	}

}
