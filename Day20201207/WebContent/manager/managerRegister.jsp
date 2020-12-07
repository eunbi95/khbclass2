<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>관리자등록처리</h1>
<%
    String nai = request.getParameter("nai");
    String irum = request.getParameter("irum");
    String part = request.getParameter("part");
%>
<%=nai %>:<%=irum %>:<%=part %>
<%
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://khgthree.cafe24.com/khgthree", "khgthree", "wjdqhrydbrdnjs3");
Statement stmt = conn.createStatement();
String sql = "insert into EBmanager(age,irum,part) values('"+nai+"','"+irum+"','"+part+"')";
int cnt = stmt.executeUpdate(sql);
%>
<%=cnt %>건 관리자님이 등록되었습니다.
<%
stmt.close();
conn.close();
response.sendRedirect("managerList.jsp");
%>
</body>
</html>