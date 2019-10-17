package com.hjit.test.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class SurveyDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String SQLStr;
	private Survey survey;
	
	public void rollback() throws SQLException
	{
		if(conn != null)
			conn.rollback();
	}
	
	public void commit() throws SQLException
	{
		if(conn != null)
			conn.commit();
	}
	
	public Survey getSurvey(String surveyDate)
	{
		SQLStr = "select survey_id,survey_desc from surveys where ? between to_char(start_date,'yyyymmdd') and to_char(end_date,'yyyymmdd')";
		
		conn = DBConn.getConnection();
		
		if(conn != null)
		{
			try {
				pstmt = conn.prepareStatement(SQLStr);
				pstmt.setString(1, surveyDate);
				rs = pstmt.executeQuery();
				
				if(rs.next())
				{
					survey = new Survey(rs.getString("survey_id"));
					survey.setSurveyDate(surveyDate);
					survey.setSurveyDesc(rs.getString("survey_desc"));
				}
				
				rs.close();
				pstmt.close();
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return survey;
	}
	
	public String getSurveyDesc(String surveyId)
	{
		String surveyDesc = null;
		
		SQLStr = "select survey_desc from surveys where survey_id = ?";
		
		conn = DBConn.getConnection();
		
		if (conn != null)
		{
			try {
				
				pstmt = conn.prepareStatement(SQLStr);				
				pstmt.setString(1, surveyId);
				rs = pstmt.executeQuery();
				
				if(rs.next())
				{
					surveyDesc = rs.getString("survey_desc");
				}
				
				rs.close();
				pstmt.close();
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return surveyDesc;
	}
	
	public SurveyItemValue getSurveyItemValue(String valueId)
	{
		SurveyItemValue itemValue = null;
		
		SQLStr = "select value_id,value_desc,item_id,user_input from survey_item_values where value_id = ?";
		
		conn = DBConn.getConnection();
		
		if (conn != null)
		{
			try {
				
				pstmt = conn.prepareStatement(SQLStr);				
				pstmt.setString(1, valueId);
				rs = pstmt.executeQuery();
				
				if(rs.next())
				{
					if(itemValue == null)
						itemValue = new SurveyItemValue(rs.getString("value_id"));
					
					itemValue.setItemId(rs.getString("item_id"));
					itemValue.setValueDesc(rs.getString("value_desc"));
					itemValue.setUserInput(rs.getString("user_input"));
				}
				
				rs.close();
				pstmt.close();
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return itemValue;
	}
	
	public void saveSurveyResult(SurveyResult result) throws SQLException
	{
		SQLStr = "insert into survey_results values(?, ?, ?, ?)";
		
		conn = DBConn.getConnection();
		
		if (conn != null)
		{
			pstmt = conn.prepareStatement(SQLStr);				
			pstmt.setString(1, result.getResultId());
			pstmt.setString(2, result.getSurveyId());
			pstmt.setString(3, result.getIpAddress());
			pstmt.setTimestamp(4, new Timestamp(result.getSubmitDatetime()));
			
			pstmt.executeUpdate();
			
			pstmt.close();
			
		}
		
	}
	
	public void saveSurveyResultDetail(SurveyResultDetail resultDetail) throws SQLException
	{
		SQLStr = "insert into survey_result_details values(?, ?, ?, ?)";
		
		conn = DBConn.getConnection();
		
		if (conn != null)
		{
			pstmt = conn.prepareStatement(SQLStr);				
			pstmt.setString(1, resultDetail.getDetailId());
			pstmt.setString(2, resultDetail.getResultId());
			pstmt.setString(3, resultDetail.getValueId());
			pstmt.setString(4, resultDetail.getValueDesc());
			
			pstmt.executeUpdate();
			
			pstmt.close();			
		}
		
	}
	
	public List<SurveyItem> getSurveyItem(String surveyId)
	{
		List<SurveyItem> surveyItemList = null;
		List<SurveyItemValue> itemValueList = null;
		
		SQLStr = "select item_id,item_desc,ismultiselect,isignore from survey_items where survey_id = ?";
		String SQLItemValues = "select value_id,value_desc,user_input from survey_item_values where item_id = ?";
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		
		conn = DBConn.getConnection();
		
		if (conn != null)
		{
			try {
				
				pstmt = conn.prepareStatement(SQLStr);
				pstmt2 = conn.prepareStatement(SQLItemValues);
				
				pstmt.setString(1, surveyId);
				rs = pstmt.executeQuery();
				
				while(rs.next())
				{
					if(surveyItemList == null)
						surveyItemList = new ArrayList<SurveyItem>();
					
					pstmt2.setString(1, rs.getString("item_id"));
					rs2 = pstmt2.executeQuery();
					
					while(rs2.next())
					{
						if(itemValueList == null)
							itemValueList = new ArrayList<SurveyItemValue>();
						
						SurveyItemValue itemValue = new SurveyItemValue(rs2.getString("value_id"));
						itemValue.setValueDesc(rs2.getString("value_desc"));
						itemValue.setItemId(rs.getString("item_id"));
						itemValue.setUserInput(rs2.getString("user_input"));
						
						itemValueList.add(itemValue);
					}
					
					SurveyItem item = new SurveyItem(rs.getString("item_id"));
					item.setItemDesc(rs.getString("item_desc"));
					item.setSurveyId(surveyId);
					item.setIsMultiSelect(rs.getString("ismultiselect"));
					item.setIsIgnore(rs.getString("isignore"));
					item.setItemValues(itemValueList);
					
					surveyItemList.add(item);
					
					itemValueList = null;
				}
				
				rs.close();
				rs2.close();
				pstmt.close();
				pstmt2.close();
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return surveyItemList;
	}
}
