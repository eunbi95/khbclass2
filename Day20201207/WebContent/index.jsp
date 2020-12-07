<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String pagefile=request.getParameter("page");
if(pagefile==null){
pagefile="haksaInfo";
}
%>    
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
ul{list-style-type: none;}
#news1{
position:absolute;
 top:50px; left:500px;
width:400px; height:100px;
/*border: 1px solid black;*/
}
#top{
   position: absolute;
   top:0px; left:600px;
   /*border: 1px solid red;*/
}
#bottom{
position:absolute;
  top:800px; left:500px;
  color: white;
}

</style>
<meta charset="UTF-8">
<title>KHJSP</title>
</head>
<body>
<div id="top">
<jsp:include page="top.jsp"/>
</div>
<div id="left">
<jsp:include page="left.jsp"/>
</div>
<div id="center">
<jsp:include page='<%= pagefile+".jsp" %>'/>
</div>
<div id="bottom">
<jsp:include page="bottom.jsp"/>
</div>
</body>
</html>