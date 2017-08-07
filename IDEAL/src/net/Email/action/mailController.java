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
		String RequestURI = req.getRequestURI();
		System.out.println(RequestURI);
		System.out.println(req.getRequestURI().substring(RequestURI.lastIndexOf('/')+1));
		String command = req.getRequestURI().substring(req.getRequestURI().lastIndexOf('/')+1);
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("MailtoAdmin.em")){
			
			action = new mailAction2();
			
			try{
				forward = action.execute(req, resp);
			}catch(Exception e){
				e.printStackTrace();
				}

		}else if (command.equals("sent.em")){//메일보내기 버튼을 누를때
			
			
			forward = new ActionForward();
			forward.setisRedirect(false);
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
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
}