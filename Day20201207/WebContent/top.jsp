<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>대구가톨릭대학교</title>
  <script type="text/javascript" src="js/jquery-1.10.2.js"></script>
  <script src="js/jquery.innerfade.js"></script>
  <style>
   body {
    background: #ccc; 
    font-family: Arial,sans-serif;
    font-size: 14px;
    line-height: 1.6;}
   #ALL {
    width: 700px;
    margin: 30px auto;
    background: #FFF;
    padding: 20px;}  
   .menu { 
    height: 45px;
    background: #000;}
   .menu ul {
    list-style: none;
    padding: 0;
    margin: 0;}
   .menu ul li {
    float: left;
    overflow: hidden;
    text-align: center;    
    line-height: 45px;}
   .menu ul li a {
    position: relative;
    display: block;
    width: 110px;
    height: 45px;
    color: #FFF; 
    font-family: Arial;
    font-size: 11px;
    font-weight: bold;
    letter-spacing: 1px;
    text-transform: uppercase;
    text-decoration: none;
    cursor: pointer;}

   .menu ul li a span {
    position: absolute;
    top: 0px;
    left: 0;
    width: 110px; } 
   .menu ul li a span.over{
    top: -45px; } 
   .menu ul li a span.over {
    background: #FFF;
    color: #000;}
.kh{
   width:200px; height:60px;
}
#homeregister{
position: absolute;
right:0px; top:0px;
width:300px; hegith:100px;
/*border:1px solid red;*/
font-size:20px;
}
a{text-decoration: none;}
a:hover {
	text-decoration: underline;
}
  </style>
 </head>
 <body>

  <div id="ALL">
   <h1><img src="images/kh.png" class="kh"></h1>
   <div class="menu">
    <ul>
     <li><a href="index.jsp?page=haksaInfo">학사소개</a></li>
     <li><a href="#">커뮤니티</a></li>
     <li><a href="#">모집과정</a></li>
     <li><a href="#">KHB반클레스</a></li>
     <li><a href="#">포트폴리오</a></li>
    </ul>
   </div>
   <div id="homeregister">
      <a href="#">홈|</a>
      <a href="#">회원가입|</a>
      <a href="#">로그인</a>
   </div>
  </div>
<!-- 실시간 검색어 -->   
   <div id ="news1">
      <ul id="news">
         <li>
            <a href="#n1">1 자바프로그래밍 </a>
         </li>
         <li>
            <a href="#n2">2 웹프로그래밍</a>
         </li>
         <li>
            <a href="#n3">3 데이터베이스</a>
         </li>
         <li>
            <a href="#n4">4 오라클</a>
         </li>
         <li>
            <a href="#n5">5 MYSQL</a>
         </li>      
      </ul>
   </div>
  <script type="text/javascript">
   $(function(){
    $(".menu li a").wrapInner( '<span class="out"></span>' );

    $(".menu li a").each(function() {
     $( '<span class="over">' +  $(this).text() + '</span>' ).appendTo( this );
    });
    $(".menu li a").hover(function() {
     $(".out", this).stop().animate({'top': '45px'}, 200); 
     $(".over", this).stop().animate({'top': '0px'}, 200);

    }, function() {
     $(".out", this).stop().animate({'top': '0px'},  200); 
     $(".over", this).stop().animate({'top': '-45px'}, 200);
    });
   });
   $('#news').innerfade({
	      animationtype: 'slide',
	      speed: 750,
	      timeout: 2000,
	      type: 'sequence',
	      containerheight: '1em'
	   });
  </script>
     </body>
</html>