package net.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.notice.db.noticeDao;
import net.notice.db.noticeDto;

public class NoticeContentAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int NoticeNum = Integer.parseInt(request.getParameter("noticeNum"));
		
		
		noticeDao dao = new noticeDao();
		
		noticeDto dto = dao.OneNotice(NoticeNum);
		
		
		request.setAttribute("dto", dto);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./main1.jsp?center=notice/NoticeContent.jsp");
		return forward;
	}

}
