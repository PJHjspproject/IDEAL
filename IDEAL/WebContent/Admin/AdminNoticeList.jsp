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
<input type="button" value="메인으로" onclick="location.href='AdminMain.ad'">
<h1>공지사항 글 목록 뿌려주는 페이지입니다.</h1>

<c:if test="${nList.isEmpty() }">
	<h2>등록된 공지사항이 없습니다.</h2>
</c:if>
<c:if test="${!nList.isEmpty() }">
	<h2>등록된 공지사항 글 목록입니다.</h2>
	<table>
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>올린날짜</td>
		</tr>
	<c:forEach var="n" items="${nList }">
		<tr onclick="location.href='AdminGetOneNotice.ad?num=${n.noticeNum}'">
			<td>${n.noticeNum }</td>
			<td>${n.noticeSubject }</td>
			<td>${n.noticeDate }</td>
	</c:forEach>
	</table>
</c:if>
<form action="AdminInsertNotice.ad" method="post">
	<input type="submit" value="글쓰기">
</form>

</body>
</html>