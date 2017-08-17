<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
   String email = (String)session.getAttribute("MemberEmail");

   session.setAttribute("MemberEmail", email);
%>
<title>Insert title here</title>

<style type="text/css">
.content{
   margin:0 auto;
   text-align: center;
   width: 500px;
   padding-bottom: 40px;
}
.wrap{
   padding-top: 100px;
   min-height: 514px;
}
table{
   margin:0 auto;

   width: 490px;
}
td{
   padding: 10px;
   text-align:left; 
}
td#right{
   text-align: right;
}
td#center{
   text-align: center;
}
#rightslide{
   padding-right:10px;
   
}
.content table tr td button{
    height: 50px;
    text-align: center;
    width: 100%;
}
.button1 {
  display: inline-block;
  border-radius: 2px;
  background-color: #f4f4f4; 
/*   border-left: 6px solid #a50019; */
  color: #333;
  text-align: center;
  font-size: 20px;
/*   padding: 20px; */
  width: 200px;
  transition: all 0.2s;
  cursor: pointer;
  margin: 5px;
  overflow: hidden;
/*   -webkit-transition-duration: 0.4s; /* Safari */ */
/*   background-image:linear-gradient(90deg, #333,#fff);  */
  font-weight: bold;
  border:none;
  border-left: 4px solid #a50019;
}
.button1:hover {
/*   background-image:linear-gradient(90deg, #A50019,#fff);  */
   background-color: #a50019;  
  color: #FFFFFF;
  font-weight: bold;
}
.button1 span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.3s;
}

.button1 span:after {
  content: '\00bb';
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
/*   transition: 0.2s; */
}

.button1:hover span {
  padding-right: 25px;
}

.button1:hover span:after {
  opacity: 1;
  right: 0;
}

</style>
</head>
<body>
<div class="wrap">
   <div class="content">
   <h1>회원 정보 보기</h1>
   <br>
      <table>
         <tr>
            <td colspan="2" id="center"><a href="./MemberMyinvestRequest.mf" ><button class="button1" style="vertical-align:middle"><span style="font-weight: bold;">내 캠페인목록</span></button></a><!-- 투자요청한 목록 --></td>
         </tr>
         <tr>
            <td colspan="2" id="center"><a href="./MemberMyinvestment.mf"><button class="button1" style="vertical-align:middle"><span style="font-weight: bold;">내 투자목록</span></button></a><!-- 내가 투자한 목록 --></td>
         </tr>
         <tr>
            <td colspan="2" id="center"><a href="./MemberMyWirteView.mf" ><button class="button1" style="vertical-align:middle"><span style="font-weight: bold;">내가 쓴 글</span></button></a><!-- 투자요청한 목록 --></td>
         </tr>
         <tr>
            <td colspan="2" id="center"><a href="./MemberMyReWirteView.mf"><button class="button1" style="vertical-align:middle"><span style="font-weight: bold;">내가 쓴 댓글</span></button></a><!-- 내가 투자한 목록 --></td>
         </tr>
         <tr>
            <td colspan="2" id="center"><a href="./MemberMyCard.mf"><button class="button1" style="vertical-align:middle"><span style="font-weight: bold;">카드 잔액</span></button></a><!-- 내가 투자한 목록 --></td>
         </tr>
         </table>
         <table>
         <tr>
            <td><a href="./MemberView.mf"><button>회원 수정</button></a></td>
            <td><a href="./MemberOutAction.mf"><button>회원 탈퇴</button></a></td>
         </tr>
      </table>
   </div> 
</div>
</body>
</html>