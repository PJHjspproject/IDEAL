<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="AdminNoticeWrite.ad" method="post">
	글제목 : <input type="text" name="subject"><br>
	글내용 : <textarea rows="15" cols="150" name="content"></textarea><br>
	이미지파일 : <input type="file" name="img"><br>
	<input type="submit" value="작성완료">
</form>
</body>
</html>