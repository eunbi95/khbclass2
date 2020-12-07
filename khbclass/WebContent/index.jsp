<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body {
   background-color: white;
}

ul {
   list-style-type: none;
}

hr {
   background-color: dimgray;
   height: 5px;
   border: white;
   border-radius: 5px;
}

#khmaintitle {
   position:absolute;
   top:20px; left:300px;
   height: 100px;width:1000px;
   font-size: 25px;
}

#khmember {
   position: absolute;
   top: 8px;
   right: 10px;
   float: left;
   font-size: 15px;
}

#khmember a {
   color: green;
}

li {
   float: left;
   margin: 10px;
}

#khpic {
   position: absolute;
   width: 400px;
   height: 800px;
   top: 120px;
   left: 300px;
}

.khlogo {
   width: 200px;
   height: 50px;
}

a {
   text-decoration: none;
   color: black;
   margin: 20px;
}

a:hover {
   text-decoration: underline;
}

.khmain {
   position:fixed;
   width: 1000px;
   height: 500px;
}

#news1 {
   position: absolute;
   top: 50px;
   right: 50px;
   width: 220px;
   height: 50px;
   font-size: 15px;
}
</style>
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery.innerfade.js"></script>
<meta charset="UTF-8">
<title>KHJSP</title>
</head>
<body>
   <div class="frame">
      <div class="header">
         <h1>
            <a href="#"> <img src="images/catlogo.png" class="khlogo"></a>
         </h1>
         <div id="khmember">
            <a href="index.jsp">홈</a> <a href="#">회원가입</a> <a href="login.jsp">로그인</a>
         </div>
         <div id="khmaintitle">
            <ul>
               <li><a href="haksaInfo.jsp">학사소개</a></li>
               <li><a href="#">커뮤니티</a></li>
               <li><a href="#">모집과정</a></li>
               <li><a href="#">KHB반클래스</a></li>
            </ul>
         </div>
      </div>
      <!-- header -->
      <hr>
      <div id="khpic">
         <ul id="portfolio">
            <li><a href="#"> <img src="images/catmain.png" title="궁디팡팡이벤트"
                  class="khmain" />
            </a></li>
            <li><a href="#"> <img src="images/cat00.gif" title="KH정보교육원"
                  class="khmain" />
            </a></li>
            <li><a href="#"> <img src="images/cat11.png" title="검은고양이"
                  class="khmain" />
            </a></li>
            <li><a href="#"> <img src="images/cat7.jpg" title="치즈냥"
                  class="khmain" />
            </a></li>
            <li><a href="#"> <img src="images/cat5.jpg" title="고앵고앵"
                  class="khmain" />
            </a></li>
         </ul>
      </div>
      <div id="news1">
         <ul id="news">
            <li><a href="#n1">1 kh정보교육원</a></li>
            <li><a href="#n2">2 khb반클래스</a></li>
            <li><a href="#n3">3 검은고양이</a></li>
            <li><a href="#n4">4 치즈고양이</a></li>
            <li><a href="#n5">5 흰냥이</a></li>
            <li><a href="#n6">6 고앵고앵</a></li>
            <li><a href="#n7">7 자바프로그래밍</a></li>
            <li><a href="#n8">8 데이터베이스</a></li>
         </ul>
      </div>
      <script>
         $('#portfolio').innerfade({
            speed : 750,
            timeout : 2000,
            type : 'sequence',
            containerheight : '220px'
         });
         $('#news').innerfade({
            animationtype : 'slide',
            speed : 750,
            timeout : 2000,
            type : 'sequence',
            containerheight : '1em'
         });
      </script>

   </div>
   <!-- frame -->
</body>
</html>