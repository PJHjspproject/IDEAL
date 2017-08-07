package net.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.investRequest.db.InvestRequestDto;
import net.member.db.MemberDAO;



public class MemberMyinvestRequest implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("MemberEmail");
		
		MemberDAO dao = new MemberDAO();
		
		ArrayList<InvestRequestDto> array = new ArrayList<InvestRequestDto>();
		
		array = dao.MemberMyinvestRequest(email);
		
		request.setAttribute("array", array);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("main1.jsp?center=member/MemberMyinvestRequest.jsp");
		
		return forward;
	}

}
