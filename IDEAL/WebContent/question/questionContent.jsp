<%@page import="net.member.db.MemberDTO"%>
<%@page import="net.question.db.questionDto"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.tableAll{
border: none;
border-collapse: collapse;
}

.tableAll tr.boardtr{
	background-image: url("img/boardbg.jpg");
	background-repeat: repeat-x;
/* 	border: 1px solid; */
/* 	color: white; */
	height: 40px;
	letter-spacing: 0.1em;
}
.tableAll td{
border: none;
}
#qucore{
background-image: url("img/boardbg.jpg");
background-repeat: repeat-x;
color: white;
height: 30px;
letter-spacing: 0.1em;


}
.wrap input{
float: right;
margin-left: 10px;
width: 150px;
}
.wrap{
padding-top: 100px;
}
.clear{
	clear:both;
}

</style>

<%

String memberEmail = (String)session.getAttribute("memberEmail");
String nickName = (String)session.getAttribute("nickName");
String realFolder = request.getServletContext().getRealPath("image\\"+nickName);
System.out.println("사진폴더 경로"+realFolder);

%>
</head>

<body>

	<div class="wrap">
	<center>
		<table width="1028px" class="tableAll">
			<tr class="boardtr">
				<th>${qdto.num }</th>
				<th>${qdto.title }</th>
				<th>${qdto.nickName }</th>
				<th><d:formatDate value="${qdto.inputDate}" pattern="yyyy-MM-dd"/> </th>
			</tr>
			<tr>
				<td colspan="4" class="txtarere">${qdto.content}</td>
			</tr>
			<tr>
				<td>
					<c:if test="${qdto.image!=null }">
						<img src="image/${qdto.nickName}/${qdto.image}">
					</c:if>
				</td>
			</tr>
			<c:if test="${qdto.questionStatement == true}">
			<tr>
				<th colspan="4" id="qucore">답변내용</th>
			</tr>
			<tr>
				<td colspan="4"><textarea rows="5" cols="100" readonly="readonly" style="resize:none;" class="txtarere">${qdto.reContent }</textarea></td>
			</tr>
			
			</c:if>
			
				
			
			<tr>
				<td colspan="4">
					<input type="button" value="목록으로" onclick="location.href='question.qU'">
					<input type="button" value="삭제" onclick="location.href='deletequestion.qU?num=${qdto.num }'">
					<c:if test="${qdto.questionStatement==false }"><input type="button" value="수정" onclick="location.href='updatequestion.qU?num=${qdto.num}'"></c:if>
				</td>
			</tr>
		</table>
		<div class="clear"></div>
		</center>
	</div>
</body>
</html>

<%-- <style type="text/css">
.tableAll{
border: none;
border-collapse: collapse;
}

.tableAll tr.boardtr{
	background-image: url("img/boardbg.jpg");
	background-repeat: repeat-x;
	border: 1px solid;
	color: white;
	height: 40px;
	letter-spacing: 0.1em;
}
.tableAll td{
border: none;
}
#qucore{
background-image: url("img/boardbg.jpg");
background-repeat: repeat-x;
color: white;
height: 30px;
letter-spacing: 0.1em;


}
.wrap input{
float: right;
margin-left: 10px;
width: 150px;
}
.wrap{
padding-top: 100px;
}

</style>

<%

String memberEmail = (String)session.getAttribute("memberEmail");
String nickName = (String)session.getAttribute("nickName");
String realFolder = request.getServletContext().getRealPath("image\\"+nickName);
System.out.println(realFolder);

%>
</head>

<body>

	<div class="wrap">
	<center>
		<table width="1028px" class="tableAll">
			<tr class="boardtr">
				<th>${qdto.num }</th>
				<th>${qdto.title }</th>
				<th>${qdto.nickName }</th>
				<th><d:formatDate value="${qdto.inputDate}" pattern="yyyy-MM-dd"/> </th>
			</tr>
			<tr>
				<td colspan="4" class="txtarere">${qdto.content}</td>
			</tr>
			<tr>
				<td><img src="image/${qdto.nickName}/${qdto.image}"></td>
			</tr>
			<c:if test="${qdto.questionStatement == true}">
			<tr>
				<th colspan="4" id="qucore">답변내용</th>
			</tr>
			<tr>
				<td colspan="4"><textarea rows="5" cols="100" readonly="readonly" style="resize:none;" class="txtarere">${qdto.reContent }</textarea></td>
			</tr>
			
			</c:if>
			
				
			
			<tr>
				<td colspan="4"><input type="button" value="목록으로" onclick="location.href='question.qu'">
								<input type="button" value="삭제" onclick="location.href='deletequestion.qu?num=${qdto.num }'">
				</td>
			</tr>
		</table>
		
		</center>
	</div>
</body>
</html> --%>