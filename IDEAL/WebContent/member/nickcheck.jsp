<%@page import="net.member.db.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	request.setCharacterEncoding("UTF-8");
	
	String nickName = request.getParameter("nickName");
	String result = request.getParameter("result");
	MemberDAO dao = new MemberDAO();
	boolean check = dao.NickCheck(nickName);
	System.out.println("dao 실행후 check 값 :"+check);
%>
<body>
	<%
		if (check == false) {
	%>
	<table width="270" border="0" cellspacing="0" cellpadding="5">
		<tr>
			<td><%=nickName%>은 이미 사용중인 닉네임입니다.</td>
		</tr>
	</table>
	<form name="checkForm" method="post" action="nickcheck.jsp">
		<table width="270" border="0" cellspacing="0" cellpadding="5">
			<tr>
				<td align="center">다른 닉네임를 선택하세요.
					<p>
						<input type="text" size="25" maxlength="30" name="nickName">
						<input type="submit" value="닉네임중복확인">
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
					<%=nickName%>
					는 사용하실 수 있는 nickName입니다.
				</p> <input type="button" value="닫기" onclick="setnick()">
			</td>
		</tr>
	</table>
	<%
		}
	%>
<script type="text/javascript">
	function setnick() {
		opener.document.getElementById("result").value=1;
		opener.document.getElementById("nickName").value = "<%=nickName%>";
		opener.document.getElementById("nickName").setAttribute("readonly", "readonly");
		self.close();
	}
</script>


</body>	
	
</html>