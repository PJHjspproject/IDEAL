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
<form action="AdminUpdateNotice.ad" method="post">
	글번호 : <input type="text" name="noticeNum" value="${ndto.noticeNum }" readonly="readonly"><br>
	글제목 : <input type="text" name="noticeSubject" value="${ndto.noticeSubject }"><br>
	올린 날짜 : <input type="text" name="noticeDate" value="${ndto.noticeDate }"><br>
	<c:if test="${!ndto.noticeImg.isEmpty() }">
		이미지 : <img src="../notice/image/${ndto.noticeImg }" style="max-width:500px;max-height:500px;"><br>
	</c:if>
	글내용 : <textarea rows="15" cols="150" name="noticeContent">${ndto.noticeContent }</textarea><br>
	<input type="submit" value="글수정">
	<input type="button" value="글삭제" onclick="location.href='AdminDeleteNotice.ad?noticeNum=${ndto.noticeNum}'">
	<input type="button" value="글목록" onclick="location.href='AdminNotice.ad'">
</form>
</body>
</html>