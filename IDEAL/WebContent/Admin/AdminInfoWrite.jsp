<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="AdminInsertInfo.ad" method="post">
	글주제 : <input type="text" name="title"><br>
	글제목 : <input type="text" name="subject"><br>
	글내용 : <textarea rows="15" cols="150" name="content"></textarea><br>
	이미지 : <input type="file" name="image"><br>
	<input type="submit" value="글쓰기">
	<input type="reset" value="다시쓰기">
</form>
</body>
</html>