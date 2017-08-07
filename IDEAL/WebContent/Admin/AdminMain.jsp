<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<h1>관리자페이지입니다</h1>
<input type="button" value="투자요청심사" onclick="location.href='AdminInvestRequest.ad'">
<input type="button" value="가입회원조회" onclick="location.href='AdminMemberList.ad'">
<input type="button" value="1:1문의글현황" onclick="location.href='AdminQuestion.ad'">
<input type="button" value="FAQ" onclick="location.href='AdminInformationUse.ad'">
<input type="button" value="카드금액결제" onclick="location.href='AdminCardPayment.ad'"><!-- 안됨 -->
<input type="button" value="공지사항" onclick="location.href='AdminNotice.ad'">
<input type="button" value="게시판조회" onclick="location.href='AdminBoardList.ad'">
<br><br><br><br><br><br><br><br><br><br>
<input type="button" value="메인페이지로" onclick="location.href='../main1.jsp'">
</div>
</body>
</html>