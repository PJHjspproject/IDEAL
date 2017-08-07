package net.Admin.action;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminFrontController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String RequestURI = req.getRequestURI();
		System.out.println(RequestURI);
		
		String ContextPath = req.getContextPath();
		System.out.println(ContextPath);
		
		String command = RequestURI.substring(ContextPath.length()+6);
		System.out.println(command);
		Action action = null;
		
		ActionForward forward = null;
		
		if(command.equals("/AdminInvestRequest.ad")){
			action = new AdminInvestRequestList();
			
			try{
				forward = action.excute(req, resp);
				forward.setRedirect(false);
			}catch(Exception e){
				System.out.println("AdminInvestRequest.ad에서 에러 : "+e);
			}
		}

		else if(command.equals("/AdminMemberList.ad")){
			action = new AdminMemberList();
			
			try{
				forward = action.excute(req, resp);
				forward.setRedirect(false);
			}catch(Exception e){
				System.out.println("AdminMemberList.ad에서 에러 :"+e);
			}
		}else if(command.equals("/AdminMemberAbout.ad")){
			action = new AdminMemberAbout();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminMemberAbout.ad에서 에러 : "+e);
			}
			
		}

		else if(command.equals("/AdminBoardList.ad")){
			action = new AdminBoardList();
			
			try{
				forward = action.excute(req, resp);
				System.out.println(forward.getPath());
			}catch(Exception e){
				System.out.println("AdminBoard.ad에서 에러 : "+e);
			}
		}else if(command.equals("/AdminGetOneBoard.ad")){
			action = new AdminGetOneBoard();
			
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminGetOneBoard.ad에서 에러 :"+e);
			}
		}else if(command.equals("/AdminBoardDelete.ad")){
			action = new AdminDeleteBoard();
			
			try {
				action.excute(req, resp);
			} catch (Exception e) {
				System.out.println("AdminBoardDelete.ad에서 에러 : "+e);
			}
		}

		else if(command.equals("/AdminQuestion.ad")){
			action = new AdminQuestionList();
			
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminQuestion.ad에서 에러 : "+e);
			}
		}else if(command.equals("/AdminGetOneQuestion.ad")){
			action = new AdminGetOneQuestion();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminGetOneQuestion.ad 에서 에러 : "+e);
			}
		}else if(command.equals("/AdminUpdateQuestion.ad")){
			action = new AdminUpdateQuestion();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminUpdateQuestion.ad 에서 에러 : "+e);
			}
		}

		else if(command.equals("/AdminNotice.ad")){
			action = new AdminNoticeList();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminNotice.ad 에서 에러 :"+e);
			}
		}else if(command.equals("/AdminGetOneNotice.ad")){
			action = new AdminGetOneNotice();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminGetOneNotice.ad에서 에러 : "+e);
			}
		}else if(command.equals("/AdminDeleteNotice.ad")){
			action = new AdminDeleteNotice();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminDeleteNotice.ad에서 에러 : "+e);
			}
		}else if(command.equals("/AdminUpdateNotice.ad")){
			action = new AdminUpdateNotice();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminUpdateNotice.ad 에서 에러 : "+e);
			}
		}else if(command.equals("/AdminInsertNotice.ad")){
			forward = new ActionForward();
			forward.setPath("AdminNoticeWrite.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/AdminNoticeWrite.ad")){
			action = new AdminInsertNotice();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminNoticeWrite.ad 에서 에러 : "+e);
			}
		}

		else if(command.equals("/AdminInformationUse.ad")){
			System.out.println("탓어");
			action = new AdminInformationUseList();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminInformationUse.ad 에서 에러 : "+e);
			}
		}else if(command.equals("/AdminGetOneInfo.ad")){
			action = new AdminGetOneInfo();
			try{
				forward= action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminGetOneInfo.ad 에서 에러 : "+e);
			}
		}else if(command.equals("/AdminUpdateInfo.ad")){
			action =  new AdminUpdateInfo();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminUpdateInfo.ad 에서 에러 :"+e);
			}
		}else if(command.equals("/AdminDeleteInfo.ad")){
			action = new AdminDeleteInfo();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminDeleteInfo.ad 에서 에러 :"+e);
			}
		}else if(command.equals("/AdminWriteInfo.ad")){
			forward = new ActionForward();
			forward.setPath("AdminInfoWrite.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/AdminInsertInfo.ad")){
			action = new AdminInsertInfo();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminInsertInfo.ad에서 에러 : "+e);
			}
		}	
		else if(command.equals("/AdminPermitChk.ad")){
			action = new AdminPermitChk();
			
			try{
				forward = new ActionForward();
				forward = action.excute(req, resp);

				forward.setPath("AdminInvestRequest.ad");
				forward.setRedirect(false);
			}catch(Exception e){
			System.out.println("AdminPermitChk.ad 에서 에러 : " +e);
			}
			
		}

				else if(command.equals("/AdminUpdateChk.ad")){
					action = new AdminUpdateChk();
					
					try{
						forward = new ActionForward();
						forward = action.excute(req, resp);
						
						forward.setPath("AdminInvestRequest.ad");
						forward.setRedirect(false);
					}catch(Exception e){
					System.out.println("AdminUpdateChk.ad 에서 에러 :" +e);
					}
					
				}

		else if(command.equals("/AdminMain.ad")){
			forward = new ActionForward();
			forward.setPath("AdminMain.jsp");
			forward.setRedirect(false);
		}

		if(forward != null){
			if(forward.isRedirect()){
				System.out.println("sendredirect");
				resp.sendRedirect(forward.getPath());
			}else{
				System.out.println("RequestDispatcher");
				RequestDispatcher view = req.getRequestDispatcher(forward.getPath());
				view.forward(req, resp);
			}
		}
		
		
	}

}
