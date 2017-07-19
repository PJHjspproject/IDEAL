package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberMoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");
		request.setAttribute("email", email);
		
		System.out.println(email);
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("./index.jsp?center=member/Joinmember.jsp");
		
		return forward;
	}
	
	

}
