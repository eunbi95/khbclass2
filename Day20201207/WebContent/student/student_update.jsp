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
<h1>학생수정</h1>
<%
String irum = request.getParameter("irum");
String age = request.getParameter("age");
String hakbun = request.getParameter("hakbun");
String irumUpdate = request.getParameter("irumUpdate");
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://khgthree.cafe24.com:3306/khgthree","khgthree","wjdqhrydbrdnjs3");//로컬용
String sql = "update EBstudent set age=?,irum=?,hakbun=? where irum=?";

PreparedStatement pstmt=conn.prepareStatement(sql);
pstmt.setString(1, age);
pstmt.setString(2, irum);
pstmt.setString(3, hakbun);
pstmt.setString(3, irumUpdate);

int cnt = pstmt.executeUpdate();
%>
<%=cnt %>건 학생이 수정되었습니다.<br>
<a href="../haksaInfo.jsp">학사관리</a>&nbsp;<a href="studentList.jsp">학생전체출력</a>
<%
pstmt.close();
conn.close();
%>
</body>
</html>