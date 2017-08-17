package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDto;
import net.member.db.MemberDAO;

public class Memberdeleteboard implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//num을 파라미터에서 받아와서 정수형변수에 저장
		int num = Integer.parseInt(request.getParameter("num"));
		
		MemberDAO dao = new MemberDAO();
		
	   dao.Memberdeleteboard(num);//친목게시판 글 삭제 메소드 만들기
	   
	   //이동
	   ActionForward forward = new ActionForward();
	   forward.setPath("./MemberMyWirteView.mf");
	   forward.setRedirect(false);
	
		return forward;
	}

}
