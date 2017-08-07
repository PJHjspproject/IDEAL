package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MemberInvestRequestContent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int investRequestNum = Integer.parseInt(request.getParameter("investRequestNum"));
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/viewInvestRequestAction.iR?investRequestNum="+investRequestNum);
		System.out.println(investRequestNum);
		return forward;
	}

}
