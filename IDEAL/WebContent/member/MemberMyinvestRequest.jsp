<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	}
</style>
</head>
<body>
<div class="wrap">
	<div class="content">
	<h1>내 캠페인목록</h1>
	<c:if test="${array.isEmpty() }">
		<h2>내 캠페인 목록이 없습니다!</h2>
	</c:if>
	<c:if test="${!array.isEmpty() }">
		<table>
			<tr>
				<th>투자요청 번호</th>
				<th>투자프로그램이름</th>
				<th>모집 종료일</th>
			</tr>
		<c:forEach var="v" items="${array }">
			<tr onclick="location.href='MemberInvestRequestContent.mf?investRequestNum=${v.investRequestNum}'">
				<td>${v.investRequestNum }</td>
				<td>${v.program }</td>
				<td>${v.endDay }</td>
			</tr>
		</c:forEach>	
			</table>
	</c:if>
	
	</div>
</div>
</body>
</html>