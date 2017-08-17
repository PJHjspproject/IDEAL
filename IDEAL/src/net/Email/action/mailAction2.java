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
		//�ѱ�ó��
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//���ǰ� �޾ƿ���(�� �̸���,�г���)
		HttpSession session = request.getSession();
		String senderMail = (String)session.getAttribute("memberEmail");
		String senderNic = (String)session.getAttribute("nickName");
		if(senderMail==null){
			senderNic = "��ȸ��";
			senderMail = request.getParameter("email");
		}
		
		//mailtoadmin.jsp���� �ۼ��� �� �޾ƿ���
		String adminMail = "idealDRT@gmail.com";//������ ���� ����
		String adminPass ="11335577";

		String subject = request.getParameter("subject");
		String content = request.getParameter("content");		
		
	
		//����ϴ� �̸��� �߶󳻱� 
		//ex abc123@gmail.com ==>gmail.com
		String site = "";
		StringTokenizer st = new StringTokenizer(adminMail, "@");
		while(st.hasMoreTokens()){
			site = st.nextToken();
		}
		//����ϴ� �̸��� ���� �����ϱ� ���� ���� ����
		Properties p = new Properties();
		p.put("mail.smtp.host", "smtp."+site);//�ش� ����Ʈ ����
		p.put("mail.smtp.port", "465");		//��Ʈ ��ȣ
		p.put("mail.smtp.starttls.enable", "true");//���Ῡ��1
		p.put("mail.smtp.auth", "true");		//���Ῡ��2
		p.put("mail.smtp.debug", "true");	//����� Ȯ��
		p.put("mail.smtp.socketFactory.port", "465");//��Ʈ��ȣ Ȯ��
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");//�α��� ���� ��� Ȯ��
		
		try{
			Authenticator auth = new SMTPAuthenticator(adminMail, adminPass);//����ϴ� ���� ����,���
			Session ses = Session.getInstance(p, auth);//���� �õ��Ͽ� ���ǰ� ����
			
			ses.setDebug(true);//���ǰ� �ش� ����Ʈ�� �� Ȯ��
			//������ ������ ���� ��ü
			MimeMessage msg = new MimeMessage(ses);
			
			
			/* ������ ��� ���� */
			Address fromAddr = new InternetAddress(adminMail, MimeUtility.encodeText(senderNic, "utf-8", "B"));
			//������ ���
			msg.setFrom(fromAddr);
			
			/* �޴� ��� ���� */
			//Ȥ�ó� ���� �迭�� ����
			InternetAddress[] TOemails = {new InternetAddress(adminMail)};
//	 		Address toAddr = new InternetAddress(adminMail);
			//�޴� ���
			msg.addRecipients(Message.RecipientType.TO, TOemails);

			
			//����
			msg.setSubject(subject+", ȸ�Ÿ��� : "+senderMail);
			//���� ��¥
			msg.setSentDate(new Date());
			//����� ���ڵ�
			msg.setContent(content, "text/html;charset=utf-8");
			//���� ����
			Transport.send(msg);
			
			request.setAttribute("check","1");
		}catch(Exception e){
			request.setAttribute("check","0");
			e.printStackTrace();

		}
		//���� �߼� ���� ���� ���� �Ǻ��� check(Ű)����
		//request.setAttribute�� ��� ���� ����
		
		//�߼� �غ� ActionForward ��ü ����
		ActionForward forward = new ActionForward();
 		forward.setisRedirect(false);//�̵��� �ּ� ���� ����
 		//sent.em���� mailConteroller�� ��ģ���� mailtoadmin.jsp
 		//�� ����� �����ַ� �̵�
 		forward.setPath("./sent.em");
 		return forward;//������ �̵���� ���ΰ�false�� 
 		//�̵��� ������ �ּҸ� ����ִ� new ActionForward()��ü�� 
 		//mailConteroller�� ����
	}

	
}
