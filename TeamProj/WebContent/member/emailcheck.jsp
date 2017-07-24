<%@page import="net.member.db.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/default.css"><!-- 약속된 css-->
<title>Insert title here</title>
<%
	request.setCharacterEncoding("UTF-8");

	String email = request.getParameter("email");
	MemberDAO dao = new MemberDAO();
	boolean check = dao.CheckMember(email);
%>
</head>
<body>
	<%
		if (check == false) {
	%>
	<table width="270" border="0" cellspacing="0" cellpadding="5">
		<tr>
			<td><%=email%>은 이미 사용중인 이메일입니다.</td>
		</tr>
	</table>
	<form name="checkForm" method="post" action="emailcheck.jsp">
		<table width="270" border="0" cellspacing="0" cellpadding="5">
			<tr>
				<td align="center">다른 아이디를 선택하세요.
					<p>
						<input type="text" size="25" maxlength="30" name="email">
						<input type="submit" value="ID중복확인">
				</td>
			</tr>
		</table>
	</form>
	<%
		} else {
	%>
	<table width="270" border="0" cellspacing="0" cellpadding="5">
		<tr>
			<td align="center">
				<p>
					입력하신
					<%=email%>
					는 사용하실 수 있는 Email입니다.
				</p> <input type="button" value="닫기" onclick="setid()">
			</td>
		</tr>
	</table>
	<%
		}
	%>
<script type="text/javascript">
	function setid() {
		opener.document.getElementById("email").value = "<%=email%>";
		opener.document.getElementById("email").setAttribute("readonly", "readonly");
		self.close();
	}
</script>


</body>
</html>