package net.Email.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.Email.action.ActionForward;




public class mailController extends HttpServlet {

	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//���� ��
		//HttpSession session = req.getSession();
		
		//���� ��û �ּ� ��������
	// System.out.println(RequestURI);
		String RequestURI = req.getRequestURI();

	// /MailtoAdmin.em ���
		//RequestURI ���� ������ �ּҰ��� / �� �߶� ��Ÿ��
		System.out.println(RequestURI);
		System.out.println(req.getRequestURI().substring(RequestURI.lastIndexOf('/')+1));
		String command = req.getRequestURI().substring(req.getRequestURI().lastIndexOf('/')+1);
	/*�ּ� ��*/
	//������ �̵���� ���ΰ�, �̵������� ��ΰ� �����Ͽ� ���� ���ִ� ��ü�� ������ 
	//�������� ����
		ActionForward forward = null;
	
	//�ڽ� Action��ü���� ���� ���� ���̽� Ÿ���� �������� ����
		Action action = null;
		
		//mailtoadmin.jsp ���� �Ѿ��
			//mailActiond.java�� �Ѿ
		if(command.equals("MailtoAdmin.em")){
			//Action �������̽��� mailAction() ��ĳ����
			action = new mailAction2();
			
			try{
				forward = action.execute(req, resp);
			}catch(Exception e){e.printStackTrace();}
			//������ �̵���� ���ΰ� false
//			forward.setisRedirect(false);
			//�̵��� ������ ��� (ȸ������ ������) �ּҰ� ����
//			forward.setPath("");	
		//mailAction.java���� �Ѿ��
			
			//����� �ѷ��ַ� mailtoadmin.jsp�� �ٽ� �̵�
		}else if (command.equals("sent.em")){
			//�̵���� ���ΰ�, �̵� ��������� �� ����
			
			forward = new ActionForward();
			forward.setisRedirect(false);//�ּҰ� ���� ����
			try{
				forward.setPath("mailtoadmin.jsp");
				System.out.println(forward.getPath());
			}catch(Exception e){e.printStackTrace();}
			
			
			
			//*** ���ǰ��� ��� ����� ***
			
		}
		//(���� �ּҷ� �̵�)
		if(forward != null){
			//new ActionForward()��ü�� �����ϰ�.....
			if(forward.getisRedirect()){
				//true
				resp.sendRedirect(forward.getPath());
			}else{
				//false
				System.out.println(command);
				RequestDispatcher view =
				req.getRequestDispatcher(forward.getPath());
				view.forward(req, resp);
			}
		}
		
	}
	@Override//Get,Post��� �Ѵ� doProcess�� Request, Response �ѱ�
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
}