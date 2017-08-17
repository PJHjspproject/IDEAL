package net.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFrontController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String p1 = request.getRequestURI();
		System.out.println(p1);
		String p2 = request.getContextPath();
		System.out.println(p2);
		String p3 = p1.substring(p2.length());
		System.out.println(p3);

		Action action = null;
		ActionForward forward = null;

		// �Խ��� �� ��� ����
		if (p3.equals("/boardList.bo")) {
			action = new BoardList();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("boardList.bo��� ���� : " + e);
			}
		} else if (p3.equals("/write.bo")) {
			action = new BoardWrite();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (p3.equals("/addpost.bo")) {
			action = new BoardAddPost();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (p3.equals("/content.bo")) {
			action = new BoardContent();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (p3.equals("/update.bo")) {
			action = new BoardUpdate();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (p3.equals("/updatePro.bo")) {
			action = new BoardUpdatePro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (p3.equals("/delete.bo")) {
			action = new BoardDelete();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (p3.equals("/commentInsert.bo")) {
			action = new BoardCommentInsert();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(p3.equals("/download.bo")){
			action = new Downloadfile();
			
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				
			}
			
		}else if(p3.equals("/deleteComment.bo")){
			action = new BoardDeleteComment();
			try{
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("deleteComment.bo ���� ����");
			}
		}

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher view = request.getRequestDispatcher(forward.getPath());
				view.forward(request, response);
			}
		}
	}
}