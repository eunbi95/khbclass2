<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table{border: 1px solid skyblue; border-collapse: collapse; width: 300px; height: 100px;}
th{border: 1px solid skyblue; background-color: skyblue; color: blue; height: 30px;}

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>관리자전체출력</h1>
<table border="1" cellspacing="0" cellpadding="0">
<tr>
<th>번호</th>
<th>나이</th>
<th>이름</th>
<th>부서</th>
</tr>

<%
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://khgthree.cafe24.com:3306/khgthree","khgthree","wjdqhrydbrdnjs3");
Statement stmt = conn.createStatement();
String sql = "select no,age,irum,part from EBmanager";
ResultSet rs = stmt.executeQuery(sql);
while(rs.next()){
	int no=rs.getInt("no");
	String age = rs.getString("age");
	String irum=rs.getString("irum");
	String part=rs.getString("part");
	out.print("<tr><td>"+no+"</td>"+"<td>"+age+"</td>"+"<td>"+irum+"</td>"+"<td>"+part+"</td></tr>");
}
stmt.close();
conn.close();
%>

</table>
<a href="manager.jsp">관리자등록</a>
<a href="../haksaInfo.jsp">학사관리</a>
<a href="manager_deleteForm.jsp">관리자삭제</a>
</body>
</html>