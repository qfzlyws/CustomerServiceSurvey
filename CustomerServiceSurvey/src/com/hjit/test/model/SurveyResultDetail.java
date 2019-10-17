package com.hjit.test.model;

public class SurveyResultDetail {
	private String detailId;
	private String resultId;
	private String valueId;
	private String valueDesc;

	public SurveyResultDetail(String detailId) {
		this.detailId = detailId;
	}
	
	public String getDetailId() {
		return detailId;
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	public String getValueId() {
		return valueId;
	}

	public void setValueId(String valueId) {
		this.valueId = valueId;
	}

	public String getValueDesc() {
		return valueDesc;
	}

	public void setValueDesc(String valueDesc) {
		this.valueDesc = valueDesc;
	}

}
