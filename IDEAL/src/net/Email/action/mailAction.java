package net.Email.action;

import java.io.PrintWriter;
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
public class mailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//�ѱ�ó��
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//���ǰ� �޾ƿ���(�� �̸���)
//		HttpSession session = request.getSession();
//		session.getAttribute("memberEmail");
		
		//mailtoadmin.jsp���� �ۼ��� �� �޾ƿ���
		String adminmail = "vavilyan@naver.com";//������ ���� ����
		String senderNic = request.getParameter("nickname");
		String sendermail = request.getParameter("email");
		String senderpass = request.getParameter("pass");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");		
		
		//�� ��� ���� ��� �Է��� ���Ұ��
		if(senderpass.isEmpty()){
			PrintWriter out = response.getWriter();
			out.println("<script>");
	 		out.println("alert('���θ��� ���� ��� �Է��ϼ���');");
	 		out.println("history.back();");
	 		out.println("</script>");
		}
		//����ϴ� �̸��� �߶󳻱� 
		//ex abc123@gmail.com ==>gmail.com
		String site = "";
		StringTokenizer st = new StringTokenizer(sendermail, "@");
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
			Authenticator auth = new SMTPAuthenticator(sendermail, senderpass);//����ϴ� ���� ����,���
			Session ses = Session.getInstance(p, auth);//���� �õ��Ͽ� ���ǰ� ����
			
			ses.setDebug(true);//���ǰ� �ش� ����Ʈ�� �� Ȯ��
			//������ ������ ���� ��ü
			MimeMessage msg = new MimeMessage(ses);
			
			
			/* ������ ��� ���� */
			Address fromAddr = new InternetAddress(sendermail, MimeUtility.encodeText(senderNic, "utf-8", "B"));
			//������ ���
			msg.setFrom(fromAddr);
			
			/* �޴� ��� ���� */
			//Ȥ�ó� ���� �迭�� ����
			InternetAddress[] TOemails = {new InternetAddress(adminmail)};
//	 		Address toAddr = new InternetAddress(adminmail);
			//�޴� ���
			msg.addRecipients(Message.RecipientType.TO, TOemails);

			
			//����
			msg.setSubject(subject);
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
