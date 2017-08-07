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
	<div class="content">
	<form action="#">
	<h1>내가 작성한 댓글들</h1>
	<c:if test="${array.isEmpty() }">
		<h2>댓글이 없습니다.!</h2>
	</c:if>
	<c:if test="${!array.isEmpty() }">
		<table>
			<tr>
				<th>글번호</th>
				<th>댓글</th>
				<th colspan="2">등록일</th>
				
			</tr>
		<c:forEach var="v" items="${array }">
			<tr>
				<td onclick="location.href='content.bo?num=${v.num}'">${v.cNum }</td>
				<td onclick="location.href='content.bo?num=${v.num}'">${v.content }</td>
				<td onclick="location.href='content.bo?num=${v.num}'"><d:formatDate value="${v.date }" pattern="yyyy-MM-dd"/>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input type="button" value="삭제" onclick="location.href='Memberdeletecomment.mf?cNum=${v.cNum}'"></td>
			</tr>
		</c:forEach>	
			</table>
	</c:if>
	</form>
	</div>
</div>
</body>
</html>