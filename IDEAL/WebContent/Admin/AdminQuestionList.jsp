<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<input type="button" value="메인으로" onclick="location.href='AdminMain.ad'">
<h1 align="center">1:1문의글 현황을보는 페이지입니다.</h1>
<c:if test="${questionList.isEmpty() }">
	<h3 align="center">문의글이 없어요!</h3>
</c:if>
<c:if test="${!questionList.isEmpty() }">
	<h3 align="center">문의글이 있어요!</h3>
	<div align="center">
	<form>
		<table>
			<tr>
				<td>글번호</td>
				<td>글제목</td>
				<td>회원이름</td>
				<td>등록일</td>
			</tr>
			<c:forEach var="q" items="${questionList }">
			<tr onclick="location.href='AdminGetOneQuestion.ad?num=${q.num}'">
				<td>${q.num }</td>
				<td>${q.title }</td>
				<td>${q.nickName }</td>
				<td>${q.inputDate }</td>
			</tr>
			</c:forEach>
		</table>
	</form>
	</div>
</c:if>

<c:if test="${cquestList.isEmpty() }">
	<h3>답변완료 글목록이 없습니다</h3>
</c:if>
<c:if test="${!cquestList.isEmpty() }">
	<br><br><br><br><br><br><br>
	<div align="center">
	<h3>답변 완료 글목록 입니다.</h3>
	<form>
		<table>
			<tr>
				<td>글번호</td>
				<td>글제목</td>
				<td>회원이름</td>
				<td>등록일</td>
			</tr>
			<c:forEach var="cq" items="${cquestionList }">
			<tr onclick="location.href='AdminGetOneQuestion.ad?num=${cq.num}'">
				<td>${cq.num }</td>
				<td>${cq.title }</td>
				<td>${cq.nickName }</td>
				<td>${cq.inputDate }</td>
			</tr>
			</c:forEach>
		</table>
	</form>
	</div>
</c:if>
</body>
</html>