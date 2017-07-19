<%@page import="javax.mail.Authenticator"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.Address"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Session"%>
<%@page import="email.SMTPAuthenticator"%>
<%@page import="java.util.Properties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<% 
	request.setCharacterEncoding("UTF-8");
	
	StringBuffer buffer = new StringBuffer();
	for (int i=0; i<=6;i++){
	int n=(int)(Math.random()*10);
	buffer.append(n);
	}
	String code =  buffer.toString();
	session.setAttribute("code", code);
	
	String result = request.getParameter("result");
	System.out.println(result);
	String sender = "wnsgh1724@gmail.com";
	String receiver = request.getParameter("email");//hwashin_12@naver.com
	System.out.println(receiver);
	String subject = "회원가입 인증번호입니다.";
	String content = "인증번호는["+buffer.toString()+"] 입니다. \n 인증번호를 사이트에 입력하시오";
	//정보담기 위해 객체 설정
	Properties p = new Properties();
	// SMTP 서버의 계정 설정
	// Naver와 연결할 경우 네이버 아이디 지정
	// Google과 연결할 경우 본인의 Gmail 주소
	p.put("mail.smtp.user", sender);

	// SMTP 서버 정보 설정
	// 네이버일 경우 smtp.naver.com
	// Google일 경우 smtp.gmail.com
	
	p.put("mail.smtp.host", "smtp.gmail.com");
	// 아래 정보는 네이버와 구글이 동일
	p.put("mail.smtp.port", "465"); 
	p.put("mail.smtp.starttls.enable","true"); 
	// 반드시 true 
	p.put("mail.smtp.auth", "true"); 
	p.put("mail.smtp.debug", "true"); 
	p.put("mail.smtp.socketFactory.port", "465"); 
	p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
	p.put("mail.smtp.socketFactory.fallback", "false");

	
	try{
		Authenticator auth = new SMTPAuthenticator();
		Session ses = Session.getInstance(p,auth);
		
		ses.setDebug(true);
		
		MimeMessage msg = new MimeMessage(ses);
	    
	        
	    // 보내는 사람의 메일주소
	    Address fromAddr = new InternetAddress(sender);
	    msg.setFrom(fromAddr);
	        
	    // 받는 사람의 메일주소
	    Address toAddr = new InternetAddress(receiver);
	    msg.addRecipient(Message.RecipientType.TO, toAddr);
		// 제목 설정
	    msg.setSubject(subject);
	    // 메시지 본문의 내용과 형식, 캐릭터 셋 설정
	    msg.setContent(content, "text/html;charset=UTF-8");
	        
	    // 발송하기
	    Transport.send(msg);
	        
	} catch (Exception mex) {
	    mex.printStackTrace();
	    String script = "<script type='text/javascript'>\n";
	    script += "alert('메일발송에 실패했습니다.');\n";
	    script += "</script>";
	    out.print(script);
	    return;
		
	}
	String script = "<script type='text/javascript'>\n";
	script += "alert('메일발송에 성공했습니다.');\n";
	script += "</script>";
	out.print(script);
	System.out.println(buffer.toString());

%>
<script type="text/javascript">

	function numcheck(){
				
		var no1 =document.getElementById("code").value;

		var no2= document.getElementById("authno").value;

		if(no1==no2){
			alert("인증 성공");
			opener.document.getElementById("result").value=1;
			opener.document.getElementById("email").setAttribute("readonly", "readonly");
			var receiver = document.getElementById("email");
			window.close();
	
		}else{
			
			alert("인증번호가 다릅니다.");
			
		} 
	}
</script>
</head>
<body>
<center>

	인증번호 : <input type="text" id="authno"><br/>
	<input type="hidden" name="result">
	<input type="hidden" id="code" value="<%=session.getAttribute("code")%>">
	<input type="button" value="입력하기" onclick="numcheck()">
	<input type="hidden" value="<%=receiver%>" id="email">
</center>
</body>
</html>