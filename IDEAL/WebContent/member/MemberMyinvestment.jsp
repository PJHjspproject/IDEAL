<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%
   String email = (String)session.getAttribute("MemberEmail");

%>
<title>Insert title here</title>

<style type="text/css">
   div.wrap{
   padding-top: 100px;
   min-height: 512px;
   padding-bottom: 80px;
   }
   .content h1{
   text-align:center;
   margin-bottom: 20px;
   }
   .content h2{
   text-align:center;
   }
   .content table{
   width:500px;
   margin: 0 auto;
   margin-bottom: 20px;
   }
   .content table tr td{
   text-align: center;
   margin-top: 10px;
   }
   .content table tr td:hover{
   color:#a50019;
   cursor: pointer;
}
   .tdalign{
   text-align: center;
   padding-left:0;
   margin: 10px;
   height: 40px;
   }
   	.mypage {
	background-color: #e0e0e0;
    border-width: 0;
    cursor: pointer;
    display: block;
    margin: 0;
    width: 120px;
    height: 60px;
    position: fixed;
    bottom: 150px;
    left: 60px;
    color: #fff;
    border-radius: 112px;
    z-index: 2;
	}
	
	.float1 {
	padding: 10px;
	background: none;
	border: none;
	text-align: center;
	padding-left:15px;	
	padding-top: 16px;
	font-weight: bold;
	}
	.trborder1 {
	border-bottom: 4px solid #a50019;
	border-collapse: none;
	padding: 10px;
	background-color: #ebebeb;
	}
	td {
	padding: 10px;
	text-align: center;
	}
</style>
</head>
<body>
<div class="wrap">
<div class="mypage"><input class="float1" type="button" value="마이페이지로" onclick="location.href='Memberinfo.mf'"></div>
   <div class="content">
   
   <c:if test="${array.isEmpty() }">
      <h2>내가 투자한 목록이 없습니다!</h2>
   </c:if>
   <c:if test="${!array.isEmpty() }">
      <table>
         <tr>
            <th colspan="2"><h1>내가 투자한 목록</h1></th>
         </tr>
         <tr>
            <th class="trborder1">투자프로그램이름</th>
            <th class="trborder1">투자금액</th>
         </tr>
            <c:forEach var="v" items="${array }">
               <tr>
                     <td><a href="viewInvestRequestAction.iR?investRequestNum=${v.investRequestNum }">${v.program }</a></td>
                     <th>${v.investMoney } 원</th>
               </tr>
            </c:forEach>   
         </table>
   </c:if>
   
   </div>
</div>
</body>
</html>