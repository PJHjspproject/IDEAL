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
	<h1>친목게시판에 쓴 글</h1>
	<c:if test="${arrayb.isEmpty() }">
		<h2>친목게시판에 쓴 글이 없습니다.!</h2>
	</c:if>
	<c:if test="${!arrayb.isEmpty() }">
		<table>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th colspan="2">등록일</th>
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
	</form>
	
	<form action="#">
	<h1>1:1문의에 쓴 글</h1>
	<c:if test="${arrayq.isEmpty() }">
		<h2>1:1문의에 쓴 글이 없습니다!</h2>
	</c:if>
	<c:if test="${!arrayq.isEmpty() }">
		<table>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>등록일</th>
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
	</form>
	</div>
</div>
</body>
</html>