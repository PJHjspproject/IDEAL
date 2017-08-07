package net.Admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;
import net.board.db.BoardDto;


public class AdminGetOneBoard implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminDao adao = new AdminDao();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDto oneBoard = adao.getOneBoard(num);
		
		request.setAttribute("oneBoard", oneBoard);
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("AdminGetOneBoard.jsp");
		
		forward.setRedirect(false);
		
		return forward;
	}

}
