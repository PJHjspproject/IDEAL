package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*member 관련 컨트롤러 *.mf는 무조건 이 컨트롤러를 거친다. 각 커멘드에 대한 설명은 회의록 참고 할것*/
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
		
		if(command.equals("/yak.mf")){ //메인페이지에서 회원기입으로 넘어갈때
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main1.jsp?center=member/yak.jsp");
			
		}else if(command.equals("/mailauth.mf")){//약관을 동의한후 메일인증페이지로 넘어갈때
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main1.jsp?center=member/mailauth.jsp");
			
		}else if(command.equals("/index.mf")){//메인페이지로 돌아가는 컨트롤러
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main1.jsp");
			System.out.println(forward.getPath());
		}else if(command.equals("/Joinmember.mf")){//이메일 인증을 완료하고 

			action = new MemberMoveAction();
			
			try{
				forward = action.execute(req, resp);
			}catch(Exception e){
				System.out.println("Joinmember 에러:"+e);
			}
			
			
		}else if(command.equals("/MemberJoin.mf")){//회원정보를 모두 입력하고 insert 하는 컨트롤러
			action = new MemberJoinAction();
			try{
				forward =action.execute(req, resp);
			}catch(Exception e){
				System.out.println("Joinmember 에러 :"+e);
			}
		}else if(command.equals("/MemberLoginAction.mf")){//로그인 버튼을  눌렀을때 컨트롤러
			action = new MemberLoginAction();
			
			try{
				
				forward = action.execute(req, resp);
			}catch(Exception e){
				System.out.println("Joinmember 에러:"+e);
				
			}
			
		}else if(command.equals("/MemberLogoutAction.mf")){//로그아웃 버튼을 눌렀을때 컨트롤러
			action = new MemberLogoutAction();
			try{
				
				forward = action.execute(req, resp);
			}catch(Exception e){
				System.out.println("MemberLogoutAction.mf 에러 :"+e);
				
			}
			
		}else if(command.equals("/MemberPassCheckAction.mf")){//비밀번호를 눌러 비밀번호를 비교하는
			action = new MemberPassCheckAction();
			try{
				
				forward = action.execute(req, resp);
			}catch(Exception e){
				System.out.println("MemberPassCheckAction:"+e);
				
			}
			
		}else if(command.equals("/Memberinfo.mf")){//비밀번호를 비교한후 제대로 비교했으면 마이페이지로 이동하는 컨트롤러
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main1.jsp?center=member/Memberinfo.jsp");
			
		}else if(command.equals("/Usercheckview.mf")){//메인페이지에서 마이페이지를 눌렀을때 동작하는 컨트롤러
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main1.jsp?center=member/Usercheck.jsp");
		}else if(command.equals("/MemberView.mf")){//비밀번호인증을 성공한후 마이페이지 화면으로 이동하는 컨트롤러
			
			action = new MemberInfoViewAction();
			try{
				forward = action.execute(req, resp);
			}catch(Exception e){
				System.out.println("MemberView:"+e);
				e.printStackTrace();
			}
		}else if(command.equals("/UpdateViewpage.mf")){//마이페이지에서 회원수정을 눌렀을시 컨트롤러
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main1.jsp?center=member/UserUpdate.jsp");
	
		}else if(command.equals("/MemberUpdateProc.mf")){//수정할 정보를 수정하기 버튼을 눌렀을시 동작하는컨트롤러
			action = new MemberUpdateAction();
			
			try{
				forward = action.execute(req, resp);
				
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("MemberUpdateProc:"+e);
			}
		}
		//마이페이지 관련...
		//내 캠페인목록
		else if (command.equals("/MemberMyinvestRequest.mf")) {//Memberinfo페이지에서 내 캠페인목록을 눌렀을때
							action = new MemberMyinvestRequest();
							
							try {
								forward = action.execute(req, resp);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("내캠페인목록 보기 오류:"+e);
							}
						}else if (command.equals("/MemberInvestRequestContent.mf")) {//내 캠페인 상세보기
								action = new MemberInvestRequestContent();
								
								try {
									forward = action.execute(req, resp);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("내 캠페인 상세보기 오류:"+e);
								}
						}
						
						//내 투자목록
						else if (command.equals("/MemberMyinvestment.mf")) {
							action = new MemberMyinvestment();
							
							try {
								forward = action.execute(req, resp);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("내 투자목록 오류:"+e);
							}
							
						}
					//내가 쓴 글
						else if (command.equals("/MemberMyWirteView.mf")) {
							action = new MemberMyWirteView();
							
							try {
								forward = action.execute(req, resp);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("내가 쓴 글 오류 : "+e);
							}
						}else if (command.equals("/Memberdeleteboard.mf")) {//친목게시판에 쓴 글 삭제
							action = new Memberdeleteboard();
							
							try {
								forward = action.execute(req, resp);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("친목게시판에 쓴 글 삭제 오류 : "+e);
							}
						}else if (command.equals("/Memberdeletequestion.mf")) {//1:1문의에 쓴 글 삭제
							action = new Memberdeletequestion();
							
							try {
								forward = action.execute(req, resp);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("1:1문의에 쓴 글 삭제 오류 : "+e);
							}
						}
						//내가 쓴 댓글 보기
						else if (command.equals("/MemberMyReWirteView.mf")) {
							action = new MemberMyReWirteView();
							
							try {
								forward = action.execute(req, resp);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("내가 쓴 댓글 보기 오류 : "+e);
							}
							
						}else if (command.equals("/Memberdeletecomment.mf")) {
							action = new Memberdeletecomment();
							
							try {
								forward = action.execute(req, resp);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("내가 쓴 댓글 삭제 오류 : "+e);
							}
							
						}else if (command.equals("/MemberMyCard.mf")) {//카드 잔액 보기
							action = new MemberMyCard();
							
							try {
								forward = action.execute(req, resp);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("카드 잔액 보기 오류 : "+e);
							}
						}
				
					//회원탈퇴
					else if (command.equals("/MemberOutAction.mf")) {
						action = new MemberOutAction();
						
						try {
							forward = action.execute(req, resp);
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("회원탈퇴 오류 : "+e);
						}
					}
		
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
