<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link href="./css/notice1.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="wrap">
<center>
<h4 class="subh4">■ IDEAL 공지사항</h4>
<table width="1028px" class="tableAll">
	<tr class="boardtrno1">
		<th width="772px" class="noth1">제목</th>
		<th width="150px" class="noth0">등록일</th>
	</tr>
	<c:forEach var="v" items="${arry}">
	<tr onclick="location.href='./NoticeContent.no?noticeNum=${v.noticeNum}'">
		<td class="dot2center">${v.noticeSubject}</td>
		<td class="dot2center"><d:formatDate value="${v.noticeDate}" pattern="yyyy-MM-dd"/></td>
	</tr>
	</c:forEach>
</table>
</center>	

</div>
</body>
</html>