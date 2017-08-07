package net.Admin.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;
import net.notice.db.noticeDto;

public class AdminInsertNotice implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		noticeDto ndto = new noticeDto();
		
		ndto.setNoticeContent(request.getParameter("content"));
		ndto.setNoticeDate(new Timestamp(System.currentTimeMillis()));
		ndto.setNoticeImg(request.getParameter("img"));
		ndto.setNoticeSubject(request.getParameter("subject"));
		System.out.println(request.getParameter("img"));
		AdminDao adao = new AdminDao();
		
		adao.insertNotice(ndto);
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("./AdminNotice.ad");
		
		forward.setRedirect(true);
		
		return forward;
		
	}

}
