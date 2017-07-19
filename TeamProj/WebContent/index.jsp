<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 센터 공간은 사용자로 부터 계속해서 정보가 바뀌는 부분이기에 해당 Center.jsp의 정보를 읽어 들여야 한다. -->
<c:set var="center" value="${param.center}"/>
<!-- 센터 공간은 사용자로 부터 계속해서 정보가 바뀌는 부분이기에 해당 Center.jsp의 정보를 읽어 들여야 한다. -->
<!--처음 CarMain.jsp를 실행하면  당연히 .. param.center값은 받아 올수 없기에 조건 주기   -->
<c:if test="${center == null }">
	<c:set var="center" value="center/center.jsp"/>
</c:if>

<center>
	<table  width="100%" height="700" >
		<tr align="center">
			<td>
				<jsp:include page="topnav/Top.jsp"/>
			</td>
			
		</tr>
		<tr>
			<td height="500">
				<jsp:include page="${center}"/>
			</td>
		</tr>
		<tr>
			<td>
				
			</td>
		</tr>
	
	</table>
</center>





</body>
</html>
    
