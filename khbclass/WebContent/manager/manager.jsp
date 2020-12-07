<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type ="text/css">
.kh01{
width:100px; height: 80px;
}
#managerRegister{
border:2px solid blue;
border-radius: 20px;
width:500px;
}
ul{list-style-type: none;}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>관리자등록</h1>
<div id="managerRegister">
<form action="managerRegister.jsp" method="get">

<ul>
<li><label for="나이">나이</label>
<input type="number" name="nai">
</li>
<li><label for="이름">이름</label>
<input type="text" name="irum" size="20">
</li>
<li><label for="부서">부서</label>
<input type="text" name="part" size="20">
</li>
<li><input type="image" src="../images/submit.png" class="kh01"></li>
</ul>
</form>
</div>
<a href="managerList.jsp">관리자전체출력</a>
<a href="../haksaInfo.jsp">학사관리</a>
</body>
</html>