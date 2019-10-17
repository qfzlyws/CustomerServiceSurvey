package com.hjit.test.model;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SurveyServlet
 */
@WebServlet("/SurveyServlet")
public class SurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SurveyDao surveyDao = null; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SurveyServlet() {
        super();
        
        surveyDao = new SurveyDao();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String surveyId = request.getParameter("surveyId");
		String surveyDesc = request.getParameter("surveyDesc");
		String submitResult = request.getParameter("submitResult");
		
		if(submitResult != null && submitResult.equals("true"))
		{
			saveResult(request,response);
			return;
		}
		
		Survey survey = null;
		
		if(surveyId == null)
		{
			//判斷當前日期有無調查
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar cal = Calendar.getInstance();
			
			survey = surveyDao.getSurvey(sdf.format(cal.getTime()));
			
			if(survey == null)
			{
				request.setAttribute("infoMsg", "當前日期下無調查問卷!");
				dispachPage(request,response,"/infoPage.jsp");
			}
			else
			{
				request.setAttribute("surveyId", survey.getSurveyId());
				request.setAttribute("surveyDesc", survey.getSurveyDesc());
				dispachPage(request,response,"/welcome.jsp");
			}
		}
		else
		{
			//獲取調查的詳細項目
			List<SurveyItem> surveyItemList = surveyDao.getSurveyItem(surveyId);
			
			if(surveyItemList == null)
			{
				request.setAttribute("infoMsg", "此調查問卷無效!");				
				dispachPage(request,response,"/infoPage.jsp");
				return;
			}
			
			surveyDesc = surveyDao.getSurveyDesc(surveyId);
			
			request.setAttribute("SurveyItemList", surveyItemList);
			request.setAttribute("surveyId", surveyId);
			request.setAttribute("surveyDesc", surveyDesc);
			dispachPage(request,response,"/surveyPage.jsp");
		}
	}
	
	private void dispachPage(HttpServletRequest request,HttpServletResponse response,String page) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	private void saveResult(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		SurveyResult surveyResult = null;
		SurveyResultDetail resultDetail = null;
		
		surveyResult = new SurveyResult(ProgramUtility.getPK());
		surveyResult.setIpAddress(request.getRemoteAddr());
		surveyResult.setSubmitDatetime(System.currentTimeMillis());
		surveyResult.setSurveyId(request.getParameter("surveyId"));
		
		try
		{
			surveyDao.saveSurveyResult(surveyResult);
			Enumeration<String> itemIds = request.getParameterNames();
			
			while(itemIds.hasMoreElements())
			{
				String itemId = itemIds.nextElement();
				
				if(itemId.equals("submitResult") || itemId.equals("surveyId"))
					continue;
				
				String itemValues[] = request.getParameterValues(itemId);
				
				for(int i=0;i<itemValues.length;i++)
				{
					if(itemValues[i].equals(""))
						continue;
						
					SurveyItemValue itemValue = surveyDao.getSurveyItemValue(itemValues[i]);
					
					if(itemValue != null)
					{
						resultDetail = new SurveyResultDetail(ProgramUtility.getPK());
						resultDetail.setResultId(surveyResult.getResultId());
						
						if(itemValue.getUserInput().equals("Y"))
							resultDetail.setValueDesc(itemValues[i + 1]);
						else
							resultDetail.setValueDesc(itemValue.getValueDesc());
						
						resultDetail.setValueId(itemValues[i]);
						
						surveyDao.saveSurveyResultDetail(resultDetail);
					}
					
				}
			}
			
			surveyDao.commit();
		}
		catch(SQLException sqlException)
		{
			try {
				surveyDao.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("infoMsg", "提交失敗!" + sqlException.getMessage());
			dispachPage(request,response,"/infoPage.jsp");
			return;
		}
		
		request.setAttribute("infoMsg", "成功完成問卷調查！");
		dispachPage(request,response,"/infoPage.jsp");
		return;
	}

}
