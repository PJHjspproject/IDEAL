package net.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.Card.db.CardDto;
import net.member.db.MemberDAO;

public class MemberMyCard implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("MemberEmail");
		
		MemberDAO dao = new MemberDAO();
		
		ArrayList<CardDto> array = new ArrayList<CardDto>();
		
		array = dao.MemberMyCard(email);
		
		request.setAttribute("array", array);
		
		ActionForward forward = new ActionForward();
		forward.setPath("main1.jsp?center=member/MemberMyCard.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
