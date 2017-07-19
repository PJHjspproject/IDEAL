<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
<%
	String id = (String)session.getAttribute("id");
	
	if(id == null){ //세션값이 없는 경우 
%>
	<div id="login">
		<a href="../member/login.jsp">login</a> | 
		<a href="../member/join.jsp">join</a>
	</div>
<%		
	}else{ //세션값이 있는 경우 
%>		
	<div id="login">
		<%=id %>님....... 반갑습니다. 
		<a href="../member/logout.jsp">logout</a>
	</div>
<%		
	}
%>

<div class="clear"></div>
<!-- 로고들어가는 곳 -->
<div id="logo"><img src="../images/logo.gif" width="265" height="62" alt="Fun Web"></div>
<!-- 로고들어가는 곳 -->
<nav id="top_menu">
<ul>
	<li><a href="../index.jsp">HOME</a></li>
	<li><a href="../company/welcome.jsp">COMPANY</a></li>
	<li><a href="#">SOLUTIONS</a></li>
	<li><a href="../center/notice.jsp">CUSTOMER CENTER</a></li>
	<li><a href="#">CONTACT US</a></li>
</ul>
</nav>
</header>