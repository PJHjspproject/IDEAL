
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="member/css/mystyle.css"/>
<%
/* 세션 작업 필요!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
String session = (String)request.getAttribute("session"); */
%>
</head>
<body>
	<div id="wrap">
	<footer>
			
			<table id="f_table">
				<tr>
					<td rowspan="3" >
						<a href="#"><img src="img/IdealLogo.png">
						</a>
					</td>
					<td>
					<a class="mail_map" onclick="infoUsing()">개인정보 취급방침</a>
				<script type="text/javascript">
				function infoUsing(){
					var url = "info/infoUsing.jsp";					
				window.open(url, "이용약관","width=700,height=740");	
				}							
				</script>
					</td>
					<td>
					<a href="#">c1</a>
					</td>
					<td>
					<a href="#">d1</a>
					</td>
					<td>
					<a href="#">e1</a>
					</td>
					<td class="endtd" rowspan="3" >
					<a class="mail_map" onclick="map()"><img src="img/Gmapicon.png"></a>
				<script type="text/javascript">
				function map(){
					var url = "maptest.jsp";
				window.open(url, "본사 위치","top=0,right=0,width=800,height=800");	
				}							
				</script><br/>
					</td>
				</tr>
				<tr>
					<td>
					<a class="mail_map" onclick="infoUsing()">수수료 안내</a>
				<script type="text/javascript">
				function infoUsing(){
					var url = "info/infoUsing.jsp";					
				window.open(url, "이용약관","width=700,height=740");	
				}							
				</script>
					</td>
					<td>
					<a href="#">c2</a>
					</td>
					<td>
					<a href="#">d2</a>
					</td>
					<td>
					<a href="#">e2</a>
					</td>
				</tr>
				<tr>
					<td>
					<a class="mail_map" onclick="infoUsing()">이용약관</a>
				<script type="text/javascript">
				function infoUsing(){
					var url = "info/infoUsing.jsp";					
				window.open(url, "이용약관","width=700,height=740");	
				}							
				</script>
					</td>
					<td>
					<a href="#">c3</a>
					</td>
					<td>
					<a href="#">d3</a>
					</td>
					<td>
					<a href="#">e3</a>
					</td>
				</tr>
				
			</table>
			<div id="f_info">
				<p>부산 광역시 진구 부전동 헥사 빌딩 54층 5401호</p>
				전화번호 : <a href="#">051-123-4567</a>&nbsp;&nbsp;&nbsp;&nbsp;
				관리자 메일 보내기 :<a class="mail_map" onclick="mailtoadmin()">admin@hexafund.co.kr</a>
				<script type="text/javascript">
				function mailtoadmin(){
					var url = "email/mailtoadmin.jsp";	
				window.open(url, "email to 관리자","width=500,height=540");	
				}							
				</script><br/>
				Copyright ⓒ 2016 hexa Investment & Securities Co., Ltd. All rights reserved.

			</div>
	</footer>
	</div>





</body>
</html>