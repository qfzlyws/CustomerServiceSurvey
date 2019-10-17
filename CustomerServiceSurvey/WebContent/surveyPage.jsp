<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${requestScope.surveyDesc }</title>
		<link rel="stylesheet" href="css/main.css" type="text/css">
		<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
		<script type="text/javascript" src="js/main.js"></script>
	</head>
	
	<body>
		<div class="pageContent">
			<h1>調查問卷開始</h1>
			
			<form action="/CustomerServiceSurvey/SurveyServlet" method="post" onsubmit="return checkNull();">
				<input type="hidden" name="submitResult" value="true"/>
				<input type="hidden" name="surveyId" value="${requestScope.surveyId }"/>
				
				<div class="survey">
					<c:forEach items="${requestScope.SurveyItemList }" var="item" varStatus="id">
						
						<div class="surveyItem" name="${item.getItemId()}">
							<c:choose>
								<c:when test="${item.getIsIgnore() == 'Y' }">
									<c:out value="${item.getItemDesc() }"></c:out>
									<div>請勾選所有符合的選項</div>
								</c:when>
								<c:otherwise>
									<span class="keyIndicator" id="${item.getItemId() }">*</span><c:out value="${item.getItemDesc() }"></c:out>
									<div>請選擇一個符合的選項</div>
								</c:otherwise>
							</c:choose>
						</div>
					
						<div class="surveyValue">
							<c:forEach items="${item.getItemValues() }" var="itemValue">
								<div>
									<c:if test="${item.getItemId() != \"32\" }">
										<c:if test="${item.getIsMultiSelect() == \"Y\" }">
											<input type="checkbox" name="${item.getItemId()}" value="${itemValue.getValueId() }"/><span id="${itemValue.getValueId() }">${itemValue.getValueDesc() }</span>
										</c:if>
										
										<c:if test="${item.getIsMultiSelect() == \"N\" }">
											<input type="radio" name="${item.getItemId()}" value="${itemValue.getValueId() }"/>${itemValue.getValueDesc() }
										</c:if>
										
										<c:if test="${itemValue.getUserInput() == \"Y\" }">
											<input type="text" name="${item.getItemId()}" id="${itemValue.getValueId() }" />
										</c:if>
									</c:if>
									
									<c:if test="${item.getItemId() == \"32\" }">
										<textarea rows="5" cols="60" name="${item.getItemId()}">無</textarea>
									</c:if>
								</div>
							</c:forEach>
						</div>
						
					</c:forEach>
					
					<div>
						<input type="submit" value="提交"/>
						<input type="reset" value="重置"/>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>