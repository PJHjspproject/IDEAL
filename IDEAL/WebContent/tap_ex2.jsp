<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	String memberEmail = (String) session.getAttribute("memberEmail");
	String nickName = (String) session.getAttribute("nickName");

%>
<style type="text/css">
/* .downplz{ */
/* 	padding: 200px; */
/* } */
	
</style>
<script src="https://code.jquery.com/jquery-3.2.1.js"
	integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
	crossorigin="anonymous"></script>
<script type="text/javascript">
$(document).ready(function () {
	var memberEmail = '<%=memberEmail%>';
	$('.btn1').on('click', function () {
		$('.info').submit();
	});
	$('.btn2').on('click', function () {
		$('.notice').submit();
	});
	$('.btn3').on('click', function () {
		if(memberEmail == 'null'){
			$('a.login-window').trigger("click");
			return false;
		}
		$('.question').submit();
	});
});

</script>
<title>Insert title here</title>
</head>
<body>
<div class="downplz">
<jsp:include page="informationUse/Info.jsp"/>
<script>
<%-- 	var memberEmail = "<%=memberEmail%>"; --%>
	var nickName = "<%=nickName%>";
	window.channelPluginSettings = {
			"plugin_id": "3fb98d16-18fd-4539-95b3-cccb3bedbc8c"    
	 };
  if(memberEmail != "null"){
// 	  alert(memberEmail);
		  window.channelPluginSettings.user = {
		      "id": memberEmail,
		      "name": nickName
		    }
  }
  (function() {
    var node = document.createElement('div');
    node.id = 'ch-plugin';
    document.body.appendChild(node);
    var async_load = function() {
      var s = document.createElement('script');
      s.type = 'text/javascript';
      s.async = true;
      s.src = '//cdn.channel.io/plugin/ch-plugin-web.js';
      s.charset = 'UTF-8';
      var x = document.getElementsByTagName('script')[0];
      x.parentNode.insertBefore(s, x);
    };
    if (window.attachEvent) {
      window.attachEvent('onload', async_load);
    } else {
      window.addEventListener('load', async_load, false);
    }
  })();
</script>
<%
String centern = request.getParameter("centern");
if(centern!=null){
%>
	<jsp:include page="<%=centern%>"/>
<%
}%>
</div>
</body>
</html>