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
<link rel="stylesheet" href="./css/question1.css">

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
	<h4 class="subh4">■ IDEAL 1:1문의</h4>
		<table width="1028px" class="tableAllqc">
			<tr class="boardqctr1">
				<th class="qcth1">${qdto.num }</th>
				<th class="qcth1">${qdto.title }</th>
				<th class="qcth1">${qdto.nickName }</th>
				<th class="qcth1"><d:formatDate value="${qdto.inputDate}" pattern="yyyy-MM-dd"/> </th>
			</tr>
			<tr>
				<td class="tdendboard" colspan="4" class="txtarere">${qdto.content}</td>
			</tr>
			<tr>
				<td width="1028px" colspan="4">
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
				<td width="1028px" colspan="4">
				<textarea rows="5" cols="100" readonly="readonly" style="resize:none;" class="txtarere2">${qdto.reContent }</textarea></td>
			</tr>
			
			</c:if>
			
				
			
			<tr>
				<td colspan="4">
					<input class="tolist1" type="button" value="목록으로" onclick="location.href='question.qU'">
					<input class="delete1" type="button" value="삭제" onclick="location.href='deletequestion.qU?num=${qdto.num }'">
					<c:if test="${qdto.questionStatement==false }"><input class="modify1" type="button" value="수정" onclick="location.href='updatequestion.qU?num=${qdto.num}'"></c:if>
				</td>
			</tr>
		</table>
		<div class="clear"></div>
		</center>
	</div>
</body>
</html>
