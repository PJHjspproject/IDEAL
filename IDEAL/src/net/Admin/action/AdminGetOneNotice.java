package net.Admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;
import net.notice.db.noticeDto;

public class AdminGetOneNotice implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int noticeNum = Integer.parseInt(request.getParameter("num"));
		
		AdminDao adao = new AdminDao();
		
		noticeDto ndto = adao.getOneNotice(noticeNum);
		
		request.setAttribute("ndto", ndto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("AdminGetOneNotice.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
