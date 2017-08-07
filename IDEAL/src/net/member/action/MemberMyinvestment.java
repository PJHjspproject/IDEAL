package net.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.Investment.db.InvestmentDto;
import net.member.db.MemberDAO;

public class MemberMyinvestment implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("MemberEmail");
		
		MemberDAO dao = new MemberDAO();
		
		ArrayList<InvestmentDto> array = new ArrayList<InvestmentDto>();
		array = dao.MemberMyinvestment(email);
		
		request.setAttribute("array", array);
		
		ActionForward forward = new ActionForward();
		forward.setPath("main1.jsp?center=member/MemberMyinvestment.jsp");
		forward.setRedirect(false);
		System.out.println("dkdkdkdk");
		return forward;
		
		
	}

}
