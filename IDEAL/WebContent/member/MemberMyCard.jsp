<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
	div.wrap{
	padding-top: 100px;
	min-height: 514px;
	width:1240px;
	margin: 0 auto;
/* 	border: 1px solid red; */
	padding-bottom: 80px;
	
	}
	.table1 {
	width:604px;
/* 	float: left; */
	margin: 0 auto;
	}
	.table2 {
	width:604px;
	display: inline-block;
	}
	.inpu {
	width:100%;
/* 	border: 1px solid black; */
	height: 35px;
/* 	float:right; */
	}
	.tbwidth1 {
	width: 604px;
	margin: 0 auto;
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
<div class="inpu">
</div>	
	<div class="content">
	<div class="table1">
	<center>
	<form action="#">
	<h1>카드 잔액들</h1>
	<c:if test="${array.isEmpty() }">
		<h2>카드가 없습니다.</h2>
	</c:if>
	<c:if test="${!array.isEmpty() }">
		<table class="tbwidth1">
			<tr>
				<th class="trborder1">은행명</th>
				<th class="trborder1">카드 잔액</th>
			</tr>
		<c:forEach var="v" items="${array }">
			<tr>
				<td >${v.cardBank }</td>
				<td>${v.cardBalance }</td>
			</tr>
		</c:forEach>	
			</table>
	</c:if>
	</form>
	</center>
	</div>
	</div>
</div>
</body>
</html>