package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class Memberdeletecomment implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int cNum = Integer.parseInt(request.getParameter("cNum"));
		
		MemberDAO dao = new MemberDAO();
		
		dao.Memberdeletecomment(cNum);
	   
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberMyReWirteView.mf");
		forward.setRedirect(false);
	
		return forward;
	}

}
