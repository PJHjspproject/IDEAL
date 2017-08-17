package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDao;

public class BoardDeleteComment implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int cNum = Integer.parseInt(request.getParameter("cNum"));//comment �� pk
		int num = Integer.parseInt(request.getParameter("num"));//board�� pk > comment�� fk
		
		
		System.out.println("BoardDeleteComment()�޼ҵ忡�� Ȯ��");
		System.out.println("�ڸ�Ʈ ��ȣ cNum:"+cNum);
		System.out.println("�Խù� ��ȣ num:"+num);
		//
		BoardDao bdao = new BoardDao();
		bdao.commentDelete(cNum);
		
		ActionForward forward = new ActionForward();
		forward.setPath("content.bo?num="+num);
		forward.setRedirect(true);
		return forward;
	}

}
