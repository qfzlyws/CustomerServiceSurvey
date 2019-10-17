<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%
		String suveryId = (String)request.getAttribute("surveyId");
		String surveyDesc = (String)request.getAttribute("surveyDesc");
	%>
	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><%=surveyDesc%></title>
	</head>
	
	<body>
		<div align="center">
			<h1><%=surveyDesc%></h1>
			
			<form action = "/CustomerServiceSurvey/SurveyServlet" method="post" >
				<input type="hidden" name="surveyId" value="<%=suveryId%>"/>
				<input type="hidden" name="surveyDesc" value="<%=surveyDesc%>"/>
				<input type="submit" value="開始"/>
			</form>
		</div>
	</body>
</html>