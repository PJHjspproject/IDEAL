package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDao;
import net.board.db.BoardDto;

public class BoardWrite implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)  {
		
		ActionForward forward = new ActionForward();
		forward.setPath("main1.jsp?center=board/Write.jsp");
		forward.setRedirect(false);
		return forward;
	}
}