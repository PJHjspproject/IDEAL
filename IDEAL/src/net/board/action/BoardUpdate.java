package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.BoardDao;
import net.board.db.BoardDto;

public class BoardUpdate implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response){
		
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDao bdao= new BoardDao();
		
		
		BoardDto bdto =  bdao.getBoard(num);
		
		
		request.setAttribute("bdto", bdto);
		System.out.println(bdto.getNickName());
		ActionForward forward = new ActionForward();
			
		forward.setPath("main1.jsp?center=board/Update.jsp");
		forward.setRedirect(false);
		return forward;
	}
}