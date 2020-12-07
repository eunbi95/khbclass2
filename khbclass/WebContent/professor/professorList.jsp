<%@page import="java.sql.PreparedStatement"%>
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
<h1>교수전체출력</h1>
<table border="1" cellspacing="0" cellpadding="0">
<tr>
<th>번호</th>
<th>나이</th>
<th>이름</th>
<th>과목</th>
</tr>

<%
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://khgthree.cafe24.com:3306/khgthree","khgthree","wjdqhrydbrdnjs3");
String sql = "select no,age,irum,subject from EBprofessor";
PreparedStatement pstmt = conn.prepareStatement(sql);
ResultSet rs = pstmt.executeQuery();
while(rs.next()){
	int no=rs.getInt("no");
	String age = rs.getString("age");
	String irum=rs.getString("irum");
	String subject=rs.getString("subject");
	out.print("<tr><td>"+no+"</td>"+"<td>"+age+"</td>"+"<td>"+irum+"</td>"+"<td>"+subject+"</td></tr>");
}
pstmt.close();
conn.close();
%>

</table>
<a href="professor.jsp">교수등록</a>
<a href="../haksaInfo.jsp">학사관리</a>
</body>
</html>