package net.Admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;
import net.notice.db.noticeDto;

public class AdminNoticeList implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminDao adao = new AdminDao();
		
		ArrayList<noticeDto> nList = adao.noticeList();
		
		request.setAttribute("nList", nList);
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("AdminNoticeList.jsp");
		
		forward.setRedirect(false);
		
		return forward;
	}

}
