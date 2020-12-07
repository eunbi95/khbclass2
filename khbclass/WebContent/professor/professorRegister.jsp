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
<h1>교수등록처리</h1>
<%
    String nai = request.getParameter("nai");
    String irum = request.getParameter("irum");
    String subject = request.getParameter("subject");
%>
<%=nai %>:<%=irum %>:<%=subject %>
<%
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://khgthree.cafe24.com/khgthree", "khgthree", "wjdqhrydbrdnjs3");
String sql = "insert into EBprofessor(age,irum,subject) values(?,?,?)";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, nai);
pstmt.setString(2, irum);
pstmt.setString(3, subject);
int cnt = pstmt.executeUpdate();
%>
<%=cnt %>건 교수님이 등록되었습니다.
<%
pstmt.close();
conn.close();
response.sendRedirect("professorList.jsp");
%>
</body>
</html>