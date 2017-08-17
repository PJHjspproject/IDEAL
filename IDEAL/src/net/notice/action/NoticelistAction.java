package net.notice.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.notice.db.noticeDao;
import net.notice.db.noticeDto;

public class NoticelistAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//DAO객체 생성
		noticeDao dao = new noticeDao();
		
		ArrayList<noticeDto> arry = new ArrayList<noticeDto>();
		arry = dao.AllNotice();
		request.setAttribute("arry", arry);
		
		//이동 
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("main1.jsp?center=tap_ex2.jsp&centern=notice/list.jsp");
		
		
		
		return forward;
	}

}
