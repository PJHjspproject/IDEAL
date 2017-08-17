package net.InformationUse.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.InformationUse.action.ActionForward;

public class InformationUseFrontController extends HttpServlet{

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
		/*�ּ� ��*/
		//�ڽ� Action��ü���� ���� �������̽� Ÿ���� �������� ����
		Action action = null;
		
		//������ �̵� ��� ���� ��, �̵������� ��� �� �����Ͽ� ���� ���ִ� ��ü�� ������ ���� ���� ����
		ActionForward forward = null;
		
		if(command.equals("/Information.iU")){
			action = new InformationUseAction();
			try {
				forward = action.excute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if (command.equals("/informationUse_content.iU")) {
			
			action = new informationUse_ContentAction();
			
			try {
				forward = action.excute(req, resp);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}else if(command.equals("/informationUse.iU")){
			forward = new ActionForward();
			forward.setPath("Information.iU");
			forward.setRedirect(false);
		}
		
		//(���� �ּҷ� �̵�)
		if(forward != null){//new ActionForward();��ü�� ���� �ϰ�..
			if(forward.isRedirect()){//true -> sendRedirect()���
				resp.sendRedirect(forward.getPath());
			}else{//false -> forward()���
				RequestDispatcher view =  req.getRequestDispatcher(forward.getPath());
				view.forward(req, resp);
			}
		}
		
		
		
	}
	
}
