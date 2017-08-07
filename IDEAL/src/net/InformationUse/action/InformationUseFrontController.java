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
		
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length());
		System.out.println(command);
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/Information.iU")){//FAQ  목록 뿌려주는 컨트롤러
			action = new InformationUseAction();
			try {
				forward = action.excute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if (command.equals("/informationUse_content.iU")) {//FAQ 목록중 글하나 의 상세정보를 뿌려주는 컨트롤러
			
			action = new informationUse_ContentAction();
			
			try {
				forward = action.excute(req, resp);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}else if(command.equals("/informationUse.iU")){ // 상세보기에서 목록 보기를 누를때 
			forward = new ActionForward();
			forward.setPath("Information.iU");
			forward.setRedirect(false);
		}
		
		
		if(forward != null){//new ActionForward();
			if(forward.isRedirect()){//true -> sendRedirect()
				resp.sendRedirect(forward.getPath());
			}else{//false -> forward()
				RequestDispatcher view =  req.getRequestDispatcher(forward.getPath());
				view.forward(req, resp);
			}
		}
		
		
		
	}
	
}
