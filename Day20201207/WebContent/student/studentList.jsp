<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
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
<h1>학생전체출력</h1>
번호  나이  이름  학번<br>
<%
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://khgthree.cafe24.com:3306/khgthree","khgthree","wjdqhrydbrdnjs3");
String sql = "select no,age,irum,hakbun from EBstudent";
PreparedStatement pstmt = conn.prepareStatement(sql);

ResultSet rs = pstmt.executeQuery();
while(rs.next()){
	int no=rs.getInt("no");
	String age = rs.getString("age");
	String irum=rs.getString("irum");
	String hakbun=rs.getString("hakbun");
	out.print(no+"&nbsp;&nbsp;&nbsp;"+age+"&nbsp;&nbsp;&nbsp;"+irum+"&nbsp;&nbsp;&nbsp;"+hakbun+"<br>");
}
pstmt.close();
conn.close();
%>
</body>
</html>