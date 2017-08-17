package net.Email.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.Email.action.ActionForward;




public class mailController extends HttpServlet {

	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//세션 값
		//HttpSession session = req.getSession();
		
		//가상 요청 주소 가져오기
	// System.out.println(RequestURI);
		String RequestURI = req.getRequestURI();

	// /MailtoAdmin.em 얻기
		//RequestURI 에서 마지막 주소값을 / 로 잘라서 나타냄
		System.out.println(RequestURI);
		System.out.println(req.getRequestURI().substring(RequestURI.lastIndexOf('/')+1));
		String command = req.getRequestURI().substring(req.getRequestURI().lastIndexOf('/')+1);
	/*주소 비교*/
	//페이지 이동방식 여부값, 이동페이지 경로값 저장하여 리턴 해주는 객체를 저장할 
	//참조변수 선언
		ActionForward forward = null;
	
	//자식 Action객체들을 담을 인터 페이스 타입의 참조변수 선언
		Action action = null;
		
		//mailtoadmin.jsp 에서 넘어옴
			//mailActiond.java로 넘어감
		if(command.equals("MailtoAdmin.em")){
			//Action 인터페이스에 mailAction() 업캐스팅
			action = new mailAction2();
			
			try{
				forward = action.execute(req, resp);
			}catch(Exception e){e.printStackTrace();}
			//페이지 이동방식 여부값 false
//			forward.setisRedirect(false);
			//이동할 페이지 경로 (회원가입 페이지) 주소값 저장
//			forward.setPath("");	
		//mailAction.java에서 넘어옴
			
			//결과값 뿌려주러 mailtoadmin.jsp로 다시 이동
		}else if (command.equals("sent.em")){
			//이동방식 여부값, 이동 페이지경로 값 저장
			
			forward = new ActionForward();
			forward.setisRedirect(false);//주소값 노출 안함
			try{
				forward.setPath("mailtoadmin.jsp");
				System.out.println(forward.getPath());
			}catch(Exception e){e.printStackTrace();}
			
			
			
			//*** 세션값은 계속 따라옴 ***
			
		}
		//(실제 주소로 이동)
		if(forward != null){
			//new ActionForward()객체가 존재하고.....
			if(forward.getisRedirect()){
				//true
				resp.sendRedirect(forward.getPath());
			}else{
				//false
				System.out.println(command);
				RequestDispatcher view =
				req.getRequestDispatcher(forward.getPath());
				view.forward(req, resp);
			}
		}
		
	}
	@Override//Get,Post방식 둘다 doProcess로 Request, Response 넘김
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
}