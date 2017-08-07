<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<style type="text/css">
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
.wrap input{
float: right;
width: 150px;
}
.dotttd{
border-bottom: 1px dotted;
}
.wrap{
padding-top: 100px;
}
</style>
</head>
<body>
<div class="wrap">
<center>
<table width="922px" class="tableAll" border="0" >
	<tr class="boardtr" style="margin-top: 100px;">
		<th width="772px">${dto.noticeSubject}</th>
		<th width="150px"><d:formatDate value="${dto.noticeDate}" pattern="yyyy-MM-dd"/> </th>
	</tr>
	<tr>
		<td>글 이미지 :<img src="notice/image/${dto.noticeImg}"></td>
	</tr>
	<tr>
		<td colspan="2" class="dotttd">${dto.noticeContent }</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" value="목록" onclick="location.href='list.no'">
		</td>
	</tr>
</table>

</center>	

</div>
</body>
</html>