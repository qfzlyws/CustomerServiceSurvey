package com.hjit.test.model;

public class SurveyResult {
	private String resultId;
	private String surveyId;
	private String ipAddress;
	private long submitDatetime;

	public SurveyResult(String resultId) {
		this.resultId = resultId;
	}
	
	public String getResultId() {
		return resultId;
	}

	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public long getSubmitDatetime() {
		return submitDatetime;
	}

	public void setSubmitDatetime(long submitDatetime) {
		this.submitDatetime = submitDatetime;
	}

}
