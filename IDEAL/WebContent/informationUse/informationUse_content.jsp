<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link href="./css/infouse.css" type="text/css" rel="stylesheet">

</head>
<body>
<div class="wrap">
<center>
<h4 class="subh4">■ IDEAL 이용안내사항</h4>
<table width="1028px" class="tableinfouse">
	<tr class="boardtr">
		<th class="iuth1">${dto.infoTitle}</th>
	</tr>
	<tr>
		<td><p class="imgpad">[Image]</p><br/>
		<img class="no_conimg" src="informationUse/image/${dto.infoImage }"></td>
	</tr>
	<tr>
		<td class="txtarere dotttd">
		${dto.infoContent}
		
		</td>
	</tr>
	<tr>
		<td><input class="tolist1" type="button" value="목록" onclick="location.href='Information.iU'"></td>
	</tr>
</table>

</center>

</div>
<div style="clear: both;"></div>
</body>
</html>