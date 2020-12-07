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
<style>
.kh01{
width:100px; height:50px;
}
#studentUpdate{
border:1px solid blue;
border-radius: 20px;
width:500px;
}
ul{list-style-type: none;}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
<% 
String irumUpdate = request.getParameter("irum");
 Class.forName("com.mysql.jdbc.Driver");
   Connection conn = DriverManager.getConnection("jdbc:mysql://khgthree.cafe24.com:3306/khgthree","khgthree","wjdqhrydbrdnjs3");//로컬용
   String sql = "select age,irum,hakbun from EBstudent where irum=?";

   PreparedStatement pstmt = conn.prepareStatement(sql);
   pstmt.setString(1, irumUpdate);
   ResultSet rs= pstmt.executeQuery();
   String age = null;
   String irum = null;
   String hakbun = null;
   while(rs.next()){
	   age=rs.getString("age");
	   irum=rs.getString("irum");
	   hakbun=rs.getString("hakbun");
	   
   }
   %>
<h1>최종변경하기전내용입니다.</h1>
<form action = "student/student_update.jsp" method="get">
<input type="hidden" name="irumUpdate" value="<%=irumUpdate %>">
<ul>
<li><label for="나이변경">나이변경</label>
<input type="number" name="age" value="<%=age %>">
</li>
<li><label for="이름변경">이름변경</label>
<input type="text" name="irum" value="<%=irum %>">
</li>
<li><label for="학번변경">학번변경</label >
<input type="text" name="hakbun" value="<%=hakbun %>">
</li>
<li><input type="image" src="../images/update.jpg" class="kh01">
</li>
</ul>
</form>
</body>
</html>