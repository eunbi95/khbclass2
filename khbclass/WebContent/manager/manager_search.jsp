<%@page import="java.sql.ResultSet"%>
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
<h1>관리자검색</h1>
<%
String irumSearch = request.getParameter("irum");
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://khgthree.cafe24.com:3306/khgthree","khgthree","wjdqhrydbrdnjs3");//로컬용
Statement stmt = conn.createStatement();
String sql="select no,age,irum,part from EBmanager where irum='"+irumSearch+"'";
ResultSet rs= stmt.executeQuery(sql);
int no=0;
String irum=null;
String age=null;
String part=null;
while(rs.next()){
	no=rs.getInt("no");
	age = rs.getString("age");
	irum = rs.getString("irum");
	part = rs.getString("part");
}
%>
<%=no %>&nbsp;&nbsp;<%=age %>&nbsp;&nbsp;<%=irum %>&nbsp;&nbsp;<%=part %><br>
<a href="managerList.jsp">관리자전체출력</a>&nbsp;<a href="../haksaInfo.jsp">학사관리</a>
<%
stmt.close();
rs.close();
conn.close();
%>
</body>
</html>