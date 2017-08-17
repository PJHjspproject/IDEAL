<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/question1.css">

<%
String memberEmail = (String)session.getAttribute("memberEmail");
String nickName = (String)session.getAttribute("nickName");
%>

<body>
	<div class="wrap">
	<center>
	<h4 class="subh4">■ IDEAL 1:1문의</h4>
		<table class="tableAll">
			<tr class="boardtrqu1">
				<th class="iuth0">글번호</th>
				<th width="570" class="iuth0">글제목</th>
				<th class="iuth0">작성자</th>
				<th class="iuth0">등록일</th>
			</tr>
			<c:forEach var="v" items="${arry }">
			<tr onclick="location.href='questionContent.qU?num=${v.num}'">
				<td class="dot2center">${v.num}</td>
				<td class="dot2center">${v.title}</td>
				<td class="dot2center">${v.nickName}</td>
				<td class="dot2center"><d:formatDate value="${v.inputDate }" pattern="yyyy-MM-dd"/></td>
			</c:forEach>
			<tr>
				<td colspan="4"><input class="write1" type="button" value="글쓰기" onclick="location.href='writequestion.qU'"></td>
			</tr>
		</table>
			<div class="clear"></div>
	</center>
	</div>
</body>
</html>
