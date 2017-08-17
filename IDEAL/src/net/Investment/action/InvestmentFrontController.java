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
		//전체 주소 가져오기
		String requestURI = req.getRequestURI();
		System.out.println(requestURI);
		//전체 주소 중 앞 부분 가져오기
		String contextPath = req.getContextPath();
		System.out.println(contextPath.length());
		//실제 요청 주소 가져오기
		String command = requestURI.substring(contextPath.length());
		System.out.println(command);
		
		Action action = null;
		ActionForward forward = null;
		
		//investment.jsp에서 투자하기를 눌러 투자 성공 시 index.jsp로 이동하라는 요청이 들어왔을 때...
		if(command.equals("/index.im")){
			
			//페이지 이동방식 여부값, 이동페이지 경로값 저장하여 리턴해주는 객체 생성
			forward = new ActionForward();
			//페이지 이동방식 여부값 false로 저장->RequestDispatcher forward() 방식
			forward.setRedirect(false);
			//이동할 페이지 경로(투자하기 페이지) 주소값 저장
			forward.setPath("main1.jsp");
			
			//investment.jsp에서 입력한 카드정보 내용을 담고있는
			//request영역을 execute메소드의 매개변수로 전달하여
			//카드정보 DB작업 후 카드결제에 성공하면
			//페이지 이동 방식 여부값 true와
			//이동할 페이지 주소를 담고 있는
			//new ActionForward()객체를 리턴받는다
			
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
				System.out.println("카드정보 확인 실패");
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
