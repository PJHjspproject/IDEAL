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
<div align="center">
<c:if test="${mdto != null }">

		<h1>회원정보</h1>
		이메일 : ${mdto.memberEmail}<br>
		이름 : ${mdto.name }<br>
		비밀번호 : ${mdto.pass }<br>
		생일 : ${mdto.birth }<br>
		핸드폰 번호 : ${mdto.phone }<br>
		별명 : ${mdto.nickName }<br>
	
		<h2>투자요청한 글 목록</h2>
		<c:if test="${irlist.isEmpty() }">
			<h3>투자 요청한 글이 없습니다.</h3>
		</c:if>
		<c:if test="${!irlist.isEmpty() }">
			<h3>투자요청한 글 목록입니다.</h3>
			<table width="1000">
				<tr>
					<td>투자요청번호</td>
					<td>프로그램명</td>
					<td>대표자이름</td>
					<td>시작일</td>
					<td>종료일</td>
					<td>금액교부일</td>
					<td>썸네일이미지</td>
					<td>펀딩진행상황</td>
					<td>퍼미션 체크</td>
				</tr>
				<c:forEach var="ir" items="${irlist}">
				<tr onclick="location.href='AdminInvestRequestList.ad?memberEmail=${ir.memberEmail}'">
					<td>${ir.investRequestNum }</td>
					<td>${ir.program }</td>
					<td>${ir.DName }</td>
					<td><f:formatDate value="${ir.startDay }" pattern="yyyy-MM-dd"/></td>
					<td><f:formatDate value="${ir.endDay }" pattern="yyyy-MM-dd"/></td>
					<td><f:formatDate value="${ir.payDay }" pattern="yyyy-MM-dd"/></td>
					<td>${ir.thumbImage}</td>
					<td>
					<c:choose>
						<c:when test="${ir.fundSituation eq 1 }">
							기간 미완료 / 펀딩 80%미만 (진행중)
						</c:when>
									
						<c:when test="${ir.fundSituation eq 2}">
							기간 완료  / 펀딩 80% 미만 (실패)
						</c:when>
									
						<c:when test="${ir.fundSituation eq 3}">
							기간 미완료 / 펀딩 100%달성 (조기성공)
						</c:when>
										
						<c:when test="${ir.fundSituation eq 4}">
							기간 완료 / 펀딩 80%이상 100%이하 (성공)											
						</c:when>
					</c:choose>
					</td>
					<td>${ir.permitChk}</td>
				</tr>
				</c:forEach>
			</table>
		</c:if>
		<h2>투자한 글 목록</h2>
		<c:if test="${imlist.isEmpty() }">
			<h3>투자한 글이 없습니다.</h3>
		</c:if>
		<c:if test="${!imlist.isEmpty() }">
			<h3>투자한 글 목록 입니다.</h3>
			<table width="1000">
				<tr>
					<td>투자번호</td>
					<td>투자요청번호</td>
					<td>투자금액</td>
					<td>프로그램명</td>
				</tr>
				<c:forEach var="im" items="${imlist }">
				<tr onclick="location.href='AdminInvestMentList.ad?memberEmail=${im.memberEmail}'">
					<td>${im.investNum }</td>
					<td>${im.investRequestNum }</td>
					<td>${im.investMoney }</td>
					<td>${im.program }</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<h2>게시판에 쓴 글 목록</h2>
		<c:if test="${blist.isEmpty() }">
			<h3>게시판 글이 없습니다.</h3>
		</c:if>
		<c:if test="${!blist.isEmpty() }">
			<h3>게시판 글 목록입니다.</h3>
			<table width="1000">
				<tr>
					<td>글번호</td>
					<td>글제목</td>
					<td>올린파일</td>
					<td>올린날짜</td>
				</tr>
			<c:forEach var="b" items="${blist }">
				<tr onclick="location.href='AdminBoardList.ad?nickName=${b.nickName}'">
					<td>${b.num }</td>
					<td>${b.title }</td>
					<td>${b.file }</td>
					<td><f:formatDate value="${b.date }" pattern="yyyy-MM-dd"/></td>
					</tr>
			</c:forEach>
			</table>
		</c:if>
	
		<h2>회원이 쓴 댓글 목록</h2>
		<c:if test="${rlist.isEmpty() }">
			<h3>회원이 쓴 댓글이 없습니다.</h3>
		</c:if>
		<c:if test="${!rlist.isEmpty() }">
			<h3>회원이 쓴 댓글 목록입니다.</h3>
			<table width="1000">
				<tr>
					<td>게시판글번호</td>
					<td>댓글번호</td>
					<td>댓글내용</td>
					<td>글쓴날짜</td>
				</tr>
			<c:forEach var="c" items="${clist}">
				<tr onclick="location.href='AdminCommentList.ad?nickName=${c.nickName}'">
					<td>${c.num }</td>
					<td>${c.cNum }</td>
					<td>${c.content}</td>
					<td><f:formatDate value="${c.date }" pattern="yyyy-MM-dd"/></td>
					</tr>
			</c:forEach>
			</table>
		</c:if>
			
	<c:if test="${qlist.isEmpty() }">
		<h2>1:1문의글이 없습니다.</h2>
	</c:if>
	<c:if test="${!qlist.isEmpty() }">
		<h2>1:1문의글이 있습니다.</h2>
		<table width="1000">
			<tr>
				<td>글번호</td>
				<td>글제목</td>
				<td>글쓴날짜</td>
			</tr>	
		<c:forEach var="q" items="${qlist }">
			<tr onclick="location.href='AdminQuestion.ad?nickName=${mdto.nickName}'">
				<td>${q.num }</td>
				<td>${q.title }</td>
				<td><f:formatDate value="${q.inputDate }" pattern="yyyy-MM-dd"/></td>
				</tr>
		</c:forEach>
		</table>
	</c:if>	

</c:if>
</div>
</body>
</html>