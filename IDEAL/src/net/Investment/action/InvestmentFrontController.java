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
		//��ü �ּ� ��������
		String requestURI = req.getRequestURI();
		System.out.println(requestURI);
		//��ü �ּ� �� �� �κ� ��������
		String contextPath = req.getContextPath();
		System.out.println(contextPath.length());
		//���� ��û �ּ� ��������
		String command = requestURI.substring(contextPath.length());
		System.out.println(command);
		
		Action action = null;
		ActionForward forward = null;
		
		//investment.jsp���� �����ϱ⸦ ���� ���� ���� �� index.jsp�� �̵��϶�� ��û�� ������ ��...
		if(command.equals("/index.im")){
			
			//������ �̵���� ���ΰ�, �̵������� ��ΰ� �����Ͽ� �������ִ� ��ü ����
			forward = new ActionForward();
			//������ �̵���� ���ΰ� false�� ����->RequestDispatcher forward() ���
			forward.setRedirect(false);
			//�̵��� ������ ���(�����ϱ� ������) �ּҰ� ����
			forward.setPath("main1.jsp");
			
			//investment.jsp���� �Է��� ī������ ������ ����ִ�
			//request������ execute�޼ҵ��� �Ű������� �����Ͽ�
			//ī������ DB�۾� �� ī������� �����ϸ�
			//������ �̵� ��� ���ΰ� true��
			//�̵��� ������ �ּҸ� ��� �ִ�
			//new ActionForward()��ü�� ���Ϲ޴´�
			
			//investment.jsp���� �����ϱ⸦ ���� investmentAction.java�� �̵� �Ͽ� �Է� ���� Ȯ��
		}else if(command.equals("/investmentAction.im")){
			action = new investmentAction();
			try {
				
				forward = action.excute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//ī�� ���� ����ġ �� �����ϱ� �������� �� �̵�
		}else if(command.equals("/investment.im")){
			
			action = new goInvestmentAction();
			
			
			try{
				forward= action.excute(req, resp);
				
			}catch(Exception e){
				System.out.println("ī������ Ȯ�� ����");
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
