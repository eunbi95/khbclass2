<%@page import="java.sql.PreparedStatement"%>
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
<h1>관리자수정</h1>
<%
String irum = request.getParameter("irum");
String age = request.getParameter("age");
String part = request.getParameter("part");
String irumUpdate = request.getParameter("irumUpdate");
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://khgthree.cafe24.com:3306/khgthree","khgthree","wjdqhrydbrdnjs3");//로컬용
String sql = "update EBmanager set age='"+age+"',irum='"+irum+"',part='"+part+"' where irum='"+irumUpdate+"'";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, age);
pstmt.setString(2, irum);
pstmt.setString(3, part);
pstmt.setString(4, irumUpdate);
int cnt = pstmt.executeUpdate();
%>
<%=cnt %>건 관리자님이 수정되었습니다.<br>
<%response.sendRedirect("managerList.jsp");%>
<%
pstmt.close();
conn.close();
%>
</body>
</html>