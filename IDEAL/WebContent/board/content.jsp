<%-- <%@page import="net.board.action.CommentList"%> --%>
<%@page import="java.sql.Time"%>
<%@page import="net.board.db.CommentDto"%>
<%@page import="net.board.action.BoardContent"%>
<%@page import="net.board.db.BoardDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.board.action.BoardAddPost"%>
<%@page import="net.board.db.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.wrap{
	padding-top: 100px;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
	$(document).ready(function () {	
		$('.commentButton').on('click', function () {
			$('.comContent').show();
		});
// 		alert($('.comContent').val());
	});
	function deleteConfirm(num){
// 		alert(num);
		if(confirm("삭제하시겠습니까?")){
			location.href='delete.bo?num='+num;
		}
	}
</script>
<title>Insert title here</title>
</head>
<body>

<%
request.setCharacterEncoding("utf-8");
String memberEmail = (String)session.getAttribute("memberEmail");
String nickName = (String)session.getAttribute("nickName");
String realFolder = request.getServletContext().getRealPath("image\\"+nickName);
System.out.println(realFolder);
%>
<div class="wrap">
<form action="modify.bo" method="post">
	
	<table>
		<tr>
			<td>작성자</td>
			<td>${bdto.nickName }</td>	<!-- bdto.setNikName()으로 세팅한 값을 getNickName()을 사용하여 표현식으로 나타낸다 -->
		</tr>
	
		<tr>
			<td>제목</td>
			<td>${bdto.title }</td>		<!-- bdto.setTitle()으로 세팅한 값을 getTitle()을 사용하여 표현식으로 나타낸다  -->
		</tr>
	
		<tr>
			<td>올린날짜</td>
			<td><f:formatDate value="${bdto.date}" pattern="yyyy-MM-dd"/></td>		<!-- bdto.setDate()으로 세팅한 값을 getDate()을 사용하여 표현식으로 나타낸다  -->
		</tr>
		<c:if test="${bdto.file!=null }">
		<tr>
			<td>파일</td>
			<td><a href=""><img src="image/${bdto.nickName }/${bdto.file }"/></a></td>
		</tr>
		</c:if>
		<tr>
			<td>글내용</td>
			<td>${bdto.content }</td>	<!-- bdto.setContent으로 세팅한 값을 getContent을 사용하여 표현식으로 나타낸다  -->
		</tr>	
		<tr>
		<c:if test="${nickName == bdto.nickName }">
			<!-- 클릭했을 때 update.bo로 bdto.getNum()을 사용하여  num값을 넘겨준다.
				 클릭한 글을 식별?하기 위해 num값을 넘겨준다.
			-->
			<td><input type="button" value="글수정" onclick="location.href='update.bo?num=${bdto.num}'"></td>
			<!-- 클릭했을때 delete.bo로 bdto.getNum()을 사용하여 num값을 넘겨준다.
				 클릭한 글을 식별?하기 위해 num값을 넘겨준다.
			 -->
			<td><input type="button" value="글삭제" onclick="deleteConfirm(${bdto.num})"></td>
		</c:if>
			<!-- 클릭했을 때 test.bo를 실행한다. -->
			<td><input type="button" value="목록보기" onclick="location.href='boardList.bo'"></td>
		</tr>
		</table>
	</form>	
	
	<!-- 클릭했을때 클릭한 게시물에 대하여 댓글을 작성하기 위해 게시글의 num값을 넘겨준다. -->
	<form action="comm	entInsert.bo" method="post">
	<input type="hidden" name="num" value=${bdto.num }>
	<!-- session.getAttribute를 사용하여 세션영역에 있는 닉네임값을 가져온다.
		 히든속성을 사용 -> get방식으로 붙여 사용할 수 있는데 코드가 보기 싫어져서 hidden으로 풀어 사용
		 nickName값이 필요하지만 입력받는 부분이 없기때문에 hidden으로 넘겨준다
	-->
	<input type="hidden" name="nickName" value="${nickName }">
	<table>
		<tr>
			<td colspan="3"><h3>댓글목록</h3></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>댓글 내용</td>
			<td>작성날짜</td>
		</tr>
		<c:forEach var="c" items="${commentList }">
		<tr>
			<td>${c.nickName }</td>
			<td>${c.content }</td>
			<td><f:formatDate value="${c.date }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<c:if test="${c.nickName == nickName }">
			<td><a href="deleteComment.bo?cNum=${c.cNum}&num=${c.num}">[삭제]</a></td>
			</c:if>
		</tr>
		</c:forEach>		
		<tr>
			<td><input type="button" class="commentButton" value="댓글쓰기"></td>
		</tr>
		<tr>
			<!-- request영역으로 값을 전달하기 위해 name을 사용한다. form에서 전송방식 확인-->
			<td><input type="text" name="comContent" class="comContent" style="display: none;"></td>	
			<!-- 
				전송방식 : get전송방식 => ?num(키값)=<bdto의 num값(value값)>
			 	게시글에 대한 댓글을 달기 위해 게시글의 num값을 전달한다.
			 -->
			<td><input type="submit" value="댓글쓰기" class="comContent" style="display: none;"></td>
		</tr>
	</table>
	</form>
</div>
</body>
</html>