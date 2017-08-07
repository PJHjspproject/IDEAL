package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDao;

public class BoardDeleteComment implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int cNum = Integer.parseInt(request.getParameter("cNum"));
		int num = Integer.parseInt(request.getParameter("num"));
		
		
		System.out.println("BoardDeleteComment()�޼ҵ忡�� Ȯ��");
		System.out.println("Comment cNum:"+cNum);
		System.out.println("Comment num:"+num);
		//
		BoardDao bdao = new BoardDao();
		bdao.commentDelete(cNum);
		
		ActionForward forward = new ActionForward();
		forward.setPath("content.bo?num="+num);
		forward.setRedirect(true);
		return forward;
	}

}
