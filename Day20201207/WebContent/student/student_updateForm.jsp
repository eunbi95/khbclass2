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
<h1>학생수정</h1>
<div id="studentUpdate">
<form action="student/student_updateConfirm.jsp" method="get">
<ul>
<li> <label for="수정할이름">수정할이름</label>
<input type="text" name="irum">
</li>
<li> 
<input type="image" src="../images/update.jpg" class="kh01">
</ul>
</form>
</div>
</body>
</html>