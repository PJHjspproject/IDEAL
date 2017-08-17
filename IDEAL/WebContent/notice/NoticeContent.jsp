<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
<table class="tablecono" border="0">
	<tr class="boardtrno1">
		<th width="742" class="noth1">${dto.noticeSubject}</th>
		<th width="286" class="noth2"><d:formatDate value="${dto.noticeDate}" pattern="yyyy-MM-dd"/> </th>
	</tr>
	<tr>
		<td colspan="2"><p class="imgpad">[Image]</p><br/>
		<img class="no_conimg" src="notice/image/${dto.noticeImg}" align="center"></td>
	</tr>
	<tr>
		<td colspan="2" class="dotttd">${dto.noticeContent }</td>
	</tr>
	<tr>
		<td colspan="2">
			<input class="tolist1" type="button" value="목록" onclick="location.href='list.no'">
		</td>
	</tr>
</table>

</center>	

</div>
</body>
</html>