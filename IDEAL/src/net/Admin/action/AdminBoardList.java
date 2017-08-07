package net.Admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;
import net.board.db.BoardDto;


public class AdminBoardList implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminDao adao = new AdminDao();
		
		String nickName = request.getParameter("nickName");
		ActionForward forward = new ActionForward();
		if(nickName == null){
			ArrayList<BoardDto> BoardList = adao.BoardList();
			
			request.setAttribute("BoardList", BoardList);
			
			forward.setPath("AdminBoardList.jsp");
			
			forward.setRedirect(false);
		}else{
			ArrayList<BoardDto> BoardList = adao.BoardList(nickName);
			
			request.setAttribute("BoardList", BoardList);
			
			forward.setPath("AdminMemberBoardList.jsp");
			
			forward.setRedirect(false);
		}
		
		return forward;
	}

}
