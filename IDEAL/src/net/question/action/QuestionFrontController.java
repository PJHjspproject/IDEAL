package net.question.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionFrontController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ü �ּ� ��������
		String requestURI = req.getRequestURI();
		//��ü �ּ� �� �� �κ� ��������
		String contextPath = req.getContextPath();
		//���� ��û �ּ� ��������
		String command = requestURI.substring(contextPath.length());
		System.out.println(command);
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/question.qU")){
			System.out.println("question.qu����");
			action = new questionAction();
			
			try {
				forward = action.excute(req, resp);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}else if(command.equals("/questionContent.qU")){
			
			action = new questionContentAction();
			
			try {
				forward = action.excute(req, resp);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}else if(command.equals("/writequestion.qU")){
			
			forward = new ActionForward();
			
			forward.setRedirect(false);
			forward.setPath("./main1.jsp?center=question/writequestion.jsp");
			
		}else if(command.equals("/insertquestion.qU")){
			
			action = new insertquestAction();
			
			try {
				forward = action.excute(req, resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(command.equals("/deletequestion.qU")){
			
			action = new deletequestAction();
			
			try {
				forward = action.excute(req, resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}else if(command.equals("/updatequestion.qU")){
				action = new updateQuestionAction();
				try {
					forward = action.excute(req, resp);
				} catch (Exception e) {
					System.out.println("updatequestion.qU:"+e);
				}
			}else if(command.equals("/updatequestionPro.qU")){
				action = new updateQuestionProAction();
				try {
					forward = action.excute(req, resp);
				} catch (Exception e) {
					System.out.println("updatequestionPro.qU:"+e);
				}
			}
		
		
		if(forward != null){
			if(forward.isRedirect()){
				System.out.println("--------샌드리다이렉트--------");
				System.out.println("command : "+command);
				System.out.println("-----------------------------");
				resp.sendRedirect(forward.getPath());
			}else{
				System.out.println("---------RequestDispatcher-------");
				System.out.println("command : "+command);
				System.out.println("------------------------------------");
				RequestDispatcher view =  req.getRequestDispatcher(forward.getPath());
				view.forward(req, resp);
			}
		}
		
		
		
	}
	
}
