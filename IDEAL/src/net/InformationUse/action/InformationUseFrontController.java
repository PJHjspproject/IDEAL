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
		//전체 주소 가져오기
		String requestURI = req.getRequestURI();
		//전체 주소 중 앞 부분 가져오기
		String contextPath = req.getContextPath();
		//실제 요청 주소 가져오기
		String command = requestURI.substring(contextPath.length());
		System.out.println(command);
		/*주소 비교*/
		//자식 Action객체들을 담을 인터페이스 타입의 참조변수 선언
		Action action = null;
		
		//페이지 이동 방식 여부 값, 이동페이지 경로 값 저장하여 리턴 해주는 객체를 저장할 참조 변수 선언
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
		
		//(실제 주소로 이동)
		if(forward != null){//new ActionForward();객체가 존재 하고..
			if(forward.isRedirect()){//true -> sendRedirect()방식
				resp.sendRedirect(forward.getPath());
			}else{//false -> forward()방식
				RequestDispatcher view =  req.getRequestDispatcher(forward.getPath());
				view.forward(req, resp);
			}
		}
		
		
		
	}
	
}
