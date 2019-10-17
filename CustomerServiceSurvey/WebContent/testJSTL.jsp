<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>應用&lt;c:out&gt;標簽輸出字符串"水平線標記&lt;hr&gt;"</title>
</head>
<body>
escapeXml屬性為true時：
<c:out value="水平線標記<hr>" escapeXml="true"></c:out>
<br>
escapeXml屬性為false時：
<c:out value="水平線標記<hr>" escapeXml="false"></c:out>
<br>

</body>
</html>