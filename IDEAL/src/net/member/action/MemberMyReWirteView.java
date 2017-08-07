package net.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.CommentDto;
import net.member.db.MemberDAO;

public class MemberMyReWirteView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String nickName = (String)session.getAttribute("nickName");
		
		MemberDAO dao = new MemberDAO();
		
		ArrayList<CommentDto> array = new ArrayList<CommentDto>();
		
		array = dao.MemberMyReWirteView(nickName);
		
		request.setAttribute("array", array);
		
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("main1.jsp?center=member/MemberMyReWirteView.jsp");
						
		return forward;
	}

}
