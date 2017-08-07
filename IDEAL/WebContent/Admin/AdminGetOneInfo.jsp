<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="AdminUpdateInfo.ad" method="post">
	글 주제 : <input type="text" name="title" value="${idto.infoTitle }"><br>
	글 제목 : <input type="text" name="subject" value="${idto.infoSubject }"><br>
	글 번호 : <input type="text" name="num" value="${idto.infoNum }"><br>
	글 이미지 :<img src="../informationUse/image/${idto.infoImage }">
	
	글 내용 : <textarea rows="15" cols="150" name="content">${idto.infoContent }</textarea><br>
	<input type="submit" value="글 수정하기">
	<input type="button" value="글 삭제하기" onclick="location.href='AdminDeleteInfo.ad?infoNum=${idto.infoNum}'">
	<input type="button" value="글 목록가기" onclick="location.href='AdminInformationUse.ad'">
</form>
</body>
</html>