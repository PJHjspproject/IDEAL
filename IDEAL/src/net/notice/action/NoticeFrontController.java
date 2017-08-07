package net.notice.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoticeFrontController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String requestURI = req.getRequestURI();
		System.out.println(requestURI);

		String contextPath = req.getContextPath();
		System.out.println(contextPath);

		String command = requestURI.substring(contextPath.length());
		System.out.println(command);
		Action action = null;
		ActionForward forward = null;

		if (command.equals("/list.no")) {// list.no(공지사항)페이지를 요청 받았다면

			action = new NoticelistAction();

			try {
				forward = action.excute(req, resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (command.equals("/NoticeContent.no")) {// 하나의 공지사항 상세 페이지를
															// 보여주려면
			action = new NoticeContentAction();

			try {
				forward = action.excute(req, resp);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		if (forward != null) {
			if (forward.isRedirect()) {
				resp.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher view = req.getRequestDispatcher(forward.getPath());
				view.forward(req, resp);
			}
		}

	}

}
