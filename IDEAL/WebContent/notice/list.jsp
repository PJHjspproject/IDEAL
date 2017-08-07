<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<!-- <link href="aie.css" type="text/css" rel="stylesheet"> -->
<style type="text/css">
.tableAll{
border: 1px solid;
border-collapse: collapse;
}
.tableAll td{
text-align: center;
}
.tableAll tr.boardtr{
	background-image: url("img/boardbg.jpg");
	background-repeat: repeat-x;
	border: 1px solid;
	height: 40px;
	letter-spacing: 0.1em;
}
.tableAll td{
 border-bottom: 1px dotted;
}
div.wrap{
	padding: 300px;
}
</style>
</head>
<body>
<div class="wrap">
<center>
<table width="1028px" class="tableAll">
	<tr class="boardtr">
		<th width="772px">제목</th>
		<th width="150px">등록일</th>
	</tr>
	<c:forEach var="v" items="${arry}">
	<tr onclick="location.href='./NoticeContent.no?noticeNum=${v.noticeNum}'">
		<td>${v.noticeSubject}</td>
		<td><d:formatDate value="${v.noticeDate}" pattern="yyyy-MM-dd"/></td>
	</tr>
	</c:forEach>
</table>
</center>	

</div>
</body>
</html>