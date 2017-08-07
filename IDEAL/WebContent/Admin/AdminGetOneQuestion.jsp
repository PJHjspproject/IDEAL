<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.2.1.js"
	integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
	crossorigin="anonymous"></script>

<script type="text/javascript">
	$(function () {
		$('.reple').on("click", function () {
			$('.retext').toggle();
		})
	})
</script>
<title>Insert title here</title>
</head>
<body>
	<form action="AdminUpdateQuestion.ad" method="post">
		<table align="center">
			<tr>
				<td>글번호</td>
				<td>제목</td>
				<td>회원별명</td>
				<td>작성일</td>
				<td>내용</td>
				<td>답변내용</td>
			</tr>
			<tr>
				<td>${qdto.num }</td>
				<td>${qdto.title }</td>
				<td>${qdto.nickName }</td>
				<td><f:formatDate value="${qdto.inputDate }" pattern="yyyy-MM-dd"/></td>
				<td>${qdto.content }</td>
				<c:if test="${qdto.reContent == null }">
					<td>답변내용이 없습니다</td>
				</c:if>
				<c:if test="${qdto.reContent != null }">
					<td>${qdto.reContent }</td>
				</c:if>
			</tr>
		</table>
		<input type="hidden" name="num" value="${qdto.num }">
		<input type="button" value="답변달아주기" class="reple">
		<input type="button" value="목록가기" onclick="location.href='AdminQuestion.ad'">
		<textarea rows="15" cols="150" name="retext" style="display: none;" class="retext"></textarea>
		<input type="submit" value="답변등록">
	</form>
</body>
</html>