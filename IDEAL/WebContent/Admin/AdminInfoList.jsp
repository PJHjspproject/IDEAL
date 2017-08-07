<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>FAQ목록 페이지입니다.</h1>
<c:if test="${ilist.isEmpty() }">
	<h2>등록된 FAQ가 없습니다</h2>
</c:if>
<c:if test="${!ilist.isEmpty() }">
	<h2>등록된 FAQ목록입니다</h2>
	<table>
		<tr>
			<td>글번호</td>
			<td>글주제</td>
			<td>글제목</td>
		</tr>
		<c:forEach var="i" items="${ilist }">
		<tr onclick="location.href='AdminGetOneInfo.ad?infoNum=${i.infoNum}'">
			<td>${i.infoNum }</td>
			<td>${i.infoTitle }</td>
			<td>${i.infoSubject }</td>
		</c:forEach>
	</table>
</c:if>
	<input type="button" value="글쓰기" onclick="location.href='AdminWriteInfo.ad'">
</body>
</html>