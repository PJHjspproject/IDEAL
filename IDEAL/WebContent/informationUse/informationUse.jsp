<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>FAQ</title>
<!-- <link href="aie.css" type="text/css" rel="stylesheet"> -->
<style type="text/css">
.wrap{
	padding: 300px;
}

</style>
</head>
<body>
<div class="wrap">
<center>
<table>
	<tr class="boardtr">
		<th>주제</th>
		<th>제목</th>
	</tr>
	<c:forEach var="v" items="${array }">
	<tr onclick="location.href='informationUse_content.iU?infoNum=${v.infoNum}'">
		<td width="150px">${v.infoSubject }</td>
		<td width="772px">${v.infoTitle }</td>
	</tr>
	</c:forEach>
</table>
</center>	

</div>
</body>
</html>