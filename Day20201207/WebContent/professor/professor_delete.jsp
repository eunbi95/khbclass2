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
<h1>교수 삭제</h1>
<%
   String irum = request.getParameter("irum");
   Class.forName("com.mysql.jdbc.Driver");
   Connection conn = DriverManager.getConnection("jdbc:mysql://khgthree.cafe24.com:3306/khgthree","khgthree","wjdqhrydbrdnjs3");//로컬용
   String sql = "delete from EBprofessor where irum=?";
   PreparedStatement pstmt = conn.prepareStatement(sql);
   pstmt.setString(1, irum);
   int cnt = pstmt.executeUpdate();
%>
<%=cnt %>건 교수님이 삭제되었습니다.
<%
pstmt.close();
conn.close();
%>
<%
response.sendRedirect("professorList.jsp");%>
</body>
</html>