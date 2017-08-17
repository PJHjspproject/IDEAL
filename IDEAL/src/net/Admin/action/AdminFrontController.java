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
		
		//��ü �ּҰ�������
		String RequestURI = req.getRequestURI();
		System.out.println(RequestURI);
		//��ü �ּ� �� �� �κ� ��������
		String ContextPath = req.getContextPath();
		System.out.println(ContextPath);
		//�����ּҸ� �պκ� ���ڼ���ŭ �߶� ��¥ �ּ� ��������
		String command = RequestURI.substring(ContextPath.length()+6);
		System.out.println(command);
		Action action = null;
		
		ActionForward forward = null;
		
		//��û��Ȳ�� ���� Controller ����
		if(command.equals("/AdminInvestRequest.ad")){
			action = new AdminInvestRequestList();
			System.out.println("if�� ſ��");
			try{
				forward = action.excute(req, resp);
				forward.setRedirect(false);
			}catch(Exception e){
				System.out.println("AdminInvestRequest.ad��� ���� : "+e);
			}
		}else if(command.equals("/AdminInvestRequestList.ad")){
			action = new AdminInvestRequestListPro();
			try{
				forward = action.excute(req, resp);
				forward.setRedirect(false);
			}catch(Exception e){
				System.out.println("AdminInvestRequestList.ad경로 오류 :" +e);
			}
		}else if(command.equals("/AdminGetOneInvestRequest.ad")){
			action = new AdminGetOneInvestRequest();
			try{
				forward = action.excute(req, resp);
				forward.setRedirect(false);
			}catch(Exception e){
				System.out.println("AdminGetOneInvestRequest.ad경로오류 :" +e);
			}
		}else if(command.equals("/AdminGetOneInvestMent.ad")){
			action = new AdminGetOneInvestMent();
			try{
				forward = action.excute(req, resp);
				forward.setRedirect(false);
			}catch(Exception e){
				System.out.println("AdminGetOneInvestMent.ad경로오류 :" +e);
			}
		}
		else if(command.equals("/AdminInvestMentList.ad")){
			action = new AdminInvestMent();
			try{
				forward = action.excute(req, resp);
				forward.setRedirect(false);
			}catch(Exception e){
				System.out.println("AdminInvestMent.ad경로오류 :" +e);
			}
		}else if(command.equals("/AdminMemberList.ad")){
			action = new AdminMemberList();
			System.out.println("if�� ſ��");
			try{
				forward = action.excute(req, resp);
				forward.setRedirect(false);
			}catch(Exception e){
				System.out.println("AdminMemberList.ad��� ���� : "+e);
			}
		}else if(command.equals("/AdminMemberAbout.ad")){
			action = new AdminMemberAbout();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminMemberAbout.ad��� ���� : "+e);
			}
			System.out.println("if�� ſ��");
		}

		else if(command.equals("/AdminBoardList.ad")){
			action = new AdminBoardList();
			System.out.println("if�� ſ��");
			try{
				forward = action.excute(req, resp);
				System.out.println(forward.getPath());
			}catch(Exception e){
				System.out.println("AdminBoard.ad��� ���� : "+e);
			}
		}else if(command.equals("/AdminGetOneBoard.ad")){
			action = new AdminGetOneBoard();
			System.out.println("if�� ſ��");
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminGetOneBoard.ad��� ���� : "+e);
			}
		}else if(command.equals("/AdminBoardDelete.ad")){
			action = new AdminDeleteBoard();
			System.out.println("if�� ſ��");
			try {
				action.excute(req, resp);
			} catch (Exception e) {
				System.out.println("AdminBoardDelete.ad��� ���� : "+e);
			}
		}

		else if(command.equals("/AdminQuestion.ad")){
			action = new AdminQuestionList();
			System.out.println("if�� ſ��");
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminQuestion.ad��� ���� : "+e);
			}
		}else if(command.equals("/AdminGetOneQuestion.ad")){
			action = new AdminGetOneQuestion();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminGetOneQuestion.ad ��� ���� : "+e);
			}
		}else if(command.equals("/AdminUpdateQuestion.ad")){
			action = new AdminUpdateQuestion();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminUpdateQuestion.ad ��� ���� : "+e);
			}
		}else if(command.equals("/AdminCommentList.ad")){
			System.out.println("if문탓");
			action = new AdminCommentList();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminCommentList.ad경로 오류: " + e);
			}
		}else if(command.equals("/AdminNotice.ad")){
			action = new AdminNoticeList();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminNotice.ad ��� ���� : "+e);
			}
		}else if(command.equals("/AdminGetOneNotice.ad")){
			action = new AdminGetOneNotice();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminGetOneNotice.ad��� ���� : "+e);
			}
		}else if(command.equals("/AdminDeleteNotice.ad")){
			action = new AdminDeleteNotice();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminDeleteNotice.ad ��� ���� : "+e);
			}
		}else if(command.equals("/AdminUpdateNotice.ad")){
			action = new AdminUpdateNotice();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminUpdateNotice.ad ��� ���� : "+e);
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
				System.out.println("AdminNoticeWrite.ad ��� ���� : "+e);
			}
		}

		else if(command.equals("/AdminInformationUse.ad")){
			System.out.println("탓어");
			action = new AdminInformationUseList();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminInformationUse.ad ��� ���� : "+e);
			}
		}else if(command.equals("/AdminGetOneInfo.ad")){
			action = new AdminGetOneInfo();
			try{
				forward= action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminGetOneInfo.ad ��� ���� : "+e);
			}
		}else if(command.equals("/AdminUpdateInfo.ad")){
			action =  new AdminUpdateInfo();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminUpdateInfo.ad ��� ���� : "+e);
			}
		}else if(command.equals("/AdminDeleteInfo.ad")){
			action = new AdminDeleteInfo();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminDeleteInfo.ad ��� ���� : "+e);
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
				System.out.println("AdminInsertInfo.ad ��� ���� : "+e);
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
			System.out.println("AdminPermitChk.ad ���� ���� : " +e);
			}
			
		}

				else if(command.equals("/AdminUpdateChk.ad")){
					action = new AdminUpdateChk();
					
					try{
						forward = new ActionForward();
						forward = action.excute(req, resp);
						//AdminInvestRequest.ad ���û �Ͽ� ����� �ٽ� �ѷ�������
						forward.setPath("AdminInvestRequest.ad");
						forward.setRedirect(false);
					}catch(Exception e){
					System.out.println("AdminUpdateChk.ad ���� ���� : " +e);
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
	/*@Override
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
			System.out.println("if�� ſ��");
			try{
				forward = action.excute(req, resp);
				forward.setRedirect(false);
			}catch(Exception e){
				System.out.println("AdminInvestRequest.ad��� ���� : "+e);
			}
		}

		else if(command.equals("/AdminMemberList.ad")){
			action = new AdminMemberList();
			System.out.println("if�� ſ��");
			try{
				forward = action.excute(req, resp);
				forward.setRedirect(false);
			}catch(Exception e){
				System.out.println("AdminMemberList.ad��� ���� : "+e);
			}
		}else if(command.equals("/AdminMemberAbout.ad")){
			action = new AdminMemberAbout();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminMemberAbout.ad��� ���� : "+e);
			}
			System.out.println("if�� ſ��");
		}

		else if(command.equals("/AdminBoardList.ad")){
			action = new AdminBoardList();
			System.out.println("if�� ſ��");
			try{
				forward = action.excute(req, resp);
				System.out.println(forward.getPath());
			}catch(Exception e){
				System.out.println("AdminBoard.ad��� ���� : "+e);
			}
		}else if(command.equals("/AdminGetOneBoard.ad")){
			action = new AdminGetOneBoard();
			System.out.println("if�� ſ��");
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminGetOneBoard.ad��� ���� : "+e);
			}
		}else if(command.equals("/AdminBoardDelete.ad")){
			action = new AdminDeleteBoard();
			System.out.println("if�� ſ��");
			try {
				action.excute(req, resp);
			} catch (Exception e) {
				System.out.println("AdminBoardDelete.ad��� ���� : "+e);
			}
		}
		//�Խ��ǿ� ���� Controller ��
			
		//1:1 ���Ǳۿ� ���� Controller ����
		else if(command.equals("/AdminQuestion.ad")){
			action = new AdminQuestionList();
			System.out.println("if�� ſ��");
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminQuestion.ad��� ���� : "+e);
			}
		}else if(command.equals("/AdminGetOneQuestion.ad")){
			action = new AdminGetOneQuestion();
			try{
				forward = action.excute(req, resp);
			}catch(Exception e){
				System.out.println("AdminGetOneQuestion.ad ��� ���� : "+e);
			}
		}
		//1:1 ���Ǳۿ� ���� Controller ��
		
		
		else if(command.equals("/AdminPermitChk.ad")){
			action = new AdminPermitChk();
			
			try{
				forward = new ActionForward();
				forward = action.excute(req, resp);
				//��� �� �ѷ����°����� �̵�
				//AdminInvestRequest.ad ���û �Ͽ� ����� �ٽ� �ѷ�������
				forward.setPath("AdminInvestRequest.ad");
				forward.setRedirect(false);
			}catch(Exception e){
			System.out.println("AdminPermitChk.ad ���� ���� : " +e);
			}
			
		}
		
		//���� AdminPermitChk Controller ��
		
		//���� AdminUpdateChk Controller ����
				//AdminInvestRequest.jsp���� �Ѿ��
				else if(command.equals("/AdminUpdateChk.ad")){
					action = new AdminUpdateChk();
					
					try{
						forward = new ActionForward();
						forward = action.excute(req, resp);
						//AdminInvestRequest.ad ���û �Ͽ� ����� �ٽ� �ѷ�������
						forward.setPath("AdminInvestRequest.ad");
						forward.setRedirect(false);
					}catch(Exception e){
					System.out.println("AdminUpdateChk.ad ���� ���� : " +e);
					}
					
				}
				
				//���� AdminUpdateChk Controller ��
		
	
		//AdminMain.jsp�������� �̵��ϴ� ���ǹ� ����
		else if(command.equals("/AdminMain.ad")){
			forward = new ActionForward();
			forward.setPath("AdminMain.jsp");
			forward.setRedirect(false);
		}
		//AdminMain.jsp�������� �̵��ϴ� ���ǹ� ��
		if(forward != null){
			if(forward.isRedirect()){
				resp.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher view = req.getRequestDispatcher(forward.getPath());
				view.forward(req, resp);
			}
		}
		
		
	}*/
}
