package net.Main.Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IdealMainController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Action action = null;
		ActionForward forward = null;
		
		String requestURI = req.getRequestURI();
		System.out.println(requestURI);
		String contextPath = req.getContextPath();
		System.out.println(contextPath);
		String command = requestURI.substring(contextPath.length());
		System.out.println(command);
		
		if(command.equals("/goMain.ma")){
			System.out.println("/goMain.ma ÁøÀÔ");
			action = new getMainListAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/Main.ma")){
			forward = new ActionForward();
			forward.setPath("main1.jsp?center=center1.jsp");
			forward.setRedirect(false);
		}else{
			System.out.println("Àß¸øµÊ");
		}
		
		if(forward!=null){
			if(forward.isRedirect()){
				resp.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher view = req.getRequestDispatcher(forward.getPath());
				view.forward(req, resp);
			}
		}
	}
	
}
