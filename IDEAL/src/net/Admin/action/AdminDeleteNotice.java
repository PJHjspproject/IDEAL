package net.Admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;

public class AdminDeleteNotice implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int noticeNum = Integer.parseInt(request.getParameter("noticeNum"));
		
		AdminDao adao = new AdminDao();
		
		adao.deleteNotice(noticeNum);
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("AdminNotice.ad");
		
		forward.setRedirect(true);
		
		return forward;
	}

}
