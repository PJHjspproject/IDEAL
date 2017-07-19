package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(asyncSupported = true, name = "MemberFrontController2", urlPatterns = { "/MemberFrontController2.mf" })
public class MemberFrontController1 extends HttpServlet {

	protected void doProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String RequestURI = req.getRequestURI();
		System.out.println(RequestURI);
		String contextPath = req.getContextPath();
		System.out.println(contextPath);
		String command = RequestURI.substring(contextPath.length());// 11
		System.out.println(command);
		
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/yak.mf")){//회원가입 클릭시
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./index.jsp?center=member/yak.jsp");
			
		}else if(command.equals("/mailauth.mf")){//약관동의를 정상적으로 완료했을시
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./index.jsp?center=member/mailauth.jsp");
			
		}else if(command.equals("/index.mf")){//메인으로 돌아갈때
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./index.jsp");
			
		}else if(command.equals("/Joinmember.mf")){//메일 인증을 완료하기 가입하기 버튼을 눌렀을때

			action = new MemberMoveAction();
			
			try{
				forward = action.execute(req, resp);
			}catch(Exception e){
				System.out.println("인증상황에서의 문제 :"+e);
			}
			
			
		}else if(command.equals("/MemberJoin.mf")){//회원 정보를 모두 입력하고 가입 버튼을 눌렀을때
			action = new MemberJoinAction();
			try{

				forward =action.execute(req, resp);
				
				
			}catch(Exception e){
				System.out.println("회원가입 실패 :"+e);
				
			}
		}else if(command.equals("/MemberLoginAction.mf")){//로그인 버튼 클릭시
			action = new MemberLoginAction();
			
			try{
				
				forward = action.execute(req, resp);
			}catch(Exception e){
				System.out.println("회원가입 실패 :"+e);
				
			}
			
		}else if(command.equals("/MemberLogoutAction.mf")){//로그 아웃 버튼을 눌렀을때
			action = new MemberLogoutAction();
			try{
				
				forward = action.execute(req, resp);
			}catch(Exception e){
				System.out.println("회원가입 실패 :"+e);
				
			}
			
		}else if(command.equals("/MemberPassCheckAction.mf")){//수정삭제를 위한 페이지를 index페이지에서 클릭시
			action = new MemberPassCheckAction();
			try{
				
				forward = action.execute(req, resp);
			}catch(Exception e){
				System.out.println("마이페이지 열기 실패:"+e);
				
			}
			
		}else if(command.equals("/Memberinfo.mf")){//비밀번호를 올바르게 입력하고 넘어갈시
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./index.jsp?center=member/Memberinfo.jsp");
			
		}else if(command.equals("/Usercheckview.mf")){//비밀번호 입력 창으로 이동
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./index.jsp?center=member/Usercheck.jsp");
		}else if(command.equals("/MemberUpdate.mf")){
			
			action = new MemberUpdateAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("회원 수정중 실패:"+e);
				e.printStackTrace();
			}
		}else if(command.equals("/MemberView.mf")){
			
			action = new MemberInfoViewAction();
			try{
				forward = action.execute(req, resp);
			}catch(Exception e){
				System.out.println("회원 수정중 실패:"+e);
				e.printStackTrace();
			}
		}else if(command.equals("/UpdateViewpage.mf")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./index.jsp?center=member/UserUpdate.jsp");
	
		}else if(command.equals("/MemberUpdateProc.mf")){
			action = new MemberUpdateAction();
			
			try{
				forward = action.execute(req, resp);
				
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("회원 수정Action전 오류:"+e);
			}
		}
		// 실제주소이동
			if (forward != null) {
				if (forward.isRedirect()) {// true sendRedirect
					resp.sendRedirect(forward.getPath());
				} else {// false forward()
					RequestDispatcher view = req.getRequestDispatcher(forward.getPath());
					view.forward(req, resp);
				}
			}
		}

	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProc(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProc(request, response);
	}
}
