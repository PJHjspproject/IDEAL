package net.investRequest.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.investRequest.db.InvestRequestDto;

public class InvestRequestFrontController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//�ѱ�ó��
		req.setCharacterEncoding("utf-8");
		
		//��ü �ּ� ��������
		String requestURI = req.getRequestURI();
		System.out.println(requestURI);
		//��ü �ּ� �� �� �κ� ��������
		String contextPath = req.getContextPath();
		System.out.println(contextPath);
		//���� ��û �ּҰ� ��������
		String command = requestURI.substring(contextPath.length());
		System.out.println(command);
		
		InvestRequestDto irdto = null;
		
		Action action = null;
		
		ActionForward forward = null;
		
		if(command.equals("/investRequest.iR")){
			System.out.println("���ڿ�û����");
			forward = new ActionForward();
			forward.setPath("main1.jsp?center=investRequest/investRequest1.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/investRequestAction1.iR")){
			action = new InvestRequestAction1();
			try {
				forward = action.excute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/investRequestAction2.iR")){
			action = new InvestRequestAction2();
			try {
				forward = action.excute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/investRequestAction3.iR")){
			action = new InvestRequestAction3();
			try {
				forward = action.excute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/viewInvestRequestAction.iR")){
			action = new goViewInvestRequestAction();
			try {
				forward = action.excute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/goInvestmentList.iR")){
			action = new getInvestRequestListAction();
			try {
				forward = action.excute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/SearchList.iR")){
			forward = new ActionForward();
			forward.setPath("main1.jsp?center=investRequest/product.jsp");
			forward.setRedirect(false);
		}else{
			System.out.println("���� �߸���");
		}
		
		if(forward != null){
			if(forward.isRedirect()){
				System.out.println("-----sendRedirect����-----");
				resp.sendRedirect(forward.getPath());
				System.out.println(forward.getPath());
				System.out.println("-------------------------");
			}else{
				System.out.println("-----requestDispatcher����-----");
				RequestDispatcher view = req.getRequestDispatcher(forward.getPath());
				System.out.println(forward.getPath());
				view.forward(req, resp);
				System.out.println("------------------------------");
			}
		}
	}
}