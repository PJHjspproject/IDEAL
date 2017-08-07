<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
	td{
		width: 150;
	}
</style>
</head>
<body>
<input type="button" value="메인으로" onclick="location.href='AdminMain.ad'">
<h1 align="center">회원 목록 페이지 입니다.</h1>
<c:if test="${MemberList.isEmpty() }">
	<h2 align="center">가입한 회원이 없습니다.</h2>
</c:if>
<c:if test="${!MemberList.isEmpty()}">
	<h2 align="center">가입한 회원이 있습니다.</h2>  
	<table align="center" width="1000">
		<tr align="center">
			<td>이메일</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>생일</td>
			<td>휴대폰번호</td>
			<td>별명</td>
		</tr>
		<c:forEach var="m" items="${MemberList }">		
		<tr align="center" onclick="location.href='AdminMemberAbout.ad?nickName=${m.nickName }&memberEmail=${m.memberEmail}'">
			<td>${m.memberEmail }</td>
			<td>${m.pass }</td>
			<td>${m.name }</td>
			<td>${m.birth }</td>
			<td>${m.phone }</td>
			<td>${m.nickName }</td>
		</tr>
		</c:forEach>
	</table>
</c:if>
</body>
</html>