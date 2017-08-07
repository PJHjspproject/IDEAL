package net.Admin.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;
import net.notice.db.noticeDto;

public class AdminUpdateNotice implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		noticeDto ndto = new noticeDto();
		ndto.setNoticeNum(Integer.parseInt(request.getParameter("noticeNum")));
		ndto.setNoticeDate(new Timestamp(System.currentTimeMillis()));
		ndto.setNoticeImg(request.getParameter("noticeImg"));
		ndto.setNoticeContent(request.getParameter("noticeContent"));
		ndto.setNoticeSubject(request.getParameter("noticeSubject"));
		
		AdminDao adao = new AdminDao();
		
		adao.UpdateNotice(ndto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("AdminNotice.ad");
		forward.setRedirect(true);
		return forward;
	}

}
