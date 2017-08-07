<%@page import="net.board.db.BoardDao"%>
<%@page import="net.board.db.BoardDto"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
request.setCharacterEncoding("UTF-8");
String memberEmail = (String)session.getAttribute("memberEmail");
String nickName = (String)session.getAttribute("nickName");
%>
<script src="https://code.jquery.com/jquery-3.2.1.js"
	integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
	crossorigin="anonymous">
</script>
<script type="text/javascript">
	var memberEmail = '<%=memberEmail%>';
	$(function () {
		$('.check').on("click", function(){
			var $num = $(this).children(".num").text();
			if(memberEmail == 'null'){
				$('a.login-window').trigger("click");
			}else{
				location.href="content.bo?num="+$num;
			}
		});
	});
	
	
</script>
<style>
	.wrap{
		width:1240px;
/* 		border:1px solid; */
		margin: 0 auto;
		padding-top: 200px;
		text-align: center;
	}
	
	a{
		text-decoration: none;
	}
	.sack{
		color: #a50019;
	}
	.table{
		margin: 0 auto;
		text-align: center;
	}

</style>

</head>
<body>
<div class="wrap">
<!-- 게시판 -->
<h1>Notice</h1>
	<table class="table">	
		<tr>
			<th class="tno">글번호</th>
			<th class="title">글제목</th>
			<th class="twrite">글작성자</th>
			<th class="tdate">글올린날짜</th>
		</tr>
		<c:if test="${BoardList.isEmpty() }">
			<tr>
				<td>글이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${!BoardList.isEmpty() }">
			<c:forEach var="a" items="${BoardList }">
	
	
	<!-- 해당하는 게시물의 Num값을 get방식으로 넘겨준다.  -->
			<tr class="check">
				<td class="num">${a.num }</td>
				<td>${a.title }</td>
				<td>${a.nickName }</td>
				<td><f:formatDate value="${a.date }" pattern="yyyy-MM-dd"/></td>
			</c:forEach>
		</c:if>
	</table>
	<c:if test="${startPage > pageBlock }">
		<a href="boardList.bo?pageNum=${startPage-pageBlock }">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
		<c:if test="${nowPage == i }">
			<a href="boardList.bo?pageNum=${i }" class="sack"><b>${i }&nbsp;&nbsp;&nbsp;&nbsp;</b></a>
		</c:if>
		<c:if test="${nowPage != i }">
			<a href="boardList.bo?pageNum=${i }">${i }&nbsp;&nbsp;&nbsp;&nbsp;</a>
		</c:if>
	</c:forEach>
	<c:if test="${endPage < pageCount }">
		<a href="boardList.bo?pageNum=${startPage+pageBlock }">[다음]</a>
	</c:if>	
	<c:if test="${memberEmail != null }">
	<input type="button" value="글쓰기" onclick="location.href='write.bo'">
	</c:if>
</div>
</body>
</head>


<%-- 
<body>
<%
	request.setCharacterEncoding("utf-8");

	
%>
<div class="wrap">
<!-- 게시판 -->
<h1>Notice</h1>
	<table>	
		<tr>
			<th class="tno">글번호</th>
			<th class="title">글제목</th>
			<th class="twrite">글작성자</th>
			<th class="tdate">글올린날짜</th>
		</tr>
		<c:if test="${BoardList.isEmpty() }">
			<tr>
				<td>글이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${!BoardList.isEmpty() }">
			<c:forEach var="a" items="${BoardList }">
	
	
	<!-- 해당하는 게시물의 Num값을 get방식으로 넘겨준다.  -->
			<tr onclick="location.href='content.bo?num=${a.num}'">
				<td>${a.num }</td>
				<td>${a.title }</td>
				<td>${a.nickName }</td>
				<td><f:formatDate value="${a.date }" pattern="yyyy-MM-dd"/></td>
			</c:forEach>
		</c:if>
		
	<!-- <form action="write.bo" method="post">
	
		<input type="submit" value="글쓰기">
	</form> -->
	
	
	<input type="button" value="글쓰기" onclick="location.href='write.bo'">
	</table>
</div>
</body> --%>
</html>