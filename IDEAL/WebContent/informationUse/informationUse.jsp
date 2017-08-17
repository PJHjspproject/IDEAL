<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>FAQ</title>
<link href="./css/infouse.css" type="text/css" rel="stylesheet">

</head>
<body>
<div class="wrap">
<center>
<h4 class="subh4">■ IDEAL 이용안내사항</h4>
<table class="tableAll">
	<tr class="boardtr">
		<th class="iuth1">주제</th>
		<th class="iuth1">제목</th>
	</tr>
	<c:forEach var="v" items="${array }">
	<tr onclick="location.href='informationUse_content.iU?infoNum=${v.infoNum}'">
		<td width="150px" class="dot2center">${v.infoSubject }</td>
		<td width="772px" class="dot2center">${v.infoTitle }</td>
	</tr>
	</c:forEach>
</table>
</center>	

</div>
</body>
</html>