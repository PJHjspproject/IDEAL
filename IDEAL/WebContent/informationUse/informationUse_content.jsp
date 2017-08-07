<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<!-- <link href="aie.css" type="text/css" rel="stylesheet"> -->
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
.wrap{
padding-top: 100px;
}
</style>
</head>
<body>
<div class="wrap">
<center>
<table width="922px" class="tableAll">
	<tr class="boardtr">
		<th>${dto.infoTitle}</th>
	</tr>
	<tr>
		<td><img src="informationUse/image/${dto.infoImage }"></td>
	</tr>
	<tr>
		<td class="txtarere">
		${dto.infoContent}
		
		</td>
	</tr>
	<tr>
		<td><input type="button" value="목록" onclick="location.href='Information.iU'"></td>
	</tr>
</table>

</center>

</div>
<div style="clear: both;"></div>
</body>
</html>