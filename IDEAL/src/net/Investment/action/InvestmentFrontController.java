package net.Investment.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import net.Card.db.CardDao;
import net.Card.db.CardDto;

import net.Investment.db.InvestmentDto;
import net.Investment.db.InvestmentDao;



public class InvestmentFrontController extends HttpServlet{

	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String requestURI = req.getRequestURI();
		System.out.println(requestURI);
		String contextPath = req.getContextPath();
		System.out.println(contextPath.length());
		String command = requestURI.substring(contextPath.length());
		System.out.println(command);
		
		Action action = null;
		ActionForward forward = null;
		
		//investment.jsp에서 투자하기를 눌러 투자 성공 시 index.jsp로 이동하라는 요청이 들어왔을 때...
		if(command.equals("/index.im")){
			
			
			forward = new ActionForward();
			
			forward.setRedirect(false);
			
			forward.setPath("main1.jsp");
		
			//investment.jsp에서 투자하기를 눌러 investmentAction.java로 이동 하여 입력 정보 확인
		}else if(command.equals("/investmentAction.im")){
			action = new investmentAction();
			try {
				
				forward = action.excute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//카드 정보 불일치 시 투자하기 페이지로 재 이동
		}else if(command.equals("/investment.im")){
			
			action = new goInvestmentAction();
			
			
			try{
				forward= action.excute(req, resp);
				
			}catch(Exception e){
				System.out.println("/investment.im 에러어"+e);
			}

		}
		
		
		if(forward != null){
			if(forward.isRedirect()){
				resp.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher view =  req.getRequestDispatcher(forward.getPath());
				view.forward(req, resp);
			}
		}
		
		
		
	}
	
}
