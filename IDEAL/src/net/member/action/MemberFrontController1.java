package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*占쎈슓維귛뜝�럥梨띠쮯�뜝�룞�삕 .mf�뤆�룊�삕 �뜝�룞�삕�뜝�룞�삕 URI�뜝�룞�삕 �뜝�룞�삕占쎈닱�뜝占� �뜝�룞�삕 占쎈슓維귛뜝�럥梨띠쮯�뜝�떬�맮�삕�뜝占� 濾곌쑨�ｅ뜝�룞�삕�뜝占�.*/
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
		
		if(command.equals("/yak.mf")){
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main1.jsp?center=member/yak.jsp");
			
		}else if(command.equals("/mailauth.mf")){
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main1.jsp?center=member/mailauth.jsp");
			
		}else if(command.equals("/index.mf")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main1.jsp");
			System.out.println(forward.getPath());
		}else if(command.equals("/Joinmember.mf")){

			action = new MemberMoveAction();
			
			try{
				forward = action.execute(req, resp);
			}catch(Exception e){
				System.out.println("�뜝�럥梨룟뜝�룞�삕�뜝�룞�삕占쎈쳴�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 占쎈닱筌뤾쒀�삕�뜝占� :"+e);
			}
			
			
		}else if(command.equals("/MemberJoin.mf")){
			action = new MemberJoinAction();
			try{
				forward =action.execute(req, resp);
			}catch(Exception e){
				System.out.println("�뜝�룞�삕�뜝�룞�삕�뤆�룊�삕�뜝�룞�삕 �뜝�뜫�걢�뜝�룞�삕 :"+e);
			}
		}else if(command.equals("/MemberLoginAction.mf")){
			action = new MemberLoginAction();
			
			try{
				
				forward = action.execute(req, resp);
			}catch(Exception e){
				System.out.println("�뜝�룞�삕�뜝�룞�삕�뤆�룊�삕�뜝�룞�삕 �뜝�뜫�걢�뜝�룞�삕 :"+e);
				
			}
			
		}else if(command.equals("/MemberLogoutAction.mf")){
			action = new MemberLogoutAction();
			try{
				
				forward = action.execute(req, resp);
			}catch(Exception e){
				System.out.println("MemberLogoutAction.mf�뇦猿뗫윥�뜝�룞�삕占쎈걞�뜝占� :"+e);
				
			}
			
		}else if(command.equals("/MemberPassCheckAction.mf")){
			action = new MemberPassCheckAction();
			try{
				
				forward = action.execute(req, resp);
			}catch(Exception e){
				System.out.println("嶺뚮엪�삕�뜝�럥�샑�뜝�룞�삕�뜝�럥�샍�뜝占� �뜝�럥�뼬�뵳占� �뜝�뜫�걢�뜝�룞�삕:"+e);
				
			}
			
		}else if(command.equals("/Memberinfo.mf")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main1.jsp?center=member/Memberinfo.jsp");
			
		}else if(command.equals("/Usercheckview.mf")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main1.jsp?center=member/Usercheck.jsp");
		}else if(command.equals("/MemberView.mf")){
			
			action = new MemberInfoViewAction();
			try{
				forward = action.execute(req, resp);
			}catch(Exception e){
				System.out.println("�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕繞벿우삕 �뜝�뜫�걢�뜝�룞�삕:"+e);
				e.printStackTrace();
			}
		}else if(command.equals("/UpdateViewpage.mf")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main1.jsp?center=member/UserUpdate.jsp");
	
		}else if(command.equals("/MemberUpdateProc.mf")){
			action = new MemberUpdateAction();
			
			try{
				forward = action.execute(req, resp);
				
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�뜫�걞�뜝占�:"+e);
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

		
		
		// �뜝�뜫�걠�뜝�룞�삕�썒�슣�닔�뜝�룞�삕�뜝�럥占썲뜝�룞�삕
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
