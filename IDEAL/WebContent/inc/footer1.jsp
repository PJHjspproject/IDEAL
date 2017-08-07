
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/footer1.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
 <div id="foot_back">
  <div id="foot_container"> 
	<div id="wrap3">
		<div id="f_logo">
			<img src="img/logo.png">
		</div>



		<div id="f_table">
			<div class="f_b">
				<a class="mail_map" onclick="PrivacyInfo()">정보취급방침</a>
				<script type="text/javascript">
				function PrivacyInfo() {
					var url = "informationUse/PrivacyInfo.jsp";
					window.open(url, "개인정보취급방침", "width=700,height=740");
				}
			</script>
				<script type="text/javascript">
					function map() {
						var url = "inc/maptest.jsp";
						window.open(url, "본사 위치",
								"top=0,right=0,width=800,height=800");
					}
				
				</script>
				<br /> <a class="mail_map" onclick="payInfo()">수수료 안내</a>
				<script type="text/javascript">
					function payInfo() {
						var url = "informationUse/PayInfo.jsp";
						window.open(url, "수수료 안내", "width=700,height=740");
					}
				</script>
				<br /> <a class="mail_map" onclick="UsingInfo()">사업이용약관</a>
				<script type="text/javascript">
					function UsingInfo() {
						var url = "informationUse/UsingInfo.jsp";
						window.open(url, "이용약관", "width=700,height=740");
					}
				</script>
			</div>
		</div>

		<div id="clear"></div>
		<div class="f_b2">
			<p>제작멤버소개</p>
			<p>회사소개연혁</p>
			<p>절차진행과정</p>
		</div>



		<div class="f_a">
			<p>부산광역시 진구 부전동 아이티대로 헥사 빌딩 54층 5401호</p>
			<img src="img/noun1.png"
				style="width: 30px; height: 30px; color: #fff;"><a href="#">051-123-4567</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="img/noun2.png" style="width: 31px; height: 31px;"><a
				class="mail_map" onclick="mailtoadmin();">admin@hexafund.co.kr</a>
			<script type="text/javascript">
				function mailtoadmin(){
					var url = "inc/mailtoadmin.jsp";	
				window.open(url, "email to 관리자","width=500,height=540");	
				}							
				</script>
			<br /> <small class="copyright">Copyright © investment</small>
		</div>

		<div id="f_info">
			<aside class="social-links">
			<ul>
				<li class="item-twitter"><a href="https://twitter.com"
					target="_blank"> <img src="img/tw.jpg">
				</a></li>
				<li class="item-facebook"><a href="https://www.facebook.com"
					target="_blank"> <img src="img/fb.jpg">
				</a></li>
				<li class="endtd"><a class="mail_map" onclick="map()"><img
						src="img/gmap.jpg"></a></li>
				<li><a class="mail_map" onclick="mailtoadmin()"> <img
						src="img/mail1.jpg"></a></li>


			</ul>
			</aside>
		</div>
		<div class="f_c">
			<img src="img/12.jpg">
		</div>
	   </div>
      </div>
	</div>


</body>
</html>