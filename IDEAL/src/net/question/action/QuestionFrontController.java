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
		
		String requestURI = req.getRequestURI();
		
		String contextPath = req.getContextPath();
		
		String command = requestURI.substring(contextPath.length());
		System.out.println(command);
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/question.qU")){//1:1 문의사항 글 목록 
			System.out.println("question.qu탓음");
			action = new questionAction();
			
			try {
				forward = action.excute(req, resp);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}else if(command.equals("/questionContent.qU")){//1:1 문의 글 읽기
			
			action = new questionContentAction();
			
			try {
				forward = action.excute(req, resp);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}else if(command.equals("/writequestion.qU")){//목록에서 글쓰기
			
			forward = new ActionForward();
			
			forward.setRedirect(false);
			forward.setPath("./main1.jsp?center=question/writequestion.jsp");
			
		}else if(command.equals("/insertquestion.qU")){//글 쓰기 버튼 클릭 insert 실행
			
			action = new insertquestAction();
			
			try {
				forward = action.excute(req, resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(command.equals("/deletequestion.qU")){//1:1 글 삭제
			
			action = new deletequestAction();
			
			try {
				forward = action.excute(req, resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}else if(command.equals("/updatequestion.qU")){//1:1글 수정
				action = new updateQuestionAction();
				try {
					forward = action.excute(req, resp);
				} catch (Exception e) {
					System.out.println("updatequestion.qU:"+e);
				}
			}else if(command.equals("/updatequestionPro.qU")){//update 작업 실행
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
