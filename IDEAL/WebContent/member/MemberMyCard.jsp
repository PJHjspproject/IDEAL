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
	}
</style>
</head>
<body>
<div class="wrap">
<input type="button" value="마이페이지로" onclick="location.href='Memberinfo.mf'">
	<div class="content">
	<form action="#">
	<h1>카드 잔액들</h1>
	<c:if test="${array.isEmpty() }">
		<h2>카드가 없습니다.</h2>
	</c:if>
	<c:if test="${!array.isEmpty() }">
		<table>
			<tr>
				<th>은행명</th>
				<th>카드 잔액</th>
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
	</div>
</div>
</body>
</html>