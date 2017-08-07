package net.Email.action;


import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.*;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.Email.action.ActionForward;

import javax.mail.internet.MimeUtility;
public class mailAction2 implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//한글처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//세션값 받아오기(고객 이메일,닉네임)
		HttpSession session = request.getSession();
		String senderMail = (String)session.getAttribute("memberEmail");
		String senderNic = (String)session.getAttribute("nickName");
		if(senderMail==null){
			senderNic = "비회원";
			senderMail = request.getParameter("email");
		}
		
		//mailtoadmin.jsp에서 작성된 값 받아오기
		String adminMail = "idealDRT@gmail.com";//관리자 메일 저장
		String adminPass ="11335577";

		String subject = request.getParameter("subject");
		String content = request.getParameter("content");		
		
	
		//사용하는 이메일 잘라내기 
		//ex abc123@gmail.com ==>gmail.com
		String site = "";
		StringTokenizer st = new StringTokenizer(adminMail, "@");
		while(st.hasMoreTokens()){
			site = st.nextToken();
		}
		//사용하는 이메일 계정 접속하기 위한 정보 저장
		Properties p = new Properties();
		p.put("mail.smtp.host", "smtp."+site);//해당 사이트 삽입
		p.put("mail.smtp.port", "465");		//포트 번호
		p.put("mail.smtp.starttls.enable", "true");//연결여부1
		p.put("mail.smtp.auth", "true");		//연결여부2
		p.put("mail.smtp.debug", "true");	//디버깅 확인
		p.put("mail.smtp.socketFactory.port", "465");//포트번호 확인
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");//로그인 여부 결과 확인
		
		try{
			Authenticator auth = new SMTPAuthenticator(adminMail, adminPass);//사용하는 메일 계정,비번
			Session ses = Session.getInstance(p, auth);//접속 시도하여 세션값 받음
			
			ses.setDebug(true);//세션값 해당 사이트와 비교 확인
			//메일의 내용을 담을 객체
			MimeMessage msg = new MimeMessage(ses);
			
			
			/* 보내는 사람 설정 */
			Address fromAddr = new InternetAddress(adminMail, MimeUtility.encodeText(senderNic, "utf-8", "B"));
			//보내는 사람
			msg.setFrom(fromAddr);
			
			/* 받는 사람 설정 */
			//혹시나 가변 배열로 받음
			InternetAddress[] TOemails = {new InternetAddress(adminMail)};
//	 		Address toAddr = new InternetAddress(adminMail);
			//받는 사람
			msg.addRecipients(Message.RecipientType.TO, TOemails);

			
			//제목
			msg.setSubject(subject+", 회신메일 : "+senderMail);
			//보낸 날짜
			msg.setSentDate(new Date());
			//내용과 인코딩
			msg.setContent(content, "text/html;charset=utf-8");
			//메일 전송
			Transport.send(msg);
			
			request.setAttribute("check","1");
		}catch(Exception e){
			request.setAttribute("check","0");
			e.printStackTrace();

		}
		//메일 발송 성공 실패 여부 판별할 check(키)값을
		//request.setAttribute에 담아 같이 보냄
		
		//발송 준비 ActionForward 객체 생성
		ActionForward forward = new ActionForward();
 		forward.setisRedirect(false);//이동할 주소 노출 안함
 		//sent.em으로 mailConteroller를 거친다음 mailtoadmin.jsp
 		//로 결과값 보내주러 이동
 		forward.setPath("./sent.em");
 		return forward;//페이지 이동방식 여부값false와 
 		//이동할 페이지 주소를 담고있는 new ActionForward()객체를 
 		//mailConteroller로 리턴
	}

	
}
