package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDao;

public class BoardDelete implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response){
		
		BoardDao bdao = new BoardDao();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		bdao.deleteBoard(num);
		
		ActionForward forward = new ActionForward();
		forward.setPath("boardList.bo");
		forward.setRedirect(true);
		return forward;
	}
}