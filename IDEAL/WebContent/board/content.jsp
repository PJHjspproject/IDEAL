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
<link rel="stylesheet" href="./css/board1.css">
<style type="text/css">

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
<div class="mainconwrap">
<form action="modify.bo" method="post">
	
	<table class="tablebocon">

		<tr class="boardtrno1">
			<td colspan="5" class="tdname" colspan="2"><h1><em>${bdto.title }</em></h1></td>		<!-- bdto.setTitle()으로 세팅한 값을 getTitle()을 사용하여 표현식으로 나타낸다  -->
		</tr>
		<tr class="boardtrno1">
			<td colspan="5" class="tdright"><f:formatDate value="${bdto.date}" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;<b>${bdto.nickName }&nbsp;님</b></td>		<!-- bdto.setDate()으로 세팅한 값을 getDate()을 사용하여 표현식으로 나타낸다  -->
		</tr>
		<c:if test="${bdto.file!=null }">
		<tr class="boardtrno1">
			<td colspan="5"><a href="none" class="conimg"><img class="bo_conimg" src="image/${bdto.nickName }/${bdto.file }"/></a></td>
		</tr>
		</c:if>
		<tr class="boardtrno1">
			<td colspan="5" class="tdcontent">${bdto.content }</td>	<!-- bdto.setContent으로 세팅한 값을 getContent을 사용하여 표현식으로 나타낸다  -->
		</tr>	
		<tr class="boardtrno1">
		<td colspan="5">
		<c:if test="${nickName == bdto.nickName }">
			<!-- 클릭했을 때 update.bo로 bdto.getNum()을 사용하여  num값을 넘겨준다.
				 클릭한 글을 식별?하기 위해 num값을 넘겨준다.
			-->
			<input type="button" value="글수정" class="tolist1" onclick="location.href='update.bo?num=${bdto.num}'">
			<!-- 클릭했을때 delete.bo로 bdto.getNum()을 사용하여 num값을 넘겨준다.
				 클릭한 글을 식별?하기 위해 num값을 넘겨준다.
			 -->
			<input type="button" value="글삭제" class="tolist1" onclick="deleteConfirm(${bdto.num})">
		</c:if>
			<!-- 클릭했을 때 test.bo를 실행한다. -->
			<input type="button" value="목록보기" class="tolist1" onclick="location.href='boardList.bo'">
			</td>
		</tr>
		</table>
	</form>	
</div>
<div class="replyconwrap">
	<!-- 클릭했을때 클릭한 게시물에 대하여 댓글을 작성하기 위해 게시글의 num값을 넘겨준다. -->
	<form action="commentInsert.bo" method="post">
	<input type="hidden" name="num" value=${bdto.num }>
	<!-- session.getAttribute를 사용하여 세션영역에 있는 닉네임값을 가져온다.
		 히든속성을 사용 -> get방식으로 붙여 사용할 수 있는데 코드가 보기 싫어져서 hidden으로 풀어 사용
		 nickName값이 필요하지만 입력받는 부분이 없기때문에 hidden으로 넘겨준다
	-->
	<input type="hidden" name="nickName" value="${nickName }">
	<table class="tablereply">
		<tr>
			<td colspan="4"><h3 class="h3css">댓글목록</h3></td>
		</tr>
		<c:forEach var="c" items="${commentList }">
		<c:if test="${c.nickName != nickName }"> <!--bdto.nickName:게시자 기준 오른쪽 정렬 / nickName:세션회원 기준 오른쪽 정렬  -->
		<tr class="replytr0">
			<td colspan="4">
			<ul class="ulwidth">
			<li><h2>${c.nickName }</h2></li>
			<li> ${c.content }</li>
			<c:if test="${c.nickName == nickName }">
			<a href="deleteComment.bo?cNum=${c.cNum}&num=${c.num}">삭제</a>
			</c:if>
			<li class="dateli"><f:formatDate value="${c.date }" pattern="yyyy-MM-dd HH:mm:ss"/></li>
			</ul></td>
		</tr>
		</c:if>
		<c:if test="${c.nickName == nickName }">
		<tr class="replytr1">
			<td colspan="4" class="tdcol">
			<ul class="ulwidthme">
			<li><h2>${c.nickName }</h2></li>
			<li> ${c.content }</li>
			<li class="datelime">
			<c:if test="${c.nickName == nickName }">
			<a href="deleteComment.bo?cNum=${c.cNum}&num=${c.num}">삭제</a>
			</c:if>
			<f:formatDate value="${c.date }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</li>
			</ul></td>
		</tr>
		</c:if>
		</c:forEach>		
		<tr>
			<td colspan="4">
			<input type="button" class="commentButton cCbt" value="댓글쓰기"></td>
		</tr>
		<tr>
			<!-- request영역으로 값을 전달하기 위해 name을 사용한다. form에서 전송방식 확인-->
			<td colspan="3"><input type="text" name="comContent" class="comContent cC01" style="display: none;"></td>	
			<!-- 
				전송방식 : get전송방식 => ?num(키값)=<bdto의 num값(value값)>
			 	게시글에 대한 댓글을 달기 위해 게시글의 num값을 전달한다.
			 -->
			<td colspan="1" class="inputbottom"><input type="submit" value="댓글입력" class="comContent cC02" style="display: none;"></td>
		</tr>
	</table>
	</form>
	</div>
	
</div>
</body>
</html>