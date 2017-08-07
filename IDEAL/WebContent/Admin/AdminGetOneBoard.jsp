<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<input type="button" value="메인으로" onclick="location.href='AdminMain.ad'">
<h1 align="center">하나의 글을 볼수있는 곳입니다.</h1>
<c:set var="b" value="${oneBoard }"></c:set>
<form action="AdminBoardDelete.ad" method="post">
글번호 :<input type="text" name="num" value="${b.num }" readonly="readonly"><br>
글제목 :<input type="text" name="title" value=" ${b.title }"><br>
글내용 :<input type="text" name="content" value="${b.content }"><br>
글쓴날짜 : <f:formatDate value="${b.date }" pattern="yyyy-MM-dd"/>
별명 :<input type="text" name="nickname" value="${b.nickName }"><br>
<input type="submit" value="삭제되라얍">
<input type="button" value="게시판 글 목록" onclick="location.href='AdminBoardList.ad'">
</form>
</body>
</html>