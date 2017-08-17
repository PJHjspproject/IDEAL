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
	margin-right:32px;
	float: left;
	display: inline-block;
	}
	.table2 {
	width:604px;
	display: inline-block;
	}
	.float1{
	float:left;
	}
	.inpu {
	width:100%;
/* 	border: 1px solid black; */
	height: 35px;
	}
	.tbwidth1 {
	width: 604px;
	}
	.tbwidth2 {
	width: 604px;
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
	.trborder2 {
	border-bottom: 4px solid #102E3F;
	border-collapse: none;
	padding: 10px;
	background-color: #ebebeb;
	}
</style>
</head>
<body>
<div class="wrap">
<div class="mypage"><input class="float1" type="button" value="마이페이지로" onclick="location.href='Memberinfo.mf'"></div>
<div class="inpu">

</div>
	<div class="content">
	<form action="#">
	<div class="table1">
	<center>
	<h1>친목게시판에 쓴 글</h1>
	<c:if test="${arrayb.isEmpty() }">
		<h2>친목게시판에 쓴 글이 없습니다.!</h2>
	</c:if>
	<c:if test="${!arrayb.isEmpty() }">
		<table class="tbwidth1">
			<tr>
				<th class="trborder1">글번호</th>
				<th class="trborder1">제목</th>
				<th class="trborder1" colspan="2">등록일</th>
			</tr>
		<c:forEach var="v" items="${arrayb }">
			<tr>
				<td onclick="location.href='content.bo?num=${v.num}'">${v.num }</td>
				<td onclick="location.href='content.bo?num=${v.num}'">${v.title }</td>
				<td onclick="location.href='content.bo?num=${v.num}'"><d:formatDate value="${v.date }" pattern="yyyy-MM-dd"/>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input type="button" value="삭제" onclick="location.href='Memberdeleteboard.mf?num=${v.num}'"></td>
			</tr>
		</c:forEach>	
		</table>
	</c:if>
	</center>
	</div>
	</form>
	
	<form action="#">
	<div class="table2">
	<center>
	<h1>1:1문의에 쓴 글</h1>
	<c:if test="${arrayq.isEmpty() }">
		<h2>1:1문의에 쓴 글이 없습니다!</h2>
	</c:if>
	<c:if test="${!arrayq.isEmpty() }">
		<table class="tbwidth2">
			<tr>
				<th class="trborder2">글번호</th>
				<th class="trborder2">제목</th>
				<th class="trborder2">등록일</th>
				<th class="trborder2"></th>
			</tr>
		<c:forEach var="v" items="${arrayq }">
			<tr>
				<td onclick="location.href='questionContent.qU?num=${v.num}'">${v.num }</td>
				<td onclick="location.href='questionContent.qU?num=${v.num}'">${v.title } &nbsp;&nbsp;&nbsp;
				<c:if test="${v.questionStatement == false }">
					<small>답변중</small>
				</c:if>
				<c:if test="${v.questionStatement == true }">
					<small>답변완료</small>
				</c:if>
				 </td>
				<td onclick="location.href='questionContent.qU?num=${v.num}'"><d:formatDate value="${v.inputDate }" pattern="yyyy-MM-dd"/>  &nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input type="button" value="삭제" onclick="location.href='Memberdeletequestion.mf?num=${v.num }'"></td>
			</tr>
		</c:forEach>	
		</table>
	</c:if>
	</center>
	</div>
	</form>
	</div>
</div>
</body>
</html>