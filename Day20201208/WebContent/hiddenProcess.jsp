<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KHJSP</title>
</head>
<body>
<%
String hiddenData = request.getParameter("hiddenData");
%>
안보이는값 전송:<%=hiddenData %>
</body>
</html>