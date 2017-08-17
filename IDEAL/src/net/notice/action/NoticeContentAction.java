package net.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.notice.db.noticeDao;
import net.notice.db.noticeDto;

public class NoticeContentAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// NoticeNum 가져오기
		int NoticeNum = Integer.parseInt(request.getParameter("noticeNum"));
		
		// dao 객체 생성
		noticeDao dao = new noticeDao();
		
		noticeDto dto = dao.OneNotice(NoticeNum);
		
		//저장
		request.setAttribute("dto", dto);
		
		//이동
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./main1.jsp?center=notice/NoticeContent.jsp");
		return forward;
	}

}
