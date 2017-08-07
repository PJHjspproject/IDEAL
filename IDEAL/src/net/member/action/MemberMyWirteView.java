package net.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.BoardDto;
import net.member.db.MemberDAO;
import net.question.db.questionDto;

public class MemberMyWirteView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String nickName = (String)session.getAttribute("nickName");
		
		MemberDAO dao = new MemberDAO();
		
		ArrayList<BoardDto> arrayb = new ArrayList<BoardDto>();
		
		arrayb = dao.MemberMyWirteBoardView(nickName);
		
		request.setAttribute("arrayb", arrayb);
		
		ArrayList<questionDto> arrayq = new ArrayList<questionDto>();
		
		arrayq = dao.MemberMyWirteQuestionView(nickName);
		
		request.setAttribute("arrayq", arrayq);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("main1.jsp?center=member/MemberMyWirteView.jsp");
				
		return forward;
	}

}
