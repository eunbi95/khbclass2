<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KHJSP</title>
</head>
<body>
<form action="radioProcess.jsp" method="get">
<fieldset>
<legend>성별선택</legend>
<ul>
<li><label for="남자">남자</label>
<input type="radio" name="gender" value="남자">
</li>
<li><label for="여자">여자</label>
<input type="radio" name="gender" value="여자">
</li>
<li><input type="submit" value="눌러라">
</li>
</ul>
</fieldset>
</form>
</body>
</html>