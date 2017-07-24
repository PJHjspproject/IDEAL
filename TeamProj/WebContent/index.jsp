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
<c:set var="center" value="${param.center}"/>
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
				<!-- Footer 부분 인크루드 해야합니다. -->
			</td>
		</tr>
	
	</table>
</center>
</body>
</html>
    
