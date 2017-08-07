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
<!-- <link href="aie.css" type="text/css" rel="stylesheet"> -->

<%
String memberEmail = (String)session.getAttribute("memberEmail");
String nickName = (String)session.getAttribute("nickName");
%>
</head>
<style type="text/css">
.tableAll{
border: none;
border-collapse: collapse;
}
.tableAll td{
text-align: center;

}
.tableAll tr.boardtr{
	background-image: url("img/boardbg.jpg");
	background-repeat: repeat-x;
	border: 1px solid;
	height: 40px;
	letter-spacing: 0.1em;
}
.wrap input{
float: right;
width: 150px;
}
.dotttd{
border-bottom: 1px dotted;
}
div.wrap{
	padding: 300px;
}
</style>
<body>
	<div class="wrap">
	<center>
		<table width="1028px" class="tableAll">
			<tr class="boardtr">
				<th>글번호</th>
				<th>글제목</th>
				<th>작성자</th>
				<th>등록일</th>
			</tr>
			<c:forEach var="v" items="${arry }">
			<tr onclick="location.href='questionContent.qU?num=${v.num}'">
				<td class="dotttd">${v.num}</td>
				<td class="dotttd">${v.title}</td>
				<td class="dotttd">${v.nickName}</td>
				<td class="dotttd"><d:formatDate value="${v.inputDate }" pattern="yyyy-MM-dd"/></td>
			</c:forEach>
			<tr>
				<td colspan="4"><input type="button" value="글쓰기" onclick="location.href='writequestion.qU'"></td>
			</tr>
		</table>
			
	</center>
	</div>
</body>
</html>
<%-- <style type="text/css">
.tableAll{
border: none;
border-collapse: collapse;
}
.tableAll td{
text-align: center;

}
.tableAll tr.boardtr{
	background-image: url("img/boardbg.jpg");
	background-repeat: repeat-x;
	border: 1px solid;
	height: 40px;
	letter-spacing: 0.1em;
}
.wrap input{
float: right;
width: 150px;
}
.dotttd{
border-bottom: 1px dotted;
}
div.wrap{
	padding: 300px;
}
</style>
<body>
	<div class="wrap">
	<center>
		<table width="1028px" class="tableAll">
			<tr class="boardtr">
				<th>글번호</th>
				<th>글제목</th>
				<th>작성자</th>
				<th>등록일</th>
			</tr>
			<c:forEach var="v" items="${arry }">
			<tr onclick="location.href='questionContent.qU?num=${v.num}'">
				<td class="dotttd">${v.num}</td>
				<td class="dotttd">${v.title}</td>
				<td class="dotttd">${v.nickName}</td>
				<td class="dotttd"><d:formatDate value="${v.inputDate }" pattern="yyyy-MM-dd"/></td>
			</c:forEach>
			<tr>
				<td colspan="4"><input type="button" value="글쓰기" onclick="location.href='writequestion.qU'"></td>
			</tr>
		</table>
			
	</center>
	</div>
</body>
</html> --%>